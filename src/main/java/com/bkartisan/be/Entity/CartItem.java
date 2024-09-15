package com.bkartisan.be.Entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CartItem implements Serializable {
    private String note;
    private Integer quantity;
}
