package com.user.oauth.Auth2.dto;

import lombok.Data;

@Data
public class UserRequest {

    private String userName;
    private String password;
    private String email;
}
