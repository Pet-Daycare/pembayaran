package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.repository;


import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.PetDetails;
import org.springframework.stereotype.Repository;

@Repository
public class PetDetailsRepository extends BaseRepository<PetDetails>{

    @Override
    public void add(PetDetails object) {
        object.setId(generateCode());
        map.put(object.getId(), object);
    }

    @Override
    public PetDetails get(String id) throws InterruptedException {
        return map.get(id);
    }
}
