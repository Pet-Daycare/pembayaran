package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.controller;

import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.controller.CustomerController;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.dto.topup.CustomerRequest;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model.Customer;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model.topup.TopUp;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.service.customer.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CustomerControllerTest {

    private CustomerController customerController;

    @Mock
    private CustomerService customerService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        customerController = new CustomerController(customerService);
    }

    @Test
    public void testDetailCustomer() {
        CustomerRequest customerRequest = new CustomerRequest();
        customerRequest.setUsername("john.doe");
        Customer expectedCustomer = new Customer();
        when(customerService.findCustomer(customerRequest.getUsername())).thenReturn(expectedCustomer);

        ResponseEntity<Customer> response = customerController.detailCustomer(customerRequest);

        assertEquals(expectedCustomer, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
        verify(customerService, times(1)).findCustomer(customerRequest.getUsername());
    }

    @Test
    public void testHistoryTopUp() {
        CustomerRequest customerRequest = new CustomerRequest();
        customerRequest.setUsername("john.doe");
        List<TopUp> expectedTopUps = new ArrayList<>();
        when(customerService.historyTopUp(customerRequest.getUsername())).thenReturn(expectedTopUps);

        ResponseEntity<List<TopUp>> response = customerController.historyTopUp(customerRequest);

        assertEquals(expectedTopUps, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
        verify(customerService, times(1)).historyTopUp(customerRequest.getUsername());
    }

    @Test
    public void testDetailCustomerFrontend() {
        String username = "john.doe";
        String token = "abc123";
        Customer expectedCustomer = new Customer();
        when(customerService.getCustomerFrontend(username, token)).thenReturn(expectedCustomer);

        ResponseEntity<Customer> response = customerController.detailCustomerFrontend(username, token);

        assertEquals(expectedCustomer, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
        verify(customerService, times(1)).getCustomerFrontend(username, token);
    }

    @Test
    public void testAddCustomer() {
        CustomerRequest customerRequest = new CustomerRequest();
        Customer expectedCustomer = new Customer();
        when(customerService.createCustomer(customerRequest)).thenReturn(expectedCustomer);

        ResponseEntity<Customer> response = customerController.addCustomer(customerRequest);

        assertEquals(expectedCustomer, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
        verify(customerService, times(1)).createCustomer(customerRequest);
    }
}
