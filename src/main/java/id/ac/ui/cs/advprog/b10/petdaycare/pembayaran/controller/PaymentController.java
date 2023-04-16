package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.controller;

import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.Coupon;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.Order;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.dto.PaymentDto;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.repository.OrderRepository;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path={"payment","payment/"})
public class PaymentController {

    private final PaymentService paymentService;
    private final OrderRepository orderRepository;

    @Autowired
    PaymentController(PaymentService paymentService,
                      OrderRepository orderRepository) {
        this.paymentService = paymentService;
        this.orderRepository = orderRepository;
    }

    @GetMapping("")
    public String allPayment(Model model) {
        model.addAttribute("allPayment",paymentService.allOrder());
        return "all_payment";
    }


    @PostMapping(path={"pay","pay/"})
    public String pay(@RequestParam String foodId,
                      @RequestParam String couponId,
                      @RequestParam String customerId ) throws InterruptedException {
        PaymentDto paymentDto = new PaymentDto(couponId, customerId, foodId);
        long start = System.nanoTime();
        Order order = paymentService.pay(paymentDto);
        long end = System.nanoTime();

        order.setTimeTaken( (double) (end - start) / 1_000_000_000);
        orderRepository.add(order);

        return "redirect:/order";
    }

    @GetMapping(path={"pay","pay/"})
    public String payForm(Model model) {
        model.addAttribute("allPets",paymentService.allPets());
        model.addAttribute("allCustomer",paymentService.allCustomer());
        model.addAttribute("allCoupon",paymentService.allCoupon());

        return "payment_form";
    }

}
