package com.getir.ReadingIsGood.service;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GenericResponse<T> extends Response{
    private T Data;
}
