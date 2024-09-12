package com.bkartisan.be.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.bkartisan.be.Dto.ProductDetailDTO;
import com.bkartisan.be.Dto.ProductFilterForAdminPageDTO;
import com.bkartisan.be.Dto.ProductsForAdminPageDTO;
import com.bkartisan.be.Dto.ProductForAdminPageMapper;
import com.bkartisan.be.Dto.ProductsForHomePageDTO;
import com.bkartisan.be.Dto.ProductsItemForHomePageMapper;
import com.bkartisan.be.Entity.Category;
import com.bkartisan.be.Entity.Product;
import com.bkartisan.be.ExceptionHandler.ErrorResponse;
import com.bkartisan.be.Service.CategoryService;
import com.bkartisan.be.Service.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

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




    @Operation(summary = "Get products", tags = { "Product" }, responses = {
        @ApiResponse(responseCode = "200", description = "Return a list of products and it's length, if system does not find any products it will return an empty list", 
        content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ProductsForHomePageDTO.class))
        }),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content())
    })

    @GetMapping("products")
    public ResponseEntity<ProductsForHomePageDTO> getProductsForHomePage(
            @RequestParam(defaultValue = "") String searchTerm, @RequestParam(defaultValue = "") Integer category,
            @RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer offset) {
        List<Product> prods = productService.getProducts(searchTerm, category, page, offset);
        ProductsForHomePageDTO prodsDTO = new ProductsForHomePageDTO(prods.size(),
                prods.stream().map(itemForHomePageMapper).collect(Collectors.toList()));
        return ResponseEntity.ok(prodsDTO);
    }




    @Operation(summary = "Get product details", tags = { "Product" }, responses = {
        @ApiResponse(responseCode = "200", description = "Return product details", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ProductDetailDTO.class))
        }),
        @ApiResponse(responseCode = "404", description = "Product not found", content = @Content(
            mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)
        )),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content())
    })

    @GetMapping("products/{id}")
    private ResponseEntity<ProductDetailDTO> getProductDetails(@PathVariable Integer id) {
        Product prod = productService.getProduct(id);
        List<Category> categories = categoryService.getCategoryHierachies(prod.getCategory());
        ProductDetailDTO prodDTO = new ProductDetailDTO(prod, categories);
        return ResponseEntity.ok(prodDTO);
    }





    @GetMapping("products-list")
    private ResponseEntity<List<ProductsForAdminPageDTO>> getProductsForAdminPage(ProductFilterForAdminPageDTO filter) {
        List<Product> prods = productService.getProductsForAdminPage(filter);
        List<ProductsForAdminPageDTO> prodsDTO = prods.stream().map(productForAdminPageMapper)
                .collect(Collectors.toList());
        return ResponseEntity.ok(prodsDTO);
    }
}
