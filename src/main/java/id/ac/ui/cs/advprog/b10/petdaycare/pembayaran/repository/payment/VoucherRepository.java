package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.repository.payment;

import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model.payment.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;


public interface VoucherRepository extends JpaRepository<Voucher, Integer> {
    Voucher findByCode(String code);
    boolean existsByCode(String code);


}
