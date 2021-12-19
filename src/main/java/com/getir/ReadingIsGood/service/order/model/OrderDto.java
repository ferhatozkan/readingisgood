package com.getir.ReadingIsGood.service.order.model;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDto {
    private int customerId;
    private double totalPrice;
    private Date createdOn;
}

