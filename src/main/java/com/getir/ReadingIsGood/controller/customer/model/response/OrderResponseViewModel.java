package com.getir.ReadingIsGood.controller.customer.model.response;

import lombok.*;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderResponseViewModel {
    private double totalPrice;
    private ArrayList<BookResponseViewModel> books;
}
