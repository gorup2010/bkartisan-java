package com.bkartisan.be.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderSellerQueryResult {
    String orderId;
    Integer shipPrice;
    Integer discountPrice;
    Integer totalPrice;
    Integer totalQuantity;
    Boolean hasGift;
    Boolean isReturn;
    String buyerName;
    String buyerUsername;
    String buyerNumPhone;
    String buyerEmail;
    String buyerAddress;
    String buyerAvatar;
    String productId;
    String productName;
    Integer productQuantity;
    Integer productPrice;
}
