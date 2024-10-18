package com.bkartisan.be.Dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderBuyerQueryResult {
    private String orderId;
    private String status;
    private Integer totalPrice;
    private Integer shipPrice;
    private Integer discountPrice;
    private LocalDateTime createAt;
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
