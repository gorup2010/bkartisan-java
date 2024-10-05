package com.bkartisan.be.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @Column(length = 10)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String seller;

    @Column(insertable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column
    private String status;

    @Column
    private String paymentMethod;

    @Column
    @Schema(description = "The order contains gift-type product or not")
    private Boolean hasGift;

    @Column
    private Integer totalPrice;

    @Column
    @Schema(description = "Refer to username column of user table")
    private String buyer;

    @Column
    @Schema(description = "Refer to name column of user table")
    private String buyerName;

    @Column
    @Schema(description = "The amount of money discounted")
    private Integer discountPrice;

    @Column
    private Integer discountId;

    @Column(length = 50)
    private String bankCode;

    @Column(length = 12, nullable = false)
    private String commonId;

    @Column(length = 50)
    private String nation;

    @Column(length = 255)
    private String address;

    @Column(length = 20)
    private String phoneNum;

    @Column
    private Boolean isReturn;

    @Column
    private Boolean isFinished;
}
