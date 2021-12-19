package com.getir.ReadingIsGood.controller.order.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookItemViewModel {
    private int bookId;
    private int count;
}
