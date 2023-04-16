package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.service;

import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.Coupon;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.Customer;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.Order;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.*;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.dto.PaymentDto;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class PaymentService implements IPaymentService{

    private final CouponRepository couponRepository;
    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;
    private final PetDetailsRepository petDetailsRepository;


    @Autowired
    PaymentService(
                   CouponRepository couponRepository,
                   CustomerRepository customerRepository,
                   OrderRepository orderRepository,
                   PetDetailsRepository petDetailsRepository

    ) {
        this.couponRepository = couponRepository;
        this.customerRepository = customerRepository;
        this.orderRepository = orderRepository;
        this.petDetailsRepository = petDetailsRepository;
    }


    @Override
    public List<Coupon> allCoupon() {
        return couponRepository.all();
    }

    @Override
    public List<Customer> allCustomer() {
        return customerRepository.all();
    }

    @Override
    public List<Order> allOrder() {
        return orderRepository.all();
    }
    @Override
    public List<PetDetails> allPets() {
        return petDetailsRepository.all();
    }


    @Override
    @Async
    public Order pay(PaymentDto paymentDto) throws InterruptedException {
        CompletableFuture<Customer> futureCust = CompletableFuture.supplyAsync(
                () -> {
                    try {
                        return customerRepository.get(paymentDto.getCustomerId());
                    } catch (InterruptedException e){
                        throw new RuntimeException();
                    }
                }
        );
        CompletableFuture<PetDetails> futurePets = CompletableFuture.supplyAsync(
                () -> {
                    try {
                        return petDetailsRepository.get(paymentDto.getPetId());
                    } catch (InterruptedException e){
                        throw new RuntimeException();
                    }
                }
        );
        CompletableFuture<Coupon> futureCoupon = CompletableFuture.supplyAsync(
                () -> {
                    try {
                        return couponRepository.get(paymentDto.getCouponId());
                    } catch (InterruptedException e){
                        throw new RuntimeException();
                    }
                }
        );

        Coupon coupon = futureCoupon.join();
        PetDetails pet = futurePets.join();
        Customer customer =futureCust.join();

        CompletableFuture.runAsync(() -> {
            try{
                reduceCustomerBalance(customer,pet,coupon);
            } catch (InterruptedException e){
                throw new RuntimeException();
            }
        });
        return new Order(customer.getName(),pet.getPetName(),coupon.getDiscount());
    }

    private void reduceCustomerBalance(Customer customer, PetDetails petDetails, Coupon coupon) throws InterruptedException {
        double price = petDetails.getTotalPrice();
        double discountedPrice = coupon.redeem(price);
        boolean couponIsUsed = discountedPrice == -1 ;

        setCustBalance(customer, petDetails, coupon, price, discountedPrice, couponIsUsed);

    }

    private synchronized void setCustBalance(Customer customer, PetDetails pet, Coupon coupon, double foodPrice, double discountedPrice, boolean couponIsUsed) throws InterruptedException {
        if (!couponIsUsed) {
            customer.setBalance(customer.getBalance() - discountedPrice);
        }
        else {
            customer.setBalance(customer.getBalance() - foodPrice);
        }
    }
}
