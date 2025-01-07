package com.sapo.store_management.service;

import com.sapo.store_management.dto.UserRequest;
import com.sapo.store_management.model.User;
import com.sapo.store_management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Couldn't find'"));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                List.of(new SimpleGrantedAuthority(user.getRole()))
        );
    }

    public void registerUser(UserRequest userRequest) {
        if (userRepository.existsByUsername(userRequest.getUsername())) {
            throw new IllegalArgumentException("Username already exists");
        }
        User newUser = new User();
        newUser.setUsername(userRequest.getUsername());
        newUser.setPassword(encodePassword(userRequest.getPassword())); // Mã hóa mật khẩu
        newUser.setFullname(userRequest.getFullname());
        newUser.setRole(userRequest.getRole() != null ? userRequest.getRole() : "ROLE_STAFF"); // Mặc định ROLE_STAFF nếu null
        newUser.setAge(userRequest.getAge());

        // Lưu người dùng vào cơ sở dữ liệu
        userRepository.save(newUser);
    }

    public String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

}

