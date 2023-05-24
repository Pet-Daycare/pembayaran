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

public class PetWalletPaymentTest {

    @Mock
    private CustomerService customerService;

    private PetWalletPayment petWalletPayment;
    private Bill bill;
    private Customer customer;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        bill = new Bill();
        bill.setUsername("john");
        bill.setTotal(100.0);

        customer = new Customer();
        customer.setUsername("john");
        customer.setBalance(200.0);

        when(customerService.findCustomer("john")).thenReturn(customer);

        petWalletPayment = new PetWalletPayment(bill, customerService);
    }

    @Test
    public void testPay_SufficientBalance_BillPaid() {
        Bill paidBill = petWalletPayment.pay(bill);

        assertTrue(paidBill.isPaid());
        assertEquals(PaymentMethod.PET_WALLET, paidBill.getMethod());
        assertEquals(100.0, paidBill.getCustomerBalance());
        assertEquals(100.0, customer.getBalance());
    }

    @Test
    public void testPay_InsufficientBalance_BillNotPaid() {
        customer.setBalance(50.0);

        Bill unpaidBill = petWalletPayment.pay(bill);

        assertFalse(unpaidBill.isPaid());
        assertNull(unpaidBill.getMethod());
        assertEquals(0.0, unpaidBill.getCustomerBalance());
        assertEquals(50.0, customer.getBalance());
    }
}
