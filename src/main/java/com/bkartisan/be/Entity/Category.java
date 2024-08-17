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
import lombok.Setter;

@Entity
@Table(name = "category")
@Getter
@Setter
@AllArgsConstructor
public class Category {
    @Id
    @Column()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;
    @Column()
    private String name;
    @Column
    private Integer categoryParent;
    @Column
    private Integer level;
    @Column
    private String image;
    @Column
    private Boolean isSelected;

    public Category() {
    }
}
