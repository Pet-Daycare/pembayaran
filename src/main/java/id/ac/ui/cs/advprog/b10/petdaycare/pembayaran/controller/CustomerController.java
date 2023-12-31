package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.controller;

import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.dto.topup.CustomerRequest;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model.Customer;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model.topup.TopUp;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.service.customer.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = {"api/customer/"})
@RequiredArgsConstructor

public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("detail")
    public ResponseEntity<Customer> detailCustomer(@RequestBody CustomerRequest request){
        Customer response = customerService.findCustomer(request.getUsername());
        return ResponseEntity.ok(response);
    }

    @GetMapping("detail/{username}")
    public ResponseEntity<Customer> detailCustomer(@PathVariable("username") String username){
        try{
            Customer response = customerService.findCustomer(username);

            return ResponseEntity.ok(response);

        } catch (Exception err){
            HttpHeaders headers = new HttpHeaders();
            headers.add("decs", "Error Desc");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

    }

    @GetMapping("detail/trasaction")
    public ResponseEntity<List<TopUp>> historyTopUp(@RequestBody CustomerRequest request){
        List<TopUp> response = customerService.historyTopUp(request.getUsername());
        return ResponseEntity.ok(response);
    }

    @GetMapping("detail/frontend")
    public ResponseEntity<List<TopUp>> historyTopUp(@RequestParam String username){
        List<TopUp> response = customerService.historyTopUp(username);
        return ResponseEntity.ok(response);
    }

    @GetMapping("frontend")
    public ResponseEntity<Customer> detailCustomerFrontend(@RequestParam String username, @RequestParam String token){
        Customer response = customerService.getCustomerFrontend(username, token);
        return ResponseEntity.ok(response);
    }

    @PostMapping("add")
    public ResponseEntity<Customer> addCustomer(@RequestBody CustomerRequest request){
        return ResponseEntity.ok(customerService.createCustomer(request));
    }
}
