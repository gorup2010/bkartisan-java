package com.bkartisan.be.Dto;

import java.util.List;

import com.bkartisan.be.Entity.Order;
import com.bkartisan.be.Entity.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderBuyerDTO {

    @Getter
    @AllArgsConstructor
    class Item {
        private String coverImage;
        private String name;
        private Integer quantity;
        private Integer price;
        private Integer discount;
    }

    private String orderId;
    private String status;
    private Integer totalPrice;
    private Integer shipPrice;
    private Integer discountPrice;
    private String createAt;
    private String paymentMethod;
    private String seller;
    private String sellerName;
    private String sellerAvatar;
    private List<Item> items;

    public OrderBuyerDTO(Order order, User user) {
        this.orderId = order.getOrderId();
        this.status = order.getStatus().toString();
        this.totalPrice = order.getTotalPrice();
        this.shipPrice = order.getShipPrice();
        this.discountPrice = order.getDiscountPrice();
        this.createAt = order.getCreateAt().toString();
        this.paymentMethod = order.getPaymentMethod();
        this.seller = user.getUsername();
        this.sellerName = user.getName();
        this.sellerAvatar = user.getAvatar();
    }
}
