package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

// dummy page for testing

@Controller
public class TestController {

    @GetMapping("/")
    public String test() {
        return "index";
    }

}
