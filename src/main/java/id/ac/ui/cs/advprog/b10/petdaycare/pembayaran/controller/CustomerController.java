package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.controller;

import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.Customer;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(path = {"customer","customer/"})
public class CustomerController {

    private final CustomerRepository customerRepository;

    @Autowired
    CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    @GetMapping("")
    public String customerForm(Model model) {
        return "customer_form";
    }

    @PostMapping("")
    public String addCustomer(Model model, @RequestParam() String name, @RequestParam double balance) {
        customerRepository.add(new Customer(name,balance));
        return "redirect:/order/pay";
    }

}
