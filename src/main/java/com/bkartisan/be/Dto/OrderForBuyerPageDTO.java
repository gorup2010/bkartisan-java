package com.bkartisan.be.Dto;

import java.util.List;

import com.bkartisan.be.Constant.OrderStatus;

import java.time.ZonedDateTime;
import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class OrderForBuyerPageDTO {

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
    private OrderStatus status;
    private Integer totalPrice;
    private Integer shipPrice;
    private Integer discountPrice;
    private ZonedDateTime createAt;
    private String paymentMethod;
    private String seller;
    private String sellerName;
    private String sellerAvatar;
    private List<Item> items;

    public OrderForBuyerPageDTO(OrderBuyerQueryResult order) {
        this.orderId = order.getOrderId();
        this.status = order.getStatus();
        this.totalPrice = order.getTotalPrice();
        this.shipPrice = order.getShipPrice();
        this.discountPrice = order.getDiscountPrice();
        this.createAt = order.getCreateAt();
        this.paymentMethod = order.getPaymentMethod();
        this.seller = order.getSeller();
        this.sellerName = order.getSellerName();
        this.sellerAvatar = order.getSellerAvatar();
        this.items = new ArrayList<>();
        this.items.add(new Item(order.getCoverImage(), order.getName(), order.getQuantity(), order.getPrice(), order.getDiscount()));
    }

    public void addItem(OrderBuyerQueryResult order) {
        this.items.add(new Item(order.getCoverImage(), order.getName(), order.getQuantity(), order.getPrice(), order.getDiscount()));
    }
}
