package com.getir.ReadingIsGood.controller.statistics.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StatisticsResponse {
    private String month;
    private int totalOrderCount;
    private int totalBookCount;
    private double totalPurchasedAmount;
}
