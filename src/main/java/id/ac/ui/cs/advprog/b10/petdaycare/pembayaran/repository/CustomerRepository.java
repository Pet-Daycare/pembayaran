package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.repository;

import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.Customer;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerRepository extends BaseRepository<Customer>{

    @Override
    public void add(Customer object) {
        object.setId(generateCode());
        map.put(object.getId(), object);
    }

    @Override
    public Customer get(String id) throws InterruptedException {
        return map.get(id);
    }
}