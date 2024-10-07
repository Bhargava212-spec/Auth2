package com.user.oauth.Auth2.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class TokenRequest {
    private String username;
    private String password;
}
