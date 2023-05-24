package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.service;

import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.dto.AuthTransactionDto;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.dto.topup.CustomerRequest;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.exception.CustomerAlreadyExistException;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.exception.CustomerDoesNotExistException;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model.Customer;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model.topup.TopUp;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.repository.CustomerRepository;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.service.customer.CustomerServiceImpl;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.service.topup.TopUpService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CustomerServiceImplTest {

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private Customer customer;

    @Mock
    private TopUpService topUpService;

    private CustomerServiceImpl customerService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        customerService = new CustomerServiceImpl(restTemplate, customerRepository);
    }

    @Test
    public void testCreateCustomer_WithValidRequest_ShouldCreateCustomer() {
        // Arrange
        CustomerRequest request = new CustomerRequest("john_doe", "123456");
        AuthTransactionDto authTransactionDto = new AuthTransactionDto();
        authTransactionDto.setIdCustomer(1);
        authTransactionDto.setUsername("john_doe");

        when(restTemplate.getForObject(anyString(), eq(AuthTransactionDto.class))).thenReturn(authTransactionDto);
        when(customerRepository.findCustomersByusername("john_doe")).thenReturn(Optional.empty());

        // Act
        Customer customer = customerService.createCustomer(request);

        // Assert
        assertNotNull(customer);
        assertEquals("john_doe", customer.getUsername());
        assertEquals(1, customer.getCustomerId());
        assertEquals(0.0, customer.getBalance());
        assertTrue(customer.getPaymentList().isEmpty());
        assertTrue(customer.getTopUpList().isEmpty());
        verify(customerRepository, times(1)).save(customer);
        verify(restTemplate, times(1)).getForObject(anyString(), eq(AuthTransactionDto.class));
        verify(customerRepository, times(1)).findCustomersByusername("john_doe");
    }

    @Test
    public void testCreateCustomer_WithInvalidToken_ShouldThrowRuntimeException() {
        // Arrange
        CustomerRequest request = new CustomerRequest("john_doe", "123456");

        when(restTemplate.getForObject(anyString(), eq(AuthTransactionDto.class))).thenThrow(RuntimeException.class);

        // Act and Assert
        assertThrows(RuntimeException.class, () -> customerService.createCustomer(request));
        verify(customerRepository, never()).save(any());
        verify(restTemplate, times(1)).getForObject(anyString(), eq(AuthTransactionDto.class));
        verify(customerRepository, never()).findCustomersByusername(any());
    }

    @Test
    public void testCreateCustomer_WithExistingCustomer_ShouldThrowCustomerDoesNotExistException() {
        // Arrange
        CustomerRequest request = new CustomerRequest("john_doe", "123456");

        when(restTemplate.getForObject(anyString(), eq(AuthTransactionDto.class))).thenReturn(new AuthTransactionDto());
        when(customerRepository.findCustomersByusername("john_doe")).thenReturn(Optional.of(new Customer()));

        // Act and Assert
        assertThrows(CustomerAlreadyExistException.class, () -> customerService.createCustomer(request));
        verify(customerRepository, never()).save(any());
        verify(restTemplate, times(1)).getForObject(anyString(), eq(AuthTransactionDto.class));
        verify(customerRepository, times(1)).findCustomersByusername("john_doe");
    }


    // Existing test case: testCreateCustomer_WithValidRequest_ShouldCreateCustomer

    @Test
    public void testAddBalance_WithExistingCustomer_ShouldUpdateBalance() {
        // Arrange
        String username = "john_doe";
        double initialBalance = 100.0;
        double amountToAdd = 50.0;
        Customer customer = new Customer();
        customer.setUsername(username);
        customer.setBalance(initialBalance);

        when(customerRepository.findCustomersByusername(username)).thenReturn(Optional.of(customer));

        // Act
        Double newBalance = customerService.addBalance(username, amountToAdd);

        // Assert
        assertNotNull(newBalance);
        assertEquals(initialBalance + amountToAdd, newBalance);
        assertEquals(initialBalance + amountToAdd, customer.getBalance());
        verify(customerRepository, times(2)).findCustomersByusername(username);
    }

    @Test
    public void testAddBalance_WithNonExistingCustomer_ShouldReturnNull() {
        // Arrange
        String username = "john_doe";
        double amountToAdd = 50.0;

        when(customerRepository.findCustomersByusername(username)).thenReturn(Optional.empty());

        // Act
        Double newBalance = customerService.addBalance(username, amountToAdd);

        // Assert
        assertNull(newBalance);
        verify(customerRepository, times(1)).findCustomersByusername(username);
    }

    @Test
    public void testFindCustomer_WithExistingCustomer_ShouldReturnCustomer() {
        // Arrange
        String username = "john_doe";
        Customer customer = new Customer();
        customer.setUsername(username);

        when(customerRepository.findCustomersByusername(username)).thenReturn(Optional.of(customer));

        // Act
        Customer foundCustomer = customerService.findCustomer(username);

        // Assert
        assertNotNull(foundCustomer);
        assertEquals(username, foundCustomer.getUsername());
        verify(customerRepository, times(2)).findCustomersByusername(username);
    }

    @Test
    public void testFindCustomer_WithNonExistingCustomer_ShouldReturnNull() {
        // Arrange
        String username = "john_doe";

        when(customerRepository.findCustomersByusername(username)).thenReturn(Optional.empty());

        // Act
        Customer foundCustomer = customerService.findCustomer(username);

        // Assert
        assertNull(foundCustomer);
        verify(customerRepository, times(1)).findCustomersByusername(username);
    }

    @Test
    public void testGetCustomerFrontend_WithExistingCustomer_ShouldReturnCustomer() {
        // Arrange
        String username = "john_doe";
        String token = "valid_token";
        Customer customer = new Customer();
        customer.setUsername(username);

        when(customerRepository.findCustomersByusername(username)).thenReturn(Optional.of(customer));

        // Act
        Customer foundCustomer = customerService.getCustomerFrontend(username, token);

        // Assert
        assertNotNull(foundCustomer);
        assertEquals(username, foundCustomer.getUsername());
        verify(customerRepository, times(2)).findCustomersByusername(username);
    }

    @Test
    public void testGetCustomerFrontend_WithNonExistingCustomer_ShouldCreateAndReturnCustomer() {
        // Arrange
        String username = "john_doe";
        String token = "valid_token";
        CustomerRequest request = new CustomerRequest(username, token);
        Customer createdCustomer = new Customer();
        createdCustomer.setUsername(username);

        when(customerRepository.findCustomersByusername(username)).thenReturn(Optional.empty());
        when(customerRepository.save(any(Customer.class))).thenReturn(createdCustomer);

        // Act
        Customer foundCustomer = customerService.getCustomerFrontend(username, token);

        // Assert
        assertNotNull(foundCustomer);
        assertEquals(username, foundCustomer.getUsername());
        verify(customerRepository, times(1)).findCustomersByusername(username);
        verify(customerRepository, times(1)).save(any(Customer.class));
    }

    @Test
    public void testHistoryTopUp_WithExistingCustomer_ShouldReturnTopUpList() {
        // Arrange
        String username = "john_doe";
//        customer = new Customer();
        customer.setUsername(username);
        List<TopUp> topUpList = new ArrayList<>();
        topUpList.add(new TopUp());
        topUpList.add(new TopUp());

        when(customerRepository.findCustomersByusername(username)).thenReturn(Optional.of(customer));
        when(customer.getTopUpList()).thenReturn(topUpList);

        // Act
        List<TopUp> history = customerService.historyTopUp(username);

        // Assert
        assertNotNull(history);
        assertEquals(topUpList, history);
        verify(customerRepository, times(2)).findCustomersByusername(username);
        verify(customer, times(1)).getTopUpList();
    }

    @Test
    public void testHistoryTopUp_WithNonExistingCustomer_ShouldReturnEmptyList() {
        // Arrange
        String username = "john_doe";

        when(customerRepository.findCustomersByusername(username)).thenReturn(Optional.empty());

        // Act
        List<TopUp> history = customerService.historyTopUp(username);

        // Assert
        assertNotNull(history);
        assertTrue(history.isEmpty());
        verify(customerRepository, times(1)).findCustomersByusername(username);
    }

    // Additional test cases for the other methods in CustomerServiceImpl can be added here...

}