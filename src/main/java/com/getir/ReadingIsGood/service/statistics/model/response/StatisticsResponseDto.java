package com.getir.ReadingIsGood.service.statistics.model.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StatisticsResponseDto {
    private String month;
    private int totalOrderCount;
    private int totalBookCount;
    private double totalPurchasedAmount;
}
