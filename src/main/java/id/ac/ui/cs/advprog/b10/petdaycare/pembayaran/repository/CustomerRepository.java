package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.repository;

import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    @NonNull
    List<Customer> findAll();

    Optional<Customer> findById(@NonNull Integer id);

    Optional<Customer> findCustomersByusername( String username);

}
