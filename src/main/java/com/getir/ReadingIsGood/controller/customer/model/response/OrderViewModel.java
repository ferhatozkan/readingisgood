package com.getir.ReadingIsGood.controller.customer.model.response;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderViewModel {
    private int customerId;
    private Date createdOn;
}
