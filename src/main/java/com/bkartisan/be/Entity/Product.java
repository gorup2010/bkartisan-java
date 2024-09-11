package com.bkartisan.be.Entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
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
    private Integer category;

    @Column()
    private String material;

    @Column()
    private String quantity;

    @Column()
    private Boolean isOnSale;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller")
    private User seller;

    @Column(insertable = false, updatable = false)
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

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<ProductLink> assets;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<Comment> comments;

}
