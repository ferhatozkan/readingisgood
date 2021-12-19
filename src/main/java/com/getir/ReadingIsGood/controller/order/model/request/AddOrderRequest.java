package com.getir.ReadingIsGood.controller.order.model.request;

import com.getir.ReadingIsGood.controller.order.model.BookItemViewModel;
import lombok.*;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddOrderRequest {
    private int customerId;
    private ArrayList<BookItemViewModel> books;
}
