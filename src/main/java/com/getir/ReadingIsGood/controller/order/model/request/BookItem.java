package com.getir.ReadingIsGood.controller.order.model.request;

import lombok.*;

import javax.validation.constraints.Min;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookItem {
    @Min(value = 1, message = "bookId must be minimum 1")
    private int bookId;
    @Min(value = 1, message = "count must be minimum 1")
    private int count;
}
