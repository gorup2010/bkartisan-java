package com.bkartisan.be.Dto;

import java.time.ZonedDateTime;

import com.bkartisan.be.Constant.OrderStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class OrderForSellerPageDTO {
    private String orderId;
    private String seller;
    private ZonedDateTime createAt;
    private OrderStatus status;
    private String paymentMethod;
    private Boolean isGift;
    private Integer totalPrice;
    private String buyer;
}
