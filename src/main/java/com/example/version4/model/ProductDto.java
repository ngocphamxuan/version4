package com.example.version4.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private Long productId;
    private String name;
    private Integer quantity;
    private String image;
    private MultipartFile imageFile;
    private Float unitPrice;
    private String description;
    private Date enteredDate;
    private Float discount;
    private Integer status;
    private Long categoryId;
    private Boolean isEdit;
}
