package com.getir.ReadingIsGood.controller.customer.model.request;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddCustomerRequest {
    @NotBlank(message = "name cant be  empty")
    private String name;
    @NotBlank(message = "email cant be empty")
    private String email;
    @NotBlank(message = "password cant be empty")
    private String password;
    @Min(value = 1, message = "age must be minimum 1")
    private int age;
}
