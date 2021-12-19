package com.getir.ReadingIsGood.service.order.model.request;

import com.getir.ReadingIsGood.service.order.model.BookItemDto;
import lombok.*;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddOrderDto {
    private int customerId;
    private ArrayList<BookItemDto> books;
}
