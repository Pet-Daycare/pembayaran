package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.controller;

import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.dto.topup.CustomerRequest;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model.Customer;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model.topup.TopUp;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.service.customer.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = {"api/customer/"})
@RequiredArgsConstructor

public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("detail/{username}")
    public ResponseEntity<Customer> detailCustomer(@PathVariable("username") String username){
        Customer response = customerService.findCustomer(username);
        return ResponseEntity.ok(response);
    }

    @GetMapping("detail/trasaction/{username}")
    public ResponseEntity<List<TopUp>> historyTopUp(@PathVariable("username") String username){
        List<TopUp> response = customerService.historyTopUp(username);
        return ResponseEntity.ok(response);
    }


    @PostMapping("/add")
    public ResponseEntity<Customer> addCustomer(@RequestBody CustomerRequest request){
        return ResponseEntity.ok(customerService.createCustomer(request));
    }
}
