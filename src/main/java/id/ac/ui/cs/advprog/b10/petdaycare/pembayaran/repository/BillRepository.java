package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.repository;

import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


public interface BillRepository extends JpaRepository<Bill, Integer> {
    Optional<Bill> findById(String id);
    List<Bill> findAll();

    @Transactional
    List<Bill> findAllByVerified(boolean condition);
}
