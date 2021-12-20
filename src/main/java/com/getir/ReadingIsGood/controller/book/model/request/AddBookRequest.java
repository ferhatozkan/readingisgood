package com.getir.ReadingIsGood.controller.book.model.request;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddBookRequest {
    @NotBlank(message = "name cant be empty")
    private String name;
    @Min(value = 0, message = "stock must be minimum 0")
    private int stock;
    @Min(value = 0, message = "price must be minimum 0")
    private double price;
}
