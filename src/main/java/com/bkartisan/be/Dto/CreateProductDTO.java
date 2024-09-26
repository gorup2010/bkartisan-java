package com.bkartisan.be.Dto;

import org.springframework.web.multipart.MultipartFile;

public record CreateProductDTO(
    String name,
    Integer price,
    Integer quantity,
    String material,
    Integer category,
    String type,
    String description,
    String introduction,
    MultipartFile[] images,
    String option1,
    String[] chooseOptions1,
    String option2,
    String[] chooseOptions2,
    MultipartFile[] videos
) {
}