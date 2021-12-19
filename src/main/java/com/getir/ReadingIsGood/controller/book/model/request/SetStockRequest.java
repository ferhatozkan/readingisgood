package com.getir.ReadingIsGood.controller.book.model.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SetStockRequest {
    private int stock;
}
