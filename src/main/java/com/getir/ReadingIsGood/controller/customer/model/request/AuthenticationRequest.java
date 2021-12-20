package com.getir.ReadingIsGood.controller.customer.model.request;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthenticationRequest {
    @NotBlank(message = "password must not be empty")
    private String password;
    @NotBlank(message = "email must not be empty")
    private String email;
}
