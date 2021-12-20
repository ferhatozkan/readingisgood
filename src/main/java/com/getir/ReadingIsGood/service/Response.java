package com.getir.ReadingIsGood.service;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Response {
    private String message;
    private boolean isSuccess;
}
