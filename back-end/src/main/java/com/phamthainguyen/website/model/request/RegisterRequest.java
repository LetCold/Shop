package com.phamthainguyen.website.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    
    private String firstName;

    private String lastName;

    private byte gender;

    private String email;

    private String password;
}
