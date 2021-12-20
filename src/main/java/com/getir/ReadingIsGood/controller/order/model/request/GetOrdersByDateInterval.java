package com.getir.ReadingIsGood.controller.order.model.request;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetOrdersByDateInterval {
    @NotNull(message = "startDate must not be empty")
    private Date startDate;
    @NotNull(message = "endDate must not be empty")
    private Date endDate;
}
