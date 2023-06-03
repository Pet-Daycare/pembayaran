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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

 class CustomerControllerTest {

    private CustomerController customerController;

    @Mock
    private CustomerService customerService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        customerController = new CustomerController(customerService);
    }

    @Test
     void testDetailCustomer() {
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
     void detailCustomer_WithValidUsername_ReturnsOkResponse() {
         // Arrange
         String username = "john";
         Customer customer = new Customer();
         customer.setUsername(username);

         when(customerService.findCustomer(username)).thenReturn(customer);

         // Act
         ResponseEntity<Customer> response = customerController.detailCustomer(username);

         // Assert
         assertEquals(customer, response.getBody());
         assertEquals(HttpStatus.OK, response.getStatusCode());
         verify(customerService, times(1)).findCustomer(username);
     }

     @Test
     void detailCustomer_WithException_ReturnsBadRequestResponse() {
         // Arrange
         String username = "john";

         when(customerService.findCustomer(username)).thenThrow(new RuntimeException());

         // Act
         ResponseEntity<Customer> response = customerController.detailCustomer(username);

         // Assert
         assertNull(response.getBody());
         assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
         verify(customerService, times(1)).findCustomer(username);
     }

    @Test
     void testHistoryTopUp() {
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
     void testDetailCustomerFrontend() {
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
     void testAddCustomer() {
        CustomerRequest customerRequest = new CustomerRequest();
        Customer expectedCustomer = new Customer();
        when(customerService.createCustomer(customerRequest)).thenReturn(expectedCustomer);

        ResponseEntity<Customer> response = customerController.addCustomer(customerRequest);

        assertEquals(expectedCustomer, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
        verify(customerService, times(1)).createCustomer(customerRequest);
    }

    @Test
    void historyTopUp_ReturnsOkResponse() {
        // Arrange
        String username = "john";
        List<TopUp> topUpList = new ArrayList<>();
        topUpList.add(new TopUp());
        topUpList.add(new TopUp());

        when(customerService.historyTopUp(username)).thenReturn(topUpList);

        // Act
        ResponseEntity<List<TopUp>> response = customerController.historyTopUp(username);

        // Assert
        assertEquals(topUpList, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
        verify(customerService, times(1)).historyTopUp(username);
    }
}
