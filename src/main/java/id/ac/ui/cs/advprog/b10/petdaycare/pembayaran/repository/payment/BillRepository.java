package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.repository.payment;

import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model.payment.Bill;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

public interface BillRepository extends JpaRepository<Bill, Integer> {
    @NonNull
    Bill findBillById(@NonNull Integer id);
    List<Bill> findAll();

    @Transactional
    List<Bill> findAllByVerified(boolean condition);

    List<Bill> findAllByUsername(String john);
}
