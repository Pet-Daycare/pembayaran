package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.controller;

import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.dto.topup.TopUpRequest;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model.topup.TopUp;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.service.topup.TopUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = {"api/customer/topup"})
@RequiredArgsConstructor
public class TopupController {

    private final TopUpService topUpServcie;

    @PostMapping("/create")
    public ResponseEntity<TopUp> createTopUpRequest(@RequestBody TopUpRequest request){
        return ResponseEntity.ok(topUpServcie.createTopUpRequest(request));
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<TopUp> getDetailTopUp(@PathVariable String id){
        return ResponseEntity.ok(topUpServcie.getDetailTopUp(id));
    }

    @PutMapping("/detail/aproval/{id}")
    public ResponseEntity<String> aprovalTopUp(@PathVariable String id){
        return ResponseEntity.ok(topUpServcie.aprovalTopUp(id));
    }

    @GetMapping("/detail/not-aproval/")
    public ResponseEntity<List<TopUp>> getAllNotAprove(){
        return ResponseEntity.ok(topUpServcie.getAllNotAprove());
    }


}
