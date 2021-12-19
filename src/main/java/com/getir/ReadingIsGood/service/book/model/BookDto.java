package com.getir.ReadingIsGood.service.book.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDto {
    private String name;
    private int stock;
    private double price;
}
