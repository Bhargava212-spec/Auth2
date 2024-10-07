package com.user.oauth.Auth2.core.repository;

import com.user.oauth.Auth2.core.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsernameAndEnabled(String userName, boolean enabled);

    User findByUsername(String userName);

    User findByEmail(String email);
}
