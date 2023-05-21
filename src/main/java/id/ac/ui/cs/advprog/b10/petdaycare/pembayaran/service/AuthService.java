package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.service;

import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.dto.AuthTransactionDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AuthService {

//    String verifyToken(String token, HttpServletRequest request);
    AuthTransactionDto verifyToken(String token, HttpServletRequest request);

}
