package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.controller;

import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.dto.topup.TopUpRequest;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model.AprovalTopUpResponse;
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

    private final TopUpService topUpService;

    @PostMapping("/create")
    public ResponseEntity<TopUp> createTopUpRequest(@RequestBody TopUpRequest request){
        return ResponseEntity.ok(topUpService.createTopUpRequest(request));
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<TopUp> getDetailTopUp(@PathVariable String id){
        return ResponseEntity.ok(topUpService.getDetailTopUp(id));
    }

    @GetMapping("/detail/all")
    public ResponseEntity<List<TopUp>> getDetailTopUp(){
        return ResponseEntity.ok(topUpService.getAllTopup());
    }
    @PutMapping("/detail/aproval/{id}")
    public ResponseEntity<AprovalTopUpResponse> aprovalTopUp(@PathVariable String id){
        return ResponseEntity.ok(topUpService.approvalTopUp(id));
    }

    @GetMapping("/detail/not-aproval/")
    public ResponseEntity<List<TopUp>> getAllNotAprove(){
        return ResponseEntity.ok(topUpService.getAllNotApprove());
    }
    @GetMapping("/detail/aproval/")
    public ResponseEntity<List<TopUp>> getAllApprove(){
        return ResponseEntity.ok(topUpService.getAllApprove());
    }


}
