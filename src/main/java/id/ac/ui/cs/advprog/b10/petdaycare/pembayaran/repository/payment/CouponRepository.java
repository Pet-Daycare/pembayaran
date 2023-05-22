package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.repository.payment;

import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model.payment.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CouponRepository extends JpaRepository<Coupon, Integer> {
    Coupon findByCode(String code);
    boolean existsByCode(String code);


}
