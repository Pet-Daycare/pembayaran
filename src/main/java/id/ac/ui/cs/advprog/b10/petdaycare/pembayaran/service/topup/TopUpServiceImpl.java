package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.service.topup;

import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.brand.TopUpCalulate;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.dto.topup.CustomerRequest;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.dto.topup.TopUpRequest;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.exception.InvalidInputException;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model.AprovalTopUpResponse;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model.Customer;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model.topup.TopUp;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model.topup.TopUpMethod;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.repository.topup.TopUpRepository;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model.topup.TopUpTypeBrand;
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
    private TopUpCalculateService serviceCalculate;

    @Override
    public TopUp createTopUpRequest(TopUpRequest request){
        if(request.getUsername() == null) throw new InvalidInputException();
        if(request.getToken() == null) throw new InvalidInputException();
        if(request.getNominal() <= 0) throw new InvalidInputException();
        if(request.getTypeMethod() == null) throw new InvalidInputException();

        Customer findCustomer = customerService.findCustomer(request.getUsername());
        if(findCustomer == null){
            findCustomer = customerService.createCustomer(new CustomerRequest(request.getUsername(), request.getToken()));
        }

        this.serviceCalculate = new TopUpCalculateServiceImpl();
        TopUpTypeBrand topUpTypeBrand =  TopUpTypeBrand.valueOf(TopUpTypeBrand.class, request.getTypeMethod());
        TopUpCalulate typeFee = serviceCalculate.createCalculateTopUp(topUpTypeBrand);

        TopUp topUp = new TopUp();
        topUp.setCustomerId(findCustomer.getCustomerId());
        topUp.setUsername(findCustomer.getUsername());
        topUp.setTypeMethod(topUpTypeBrand.toString());
        topUp.setTimeTaken(new SimpleDateFormat("yyy-MM-dd HH:mm:ss").format(new Date()).toLowerCase());
        topUp.setNominal(request.getNominal());
        System.out.println(typeFee.getSummaryTopUp());
        topUp.setAcumulateNominal((typeFee.getSummaryTopUp() + 1 ) * topUp.getNominal());
        topUp.setValidate(false);

        topUpRepository.save(topUp);
        addTopUpToCustomer(findCustomer, topUp);

        return topUp;
    }

    @Override
    public TopUp getDetailTopUp(String id){
        return findTopUpId(id);
    }

    @Override
    public AprovalTopUpResponse approvalTopUp(String id){
        TopUp topUpAprove = findTopUpId(id);
        topUpAprove.setValidate(true);
        customerService.addBalance(topUpAprove.getUsername(), topUpAprove.getAcumulateNominal());
        return AprovalTopUpResponse.builder()
                .message(String.format("Success approval TopUp with ID: %s to username: %s", id, topUpAprove.getUsername()))
                .detail_topup(topUpAprove)
                .build();
//        if(topUpAprove.isValidate()){
//            return "Already validated!";
//        }

    }

    @Override
    public List<TopUp> getAllNotApprove() {
        return topUpRepository.findAllByValidate(false);
    }

    @Override
    public List<TopUp> getAllApprove() {
        return topUpRepository.findAllByValidate(true);
    }

    @Override
    public List<TopUp> getAllTopup() {
        return topUpRepository.findAll();
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
