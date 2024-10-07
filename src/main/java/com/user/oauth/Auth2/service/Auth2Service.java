package com.user.oauth.Auth2.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface Auth2Service {

    String generateToken(UserDetails userDetails);
}
