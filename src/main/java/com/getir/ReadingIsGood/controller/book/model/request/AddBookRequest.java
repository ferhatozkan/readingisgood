package com.getir.ReadingIsGood.controller.book.model.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddBookRequest {
    private String name;
    private int stock;
    private double price;
}
