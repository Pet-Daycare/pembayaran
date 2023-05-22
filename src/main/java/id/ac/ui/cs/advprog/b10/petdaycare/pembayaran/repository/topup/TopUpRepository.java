package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.repository.topup;

import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model.Customer;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model.topup.TopUp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;

public interface TopUpRepository extends JpaRepository<TopUp, Integer> {

//    List<TopUp> findAllByUserId(Integer userId);
//    @NonNull
//    Optional<TopUp> findById(@NonNull Integer id);

    Optional<TopUp> findToUpById( String id);

    List<TopUp> findAllByValidate(boolean condtion);

}
