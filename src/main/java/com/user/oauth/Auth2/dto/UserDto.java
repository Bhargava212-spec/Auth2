package com.user.oauth.Auth2.dto;

import lombok.Data;

import java.util.Date;

@Data
public class UserDto {

    private String userName;
    private String password;
    private String email;
    private Date createdDate;
    private Date lastUpdatedDate;

}
