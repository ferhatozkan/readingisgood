package com.getir.ReadingIsGood.controller.customer.model.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookResponse {
    private String name;
    private int count;
    private double price;
}
