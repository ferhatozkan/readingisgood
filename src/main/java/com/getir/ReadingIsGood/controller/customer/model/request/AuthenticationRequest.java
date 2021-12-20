package com.getir.ReadingIsGood.controller.customer.model.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthenticationRequest {
    private String password;
    private String email;
}
