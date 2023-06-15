package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.payment;

import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model.Customer;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model.payment.Bill;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model.payment.PaymentMethod;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.service.customer.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

 class PetWalletPaymentTest {

    @Mock
    private CustomerService customerService;
    @Mock
    private Bill bill;

    private PetWalletPayment petWalletPayment;
    private Customer customer;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        when(customerService.findCustomer("john")).thenReturn(customer);

        petWalletPayment = new PetWalletPayment(bill, customerService);
    }

     @Test
     void setCustomerService_SetsCorrectValue() {
         // Act
         petWalletPayment.setCustomerService(customerService);

         // Assert
         assertEquals(customerService, petWalletPayment.getCustomerService());
     }

     @Test
     void setBill_SetsCorrectValue() {
         // Act
         petWalletPayment.setBill(bill);

         // Assert
         assertEquals(bill, petWalletPayment.getBill());
     }
     @Test
     void getCustomerService_ReturnsCorrectValue() {
         // Act
         CustomerService result = petWalletPayment.getCustomerService();

         // Assert
         assertEquals(customerService, result);
     }

     @Test
     void getBill_ReturnsCorrectValue() {
         // Act
         Bill result = petWalletPayment.getBill();

         // Assert
         assertEquals(bill, result);
     }

    @Test
    void pay_WithSufficientBalance_ReturnsPaidBill() {
        // Arrange
        Double billTotal = 100000.0;
        Double initialBalance = 200000.0;

        bill = new Bill();
        bill.setUsername("john");
        bill.setTotal(billTotal);
        bill.setMethod(PaymentMethod.PET_WALLET);

        customer = new Customer();
        customer.setUsername("john");
        customer.setBalance(initialBalance);

        when(customerService.findCustomer(bill.getUsername())).thenReturn(customer);

        // Act
        Bill result = petWalletPayment.pay(bill);

        // Assert
        assertTrue(result.isPaid());
        assertEquals(PaymentMethod.PET_WALLET, result.getMethod());
        assertEquals(initialBalance - billTotal, result.getCustomerBalance());
        assertEquals(initialBalance - billTotal, customer.getBalance());
        verify(customerService, times(1)).findCustomer(bill.getUsername());
    }

    @Test
    void pay_WithInsufficientBalance_ReturnsUnpaidBill() {
        // Arrange
        double billTotal = 100000;
        double initialBalance = 50000;

        bill = new Bill();
        bill.setUsername("john");
        bill.setTotal(billTotal);
        bill.setMethod(PaymentMethod.PET_WALLET);

        customer = new Customer();
        customer.setUsername("john");
        customer.setBalance(initialBalance);

        when(customerService.findCustomer(bill.getUsername())).thenReturn(customer);

        // Act
        Bill result = petWalletPayment.pay(bill);

        // Assert
        assertFalse(result.isPaid());
        assertEquals(PaymentMethod.PET_WALLET, result.getMethod());
        assertEquals(initialBalance, customer.getBalance());
        verify(customerService, times(1)).findCustomer(bill.getUsername());
    }
}
