package com.user.oauth.Auth2.dto;

import com.user.oauth.Auth2.core.User;
import lombok.Data;

import java.util.Date;

@Data
public class UserEvent {

        private User user;
        private EventType eventType;
        private Date date;
}
