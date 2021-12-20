package com.getir.ReadingIsGood.controller.customer.model.response;

import lombok.*;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderResponse {
    private double totalPrice;
    private ArrayList<BookResponse> books;
}
