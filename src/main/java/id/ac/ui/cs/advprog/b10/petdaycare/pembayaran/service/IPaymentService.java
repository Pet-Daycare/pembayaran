package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.service;

import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.Coupon;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.Customer;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.Order;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.PetDetails;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.dto.PaymentDto;

import java.util.List;

public interface IPaymentService {
    List<Coupon> allCoupon();
    List<Customer> allCustomer();
    List<Order> allOrder();

    List<PetDetails> allPets();
    Order pay(PaymentDto paymentDto) throws InterruptedException;
}
