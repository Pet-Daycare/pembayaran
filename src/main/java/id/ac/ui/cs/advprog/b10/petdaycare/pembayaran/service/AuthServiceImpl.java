package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.service;


import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.dto.AuthTransactionDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthServiceImpl implements AuthService{

    private final RestTemplate restTemplate;

    @Autowired
    public AuthServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

//    @Override
//    public String verifyToken(String token, HttpServletRequest request) {
//        String otherInstanceURL = "http://localhost:8080/api/v1/auth/verify-token/"+token;
//        // Panggil service arti untuk mengecek id article (post) yang bersesuaian
//        String res = restTemplate.getForObject((otherInstanceURL), String.class);
//        return res;
//    }

    @Override
    public AuthTransactionDto verifyToken(String token, HttpServletRequest request) {
        String otherInstanceURL = "http://localhost:8080/api/v1/auth/verify-token/"+token;
        // Panggil service arti untuk mengecek id article (post) yang bersesuaian
        AuthTransactionDto res = restTemplate.getForObject((otherInstanceURL), AuthTransactionDto.class);
        return res;
    }
}
