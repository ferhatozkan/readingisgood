package com.getir.ReadingIsGood.controller.customer.model.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddCustomerRequest {
    private String name;
    private String email;
    private String password;
    private int age;
}
