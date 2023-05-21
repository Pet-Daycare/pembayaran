package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.controller;


import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.service.AuthService;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.dto.AuthTransactionDto;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.repository.payment.CouponRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;




import org.springframework.web.client.RestTemplate;
@RestController
@RequestMapping(path = {"api/auth"})
//@JsonSerialize
public class AuthController {
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/verify-token/{token}")
    public ResponseEntity<AuthTransactionDto> verifyToken(@PathVariable String token, HttpServletRequest request) {
        return ResponseEntity.ok(authService.verifyToken(token, request));
    }
}
