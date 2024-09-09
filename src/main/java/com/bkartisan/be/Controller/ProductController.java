package com.bkartisan.be.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.bkartisan.be.Dto.ProductDetailDTO;
import com.bkartisan.be.Dto.ProductFilterForAdminPageDTO;
import com.bkartisan.be.Dto.ProductForAdminPageDTO;
import com.bkartisan.be.Dto.ProductForAdminPageMapper;
import com.bkartisan.be.Dto.ProductsForHomePageDTO;
import com.bkartisan.be.Dto.ProductsItemForHomePageMapper;
import com.bkartisan.be.Entity.Category;
import com.bkartisan.be.Entity.Product;
import com.bkartisan.be.Service.CategoryService;
import com.bkartisan.be.Service.ProductService;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/v1")
public class ProductController {

    private ProductService productService;
    private CategoryService categoryService;
    private ProductsItemForHomePageMapper itemForHomePageMapper;
    private ProductForAdminPageMapper productForAdminPageMapper;

    @Autowired
    public ProductController(ProductService productService, CategoryService categoryService,
            ProductsItemForHomePageMapper itemForHomePageMapper,
            ProductForAdminPageMapper productForAdminPageMapper) {
        this.categoryService = categoryService;
        this.productService = productService;
        this.itemForHomePageMapper = itemForHomePageMapper;
        this.productForAdminPageMapper = productForAdminPageMapper;
    }

    @GetMapping("products")
    public ResponseEntity<ProductsForHomePageDTO> getProductsForHomePage(
            @RequestParam(defaultValue = "") String searchTerm,
            @RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer offset) {
        List<Product> prods = productService.getProducts(searchTerm, page, offset);
        ProductsForHomePageDTO prodsDTO = new ProductsForHomePageDTO(prods.size(),
                prods.stream().map(itemForHomePageMapper).collect(Collectors.toList()));
        return ResponseEntity.ok(prodsDTO);
    }

    @GetMapping("products/{id}")
    private ResponseEntity<ProductDetailDTO> getProductDetails(@PathVariable Integer id) {
        Product prod = productService.getProduct(id);
        List<Category> categories = categoryService.getCategoryHierachies(prod.getCategory());
        System.out.println(categories);
        ProductDetailDTO prodDTO = new ProductDetailDTO(prod, categories);
        return ResponseEntity.ok(prodDTO);
    }

    @GetMapping("products-list")
    private ResponseEntity<List<ProductForAdminPageDTO>> getProductsForAdminPage(ProductFilterForAdminPageDTO filter) {
        List<Product> prods = productService.getProductsForAdminPage(filter);
        List<ProductForAdminPageDTO> prodsDTO = prods.stream().map(productForAdminPageMapper)
                .collect(Collectors.toList());
        return ResponseEntity.ok(prodsDTO);
    }
}
