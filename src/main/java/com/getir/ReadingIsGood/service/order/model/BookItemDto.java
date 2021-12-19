package com.getir.ReadingIsGood.service.order.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookItemDto {
    private int bookId;
    private int count;
}
