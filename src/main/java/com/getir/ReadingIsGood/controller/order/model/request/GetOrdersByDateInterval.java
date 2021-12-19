package com.getir.ReadingIsGood.controller.order.model.request;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetOrdersByDateInterval {
    private Date startDate;
    private Date endDate;
}
