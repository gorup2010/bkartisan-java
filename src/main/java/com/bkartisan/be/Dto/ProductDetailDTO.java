package com.bkartisan.be.Dto;

import com.bkartisan.be.Entity.Category;
import com.bkartisan.be.Entity.Comment;
import com.bkartisan.be.Entity.Product;
import com.bkartisan.be.Entity.ProductLink;
import com.bkartisan.be.Entity.User;

import lombok.Getter;

import java.util.List;

@Getter
public class ProductDetailDTO {

    @Getter
    private class ProductLinkDTO {
        String link;
        String type;

        ProductLinkDTO(ProductLink link) {
            this.link = link.getLink();
            this.type = link.getType();
        }
    }

    @Getter
    private class CommentDTO {
        Comment data;
        List<Comment> replies;

        CommentDTO(Comment comment) {
            this.data = comment;
            this.replies = comment.getChildComments();
        }
    }

    @Getter
    private class CategoryDTO {
        Integer id;
        String name;
        Integer level;

        CategoryDTO(Category category) {
            this.id = category.getCategoryId();
            this.name = category.getName();
            this.level = category.getLevel();
        }
    }

    private String name;
    private List<ProductLinkDTO> assets;
    private List<CommentDTO> comments;
    private List<CategoryDTO> categories;
    private String introduction;
    private String description;
    private String seller;
    private String sellerName;
    private String avatar;
    private Integer price;
    private Integer discount;
    private Boolean isOnSale;
    private String status;
    private Integer numberOfStar;
    private Integer numberOfRating;
    

    private void setComments(List<Comment> comments) {
        this.comments = comments.stream().filter(comment -> !comment.getChildComments().isEmpty()).map(CommentDTO::new).toList();
    }

    private void setAssets(List<ProductLink> assets) {
        this.assets = assets.stream().map(ProductLinkDTO::new).toList();
    }

    private void setCategories(List<Category> categories) {
        this.categories = categories.stream().map(CategoryDTO::new).toList();
    }

    public ProductDetailDTO(Product product, List<Category> categories) {

        this.name = product.getName();
        this.introduction = product.getIntroduction();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.discount = product.getDiscount();
        this.isOnSale = product.getIsOnSale();
        this.status = product.getStatus();
        this.numberOfStar = product.getNumberOfStar();

        User seller = product.getSeller();
        this.seller = seller.getUsername();
        this.sellerName = seller.getName();
        this.avatar = seller.getAvatar();

        setComments(product.getComments());
        setAssets(product.getAssets());
        setCategories(categories);
        
    }

}
