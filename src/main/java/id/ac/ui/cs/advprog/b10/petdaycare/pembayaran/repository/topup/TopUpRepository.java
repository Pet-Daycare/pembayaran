package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.repository.topup;

import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model.topup.TopUp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TopUpRepository extends JpaRepository<TopUp, Integer> {
    Optional<TopUp> findToUpById( String id);

    List<TopUp> findAllByValidate(boolean condtion);

}
