package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.repository;

import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface CouponRepository extends JpaRepository<Coupon, Integer> {
    Coupon findByCode(String code);
    boolean existsByCode(String code);


}
