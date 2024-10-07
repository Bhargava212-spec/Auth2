package com.user.oauth.Auth2.service;

import com.user.oauth.Auth2.dto.UserDto;
import com.user.oauth.Auth2.dto.UserRequest;

public interface UserService {

    String createUser(UserRequest userDto);

    String updateUser(UserDto userDto);

    UserDto getUserDetails(String userName);

    String deleteUser(String userName);
}
