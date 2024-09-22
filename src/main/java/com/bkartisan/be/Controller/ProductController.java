package com.bkartisan.be.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.bkartisan.be.Constant.ProductStatus;
import com.bkartisan.be.Dto.ProductDetailDTO;
import com.bkartisan.be.Dto.ProductFilterForAdminPageDTO;
import com.bkartisan.be.Dto.ProductsForAdminPageDTO;
import com.bkartisan.be.Dto.ProductForAdminPageMapper;
import com.bkartisan.be.Dto.ProductForHomePageDTO;
import com.bkartisan.be.Dto.ProductForHomePageMapper;
import com.bkartisan.be.Dto.ProductForSellerPageDTO;
import com.bkartisan.be.Dto.ProductForSellerPageMapper;
import com.bkartisan.be.Entity.Category;
import com.bkartisan.be.Entity.Product;
import com.bkartisan.be.ExceptionHandler.ErrorResponse;
import com.bkartisan.be.Service.CategoryService;
import com.bkartisan.be.Service.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private ProductService productService;
    private CategoryService categoryService;
    private ProductForHomePageMapper productForHomePageMapper;
    private ProductForAdminPageMapper productForAdminPageMapper;
    private ProductForSellerPageMapper productForSellerPageMapper;

    @Autowired
    public ProductController(ProductService productService, CategoryService categoryService,
            ProductForHomePageMapper productForHomePageMapper, ProductForAdminPageMapper productForAdminPageMapper,
            ProductForSellerPageMapper productForSellerPageMapper) {
        this.categoryService = categoryService;
        this.productService = productService;
        this.productForHomePageMapper = productForHomePageMapper;
        this.productForAdminPageMapper = productForAdminPageMapper;
        this.productForSellerPageMapper = productForSellerPageMapper;
    }




    @Operation(summary = "Get products for home page", tags = { "Product" }, responses = {
        @ApiResponse(responseCode = "200", description = "Return a list of products, if system does not find any products it will return an empty list", 
        content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ProductForHomePageDTO.class, type = "array"))
        }),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content())
    })

    @GetMapping()
    public ResponseEntity<List<ProductForHomePageDTO>> getProductsForHomePage(
            @RequestParam(defaultValue = "") String searchTerm, @RequestParam(defaultValue = "") Integer category,
            @RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer offset) {
        List<Product> prods = productService.getProductsForHomePage(searchTerm, category, page, offset);
        List<ProductForHomePageDTO> prodsDTO = prods.stream().map(productForHomePageMapper).collect(Collectors.toList());
        return ResponseEntity.ok(prodsDTO);
    }




    @Operation(summary = "Get products for seller page", tags = { "Product" }, responses = {
        @ApiResponse(responseCode = "200", description = "Return a list of products, if system does not find any products it will return an empty list", 
        content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ProductForSellerPageDTO.class, type = "array"))
        }),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content())
    })

    @GetMapping("seller")
    @Secured("seller")
    public ResponseEntity<List<ProductForSellerPageDTO>> getProductsForSellerPage(
            @RequestParam(defaultValue = "") String searchTerm, @RequestParam(defaultValue = "") ProductStatus status,
            @RequestParam(defaultValue = "") Boolean isSoldOut, @RequestParam(defaultValue = "1") Integer page, 
            @RequestParam(defaultValue = "10") Integer offset, Principal principal) {
        List<Product> prods = productService.getProductsForSellerPage(searchTerm, principal.getName(), status, isSoldOut, page, offset);
        List<ProductForSellerPageDTO> prodsDTO = prods.stream().map(productForSellerPageMapper).collect(Collectors.toList());
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

    @GetMapping("{id}")
    public ResponseEntity<ProductDetailDTO> getProductDetails(@PathVariable Integer id) {
        Product prod = productService.getProduct(id);
        List<Category> categories = categoryService.getCategoryHierachies(prod.getCategory());
        ProductDetailDTO prodDTO = new ProductDetailDTO(prod, categories);
        return ResponseEntity.ok(prodDTO);
    }





    // @GetMapping("products-list")
    // private ResponseEntity<List<ProductsForAdminPageDTO>> getProductsForAdminPage(ProductFilterForAdminPageDTO filter) {
    //     List<Product> prods = productService.getProductsForAdminPage(filter);
    //     List<ProductsForAdminPageDTO> prodsDTO = prods.stream().map(productForAdminPageMapper)
    //             .collect(Collectors.toList());
    //     return ResponseEntity.ok(prodsDTO);
    // }
}
