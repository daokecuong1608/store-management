package com.sapo.store_management.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
@CrossOrigin(origins = "*")
public class RoleTestController {

    // API chỉ dành cho Admin
    @GetMapping("/admin")
    public String adminAccess() {
        return "Hello Admin! You have access to this endpoint.";
    }

    // API chỉ dành cho Staff
    @GetMapping("/staff")
    public String staffAccess() {
        return "Hello Staff! You have access to this endpoint.";
    }
    @GetMapping("/csr")
    public String csrAccess() {
        return "Hello CSR! You have access to this endpoint.";
    }

}
