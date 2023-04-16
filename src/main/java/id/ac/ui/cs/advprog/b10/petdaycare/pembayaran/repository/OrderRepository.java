package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.repository;

import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.Order;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepository extends BaseRepository<Order>{
    @Override
    public void add(Order object) {
        object.setId(generateCode());
        map.put(object.getId(), object);
    }

    @Override
    public Order get(String id) throws InterruptedException {
        return map.get(id);
    }
}
