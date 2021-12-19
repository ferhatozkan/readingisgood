package com.getir.ReadingIsGood.service.order.model.response;


import lombok.*;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderResponseDto {
    private double totalPrice;
    private ArrayList<BookResponseDto> books;
}
