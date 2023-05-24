package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.controller;

import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.controller.PaymentController;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.dto.payment.PaymentRequest;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model.payment.Bill;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.service.payment.PaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PaymentControllerTest {

    private PaymentController paymentController;

    @Mock
    private PaymentService paymentService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        paymentController = new PaymentController(paymentService);
    }

    @Test
    public void testCreatePayment() throws InterruptedException {
        PaymentRequest paymentRequest = new PaymentRequest();
        Bill expectedBill = new Bill();
        when(paymentService.createBill(paymentRequest)).thenReturn(expectedBill);
        when(paymentService.makePayment(expectedBill)).thenReturn(expectedBill);

        ResponseEntity<Bill> response = paymentController.createPayment(paymentRequest);

        assertEquals(expectedBill, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
        verify(paymentService, times(1)).createBill(paymentRequest);
        verify(paymentService, times(1)).makePayment(expectedBill);
    }

    @Test
    public void testApprovePayment() {
        Integer id = 123;
        Bill expectedBill = new Bill();
        when(paymentService.approvePayment(id)).thenReturn(expectedBill);

        ResponseEntity<Bill> response = paymentController.approvePayment(id);

        assertEquals(expectedBill, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
        verify(paymentService, times(1)).approvePayment(id);
    }

    @Test
    public void testApproveAllPayments() {
        List<Bill> expectedBills = new ArrayList<>();
        when(paymentService.approveAllPayments()).thenReturn(expectedBills);

        ResponseEntity<List<Bill>> response = paymentController.approveAllPayments();

        assertEquals(expectedBills, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
        verify(paymentService, times(1)).approveAllPayments();
    }

    @Test
    public void testGetAllVerified() {
        List<Bill> expectedBills = new ArrayList<>();
        when(paymentService.getAllVerified()).thenReturn(expectedBills);

        ResponseEntity<List<Bill>> response = paymentController.getAllVerified();

        assertEquals(expectedBills, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
        verify(paymentService, times(1)).getAllVerified();
    }

    @Test
    public void testGetAllNotVerified() {
        List<Bill> expectedBills = new ArrayList<>();
        when(paymentService.getAllNotVerified()).thenReturn(expectedBills);

        ResponseEntity<List<Bill>> response = paymentController.getAllNotVerified();

        assertEquals(expectedBills, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
        verify(paymentService, times(1)).getAllNotVerified();
    }

    @Test
    public void testGetBillById() {
        Integer id = 123;
        Bill expectedBill = new Bill();
        when(paymentService.getBillById(id)).thenReturn(expectedBill);

        ResponseEntity<Bill> response = paymentController.getBillById(id);

        assertEquals(expectedBill, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
        verify(paymentService, times(1)).getBillById(id);
    }

    @Test
    public void testGetAllBills() {
        List<Bill> expectedBills = new ArrayList<>();
        when(paymentService.getAllBills()).thenReturn(expectedBills);

        ResponseEntity<List<Bill>> response = paymentController.getAllBills();

        assertEquals(expectedBills, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
        verify(paymentService, times(1)).getAllBills();
    }
}
