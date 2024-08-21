package com.bkartisan.be.Entity;

import java.time.LocalDateTime;

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

@Entity
@Table(name = "product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @Column()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;

    @Column()
    private String name;

    @Column()
    private Integer price;

    @Column()
    private String description;

    @Column()
    private String category;

    @Column()
    private String material;

    @Column()
    private String quantity;

    @Column()
    private Boolean isOnSale;

    @Column()
    private String seller;

    @Column()
    private LocalDateTime createdAt;

    @Column()
    private LocalDateTime approvedAt;

    @Column(nullable = true)
    private String approver;

    @Column()
    private String status;

    @Column()
    private String coverImage;

    @Column()
    private Integer discount;

    @Column()
    private Integer numberOfStar;

    @Column()
    private Integer numberOfRating;

    @Column()
    private Boolean approvedByAI;

    @Column()
    private String introduction;

    @Column()
    private String type;
}
