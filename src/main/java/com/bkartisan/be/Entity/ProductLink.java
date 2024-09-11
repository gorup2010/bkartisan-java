package com.bkartisan.be.Entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "product_links")
@IdClass(ProductLinkId.class)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductLink {
    @Id
    @Column()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer imageId;

    @Id
    @Column()
    private String link;

    @Column()
    private String type;

    @Column(insertable = false, updatable = false)
    private LocalDateTime createdAt;

    @ManyToOne()
    @JoinColumn(name = "productId")
    @JsonIgnore
    private Product product;
}


@NoArgsConstructor
@EqualsAndHashCode
class ProductLinkId implements Serializable {
    public Integer imageId;
    public String link;
    
    public ProductLinkId(Integer imageId, String link) {
        this.imageId = imageId;
        this.link = link;
    }
}