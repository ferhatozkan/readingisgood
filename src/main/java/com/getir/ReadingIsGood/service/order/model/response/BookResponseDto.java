package com.getir.ReadingIsGood.service.order.model.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookResponseDto {
    private String name;
    private int count;
    private double price;
}
