package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.repository.payment;

import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model.payment.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


public interface BillRepository extends JpaRepository<Bill, Integer> {
    Optional<Bill> findById(String id);
    List<Bill> findAll();

    @Transactional
    List<Bill> findAllByVerified(boolean condition);
}
