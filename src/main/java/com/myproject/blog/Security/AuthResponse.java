package com.myproject.blog.Security;

import java.sql.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AuthResponse {
    private String email;
    private String accessToken;
    private Date tokenExpireTime;
     
    public AuthResponse(String email, String accessToken, Date tokenExpireTime) {
        this.email = email;
        this.accessToken = accessToken;
        this.tokenExpireTime=tokenExpireTime;
    }

}