package com.getir.ReadingIsGood.controller.order.model.request;

import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddOrderRequest {
    @Min(value = 1, message = "customerId must be minimum 1")
    private int customerId;
    @NotEmpty
    private List<@Valid BookItem> books;
}
