package com.getir.ReadingIsGood.service.customer.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerDto {
    private String name;
    private String email;
    private String password;
    private int age;
}
