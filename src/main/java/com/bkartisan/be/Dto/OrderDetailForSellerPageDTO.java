package com.bkartisan.be.Dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderDetailForSellerPageDTO {

    @Getter
    @AllArgsConstructor
    class Item {
        String productId;
        String name;
        Integer quantity;
        Integer price;
    }

    String orderId;
    Integer shipPrice;
    Integer discountPrice;
    Integer totalPrice;
    Integer totalQuantity;
    Boolean hasGift;
    Boolean isReturn;
    UserInformationDTO buyer;
    List<Item> items;
}
