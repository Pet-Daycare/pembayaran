package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.repository;

import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.Coupon;
import org.springframework.stereotype.Repository;

@Repository
public class CouponRepository extends BaseRepository<Coupon>{

    @Override
    public void add(Coupon object) {
        object.setId(generateCode());
        map.put(object.getId(), object);
    }

    @Override
    public Coupon get(String id) throws InterruptedException {
        return map.get(id);
    }
}
