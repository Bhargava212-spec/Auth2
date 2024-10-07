package com.user.oauth.Auth2.api;

import com.user.oauth.Auth2.dto.UserDto;
import com.user.oauth.Auth2.dto.UserRequest;
import com.user.oauth.Auth2.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/user")
@Tag(name = "User Operations", description = "Operations related to users")
public class UserController {

    @Autowired
    private UserService userService;

//    @Autowired
//    private RateLimiterService rateLimiterService;

    @PostMapping("/create-user")
//    @RateLimiter(name = "myRateLimiter", fallbackMethod = "rateLimiterFallback")
    @Operation(summary = "create user")
    public ResponseEntity<String> createUser(@RequestBody UserRequest userDto) {
        return ResponseEntity.ok(userService.createUser(userDto));
    }

    public ResponseEntity<String> rateLimiterFallback(UserDto dto, Throwable t) {
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body("Too many requests - Try after sometime");
    }

    @PutMapping("/update-user")
    public ResponseEntity<String> updateUser(@RequestBody UserDto userDto) {
//        if (!rateLimiterService.isRequestAllowed(userDto.getUserName())) {
//            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body("Too many requests - Try after sometime");
//        }
        return ResponseEntity.ok(userService.updateUser(userDto));
    }

    @GetMapping("/get-user-details/{userName}")
    @Operation(
            security = @SecurityRequirement(name = "bearerAuth")
    )
    public UserDto getUserDetails(@PathVariable("userName") String userName) {
        return userService.getUserDetails(userName);
    }

    @DeleteMapping("/delete-user/{userName}")
    public String deleteUser(@PathVariable("userName") String userName) {
        return userService.deleteUser(userName);
    }
}
