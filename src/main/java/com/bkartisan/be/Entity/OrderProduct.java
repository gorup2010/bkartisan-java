package com.bkartisan.be.Entity;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// TODO: Refactor the table into product_order table instead of carts table. Change order_id to common_id.
@Entity
@Table(name = "carts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Cart stored in database. Act like a order_product table in database")
public class OrderProduct implements Serializable {
    @Id
    @Column()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private Integer productId;
    @Column
    private Integer quantity;
    @Column
    private String note;
    @Column(nullable = true, unique = true)
    private String buyer;
    @Column
    private Integer discountId;
    @Column(length = 12, name = "orderId")
    @Schema(description = "Note that this column refer to the common_id column in order table")
    private String commonId;
}
