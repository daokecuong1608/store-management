package com.sapo.store_management.controller;


import com.sapo.store_management.dto.JwtResponse;
import com.sapo.store_management.dto.LoginRequest;
import com.sapo.store_management.dto.UserRequest;
import com.sapo.store_management.model.User;
import com.sapo.store_management.service.JWTService;
import com.sapo.store_management.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTService jwtService;

    /**
     * Authenticates a user and generates a JWT token upon successful authentication.
     *
     * @param loginRequest The login request containing username and password.
     * @return A {@link ResponseEntity} containing the JWT token if authentication is successful,
     * or an error message if authentication fails.
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {
        System.out.println("Tên đăng nhập : " + loginRequest.getUsername() + " và mật khẩu : " + loginRequest.getPassword());
        try {
            // Authenticate the user with the provided username and password
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
            if (authentication.isAuthenticated()) {
                final String accessToken = jwtService.generateAccessToken(loginRequest.getUsername());
                final String refreshToken = jwtService.generateRefreshToken(loginRequest.getUsername());
                User user = userService.findByUsername(loginRequest.getUsername());
                SecurityContextHolder.getContext().setAuthentication(authentication);
                return ResponseEntity.ok(new JwtResponse(accessToken, refreshToken, user.getId(), user.getUsername(), user.getFullname()));
            }
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("ncorrect username or password");
        }
        return ResponseEntity.badRequest().body("Xác thực không thành công");
    }


    @PostMapping("/refresh-token")
    public ResponseEntity<String> refreshToken(@RequestBody Map<String, String> requestBody) {
        try {
            String refreshToken = requestBody.get("refreshToken");
            System.out.println("Refresh token: " + refreshToken);
            String username = jwtService.extractUsername(refreshToken);
            UserDetails userDetails = userService.loadUserByUsername(username);
            if (userDetails == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username");
            }
            String newAccessToken = jwtService.validateAndGenerateAccessToken(refreshToken, userDetails);
            return ResponseEntity.ok(newAccessToken);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or expired refresh token");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserRequest userRequest) {
        System.out.println("UserRequest : " + userRequest.toString());
        try {
            userService.registerUser(userRequest);
            return ResponseEntity.ok(Map.of("message", "User registered successfully"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "An unexpected error occurred during registration."));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable int id ,@RequestBody UserRequest userRequest){
        try {
            userService.updateUser(id, userRequest);
            return ResponseEntity.ok(Map.of("message", "User updated successfully"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                   .body(Map.of("error", "An unexpected error occurred during user update."));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id){
        try {
            userService.deleteUser(id);
            return ResponseEntity.ok(Map.of("message", "User deleted successfully"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                   .body(Map.of("error", "An unexpected error occurred during user deletion."));
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllUsers(){
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

}
