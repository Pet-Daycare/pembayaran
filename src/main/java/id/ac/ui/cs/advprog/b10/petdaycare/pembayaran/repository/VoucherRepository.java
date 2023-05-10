package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.repository;

import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface VoucherRepository extends JpaRepository<Voucher, Integer> {
    Voucher findByCode(String code);
    boolean existsByCode(String code);


}
