package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.controller;

import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.Coupon;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.repository.CouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(path = {"coupon","coupon/"})
public class CouponController {

    private final CouponRepository couponRepository;

    @Autowired
    CouponController(CouponRepository couponRepository) {
        this.couponRepository = couponRepository;
    }


    @GetMapping("")
    public String couponForm(Model model) {
        return "coupon_form";
    }

    @PostMapping("")
    public String addCoupon(Model model, @RequestParam() double discount) {
        couponRepository.add(new Coupon(discount));
        return "redirect:/order/pay";
    }

}
