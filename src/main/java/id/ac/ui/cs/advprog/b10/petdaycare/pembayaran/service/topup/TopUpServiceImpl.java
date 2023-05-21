package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.service.topup;

import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.dto.topup.TopUpRequest;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.exception.InvalidInputException;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model.Customer;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model.topup.TopUp;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model.topup.TopUpMethod;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.repository.topup.TopUpRepository;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.service.customer.CustomerService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class TopUpServiceImpl implements TopUpService{
    @Autowired
    private TopUpRepository topUpRepository;

    @Autowired
    private CustomerService customerService;

    @Override
    public TopUp createTopUpRequest(TopUpRequest request){
        if(request.getUsername() == null) throw new InvalidInputException();
        if(request.getNominal() <= 0) throw new InvalidInputException();
        if(request.getTypeMethod() == null) throw new InvalidInputException();

        Customer findCustomer = customerService.findCustomer(request.getUsername());
        if(findCustomer == null){
            return null;
        } else {

            TopUp topUp = new TopUp();
            topUp.setUsername(findCustomer.getUsername());
            topUp.setTypeMethod(TopUpMethod.valueOf(request.getTypeMethod()));
            topUp.setTimeTaken(new SimpleDateFormat("yyy-MM-dd HH:mm:ss").format(new Date()).toLowerCase());
            topUp.setNominal(request.getNominal());
            topUp.setValidate(false);
            topUpRepository.save(topUp);
            addTopUpToCustomer(findCustomer, topUp);
            return topUp;
        }
    }

    @Override
    public TopUp getDetailTopUp(String id){
        return findTopUpId(id);
    }

    @Override
    public String aprovalTopUp(String id){
        TopUp topUpAprove = findTopUpId(id);
        topUpAprove.setValidate(true);
        customerService.addBalance(topUpAprove.getUsername(), topUpAprove.getNominal());
        return String.format("Success TopUp with ID: %s ", id);
    }

    @Override
    public List<TopUp> getAllNotAprove() {
        return topUpRepository.findAllByValidate(false);
    }


    public TopUp findTopUpId(String id){
        if(topUpRepository.findToUpById(id).isEmpty()){
            return null;
        }
        return topUpRepository.findToUpById(id).get();
    }

    public boolean addTopUpToCustomer(Customer customer, TopUp topUp){
        List<TopUp> customerListTopUp = null;
        if(customer.getTopUpList() != null){
            customerListTopUp = customer.getTopUpList();
        } else{
            customer.setTopUpList(Collections.singletonList(topUp));
            return true;
        }
        customerListTopUp.add(topUp);
        customer.setTopUpList(customerListTopUp);
        return true;
    }
}
