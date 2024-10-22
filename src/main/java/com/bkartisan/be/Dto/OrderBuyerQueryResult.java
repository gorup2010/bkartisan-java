package com.bkartisan.be.Dto;

import java.time.ZonedDateTime;

import com.bkartisan.be.Constant.OrderStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderBuyerQueryResult {
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
    private String coverImage;
    private String name;
    private Integer quantity;
    private Integer price;
    private Integer discount;
}
