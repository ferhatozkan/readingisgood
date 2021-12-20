package com.getir.ReadingIsGood.repository.statistics;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Statistic {
    private Double totalAmount;
    private String monthName;
}
