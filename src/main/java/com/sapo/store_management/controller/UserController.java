package com.sapo.store_management.controller;


import com.sapo.store_management.dto.JwtResponse;
import com.sapo.store_management.dto.LoginRequest;
import com.sapo.store_management.service.JWTService;
import com.sapo.store_management.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
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
     *         or an error message if authentication fails.
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {
        System.out.println("Tên đăng nhập : " + loginRequest.getUsername() + " và mật khẩu : " + loginRequest.getPassword());
        try {
            // Authenticate the user with the provided username and password
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
            if (authentication.isAuthenticated()) {
                final String jwt = jwtService.generateToken(loginRequest.getUsername());
                SecurityContextHolder.getContext().setAuthentication(authentication);
                return ResponseEntity.ok(new JwtResponse(jwt));
            }
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("ncorrect username or password");
        }
        return ResponseEntity.badRequest().body("Xác thực không thành công");

    }

}
