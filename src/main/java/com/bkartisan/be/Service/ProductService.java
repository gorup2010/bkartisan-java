package com.bkartisan.be.Service;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bkartisan.be.Repository.ProductRepository;
import com.bkartisan.be.Util.FileUploaderUtil;
import com.bkartisan.be.Constant.ErrorMessage;
import com.bkartisan.be.Dto.CreateProductDTO;
import com.bkartisan.be.Dto.ProductFilterForAdminPageDTO;
import com.bkartisan.be.Dto.ProductFilterForSellerPageDTO;
import com.bkartisan.be.Entity.Product;
import com.bkartisan.be.Entity.ProductLink;
import com.bkartisan.be.Entity.User;
import com.bkartisan.be.ExceptionHandler.NotFoundException;

@Service
public class ProductService {
    
    UserService userService;
    ProductRepository productRepo;
    FileUploaderUtil uploaderUtil;

    @Autowired
    public ProductService(ProductRepository productRepo, UserService userService, FileUploaderUtil uploaderUtil) {
        this.uploaderUtil = uploaderUtil;
        this.productRepo = productRepo;
        this.userService = userService;
    }

    public Product getProduct(Integer id) {
        Product product = productRepo.findById(id).orElse(null);
        if (product == null)
            throw new NotFoundException(ErrorMessage.NOT_FOUND_PRODUCT);
        return product;
    }

    public List<Product> getProductsForHomePage(String searchTerm, Integer category, Integer page, Integer size) {
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return productRepo.findByNameAndCategory(searchTerm, category, pageRequest);
    }

    public List<Product> getProductsForAdminPage(ProductFilterForAdminPageDTO filter) {
        return productRepo.findByFilterInAdminPage(filter);
    }

    public List<Product> getProductsForSellerPage(ProductFilterForSellerPageDTO filter, String username) {
        List<Product> prods = productRepo.findByFilterInSellerPage(filter, username);
        return prods;
    }

    public Product createProduct(CreateProductDTO productDTO, String username) {
        List<MultipartFile> files = new ArrayList<>(productDTO.images());
        files.addAll(productDTO.videos());

        Map<String, String> results = uploaderUtil.uploadFiles(files);

        User seller = userService.getReferenceByUsername(username);

        Product product = Product.builder().name(productDTO.name())
                .price(productDTO.price())
                .description(productDTO.description())
                .quantity(productDTO.quantity())
                .material(productDTO.material())
                .category(productDTO.category())
                .type(productDTO.type())
                .introduction(productDTO.introduction())
                .seller(seller)
                .build();

        List<ProductLink> links = results.entrySet().stream()
                .map(e -> ProductLink.builder().link(e.getKey()).type(e.getValue()).product(product).build())
                .toList();

        product.setCoverImage(links.get(0).getLink());
        product.setAssets(links);
        
        // Return the saved product
        return productRepo.save(product);
    }
}
