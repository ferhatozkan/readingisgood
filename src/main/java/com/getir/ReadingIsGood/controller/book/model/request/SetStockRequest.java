package com.getir.ReadingIsGood.controller.book.model.request;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SetStockRequest {
    @NotNull(message = "stock cant be null")
    @Min(value = 0, message = "stock must be minimum 0")
    private Integer stock;
}
