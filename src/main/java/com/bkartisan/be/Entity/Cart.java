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
@Schema(description = "Cart stored in database. Act like a product_order table in database")
public class Cart implements Serializable {
    @Id
    @Column()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private Integer productId;
    @Column
    private Integer quantity;
    @Column(nullable = true, unique = true)
    private String buyer;
    @Column
    private Integer discountId;
    @Column(length = 12)
    @Schema(description = "Note that this column refer to the common_id column in order table")
    private String orderId;
    @Column(length = 10)
    private String parentId;
}
