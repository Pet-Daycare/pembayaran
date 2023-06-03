package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.controller;

import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.controller.PaymentController;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.dto.payment.PaymentRequest;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.exception.InvalidInputException;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model.payment.Bill;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model.payment.PaymentMethod;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.service.payment.PaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

 class PaymentControllerTest {

    private PaymentController paymentController;

    @Mock
    private PaymentService paymentService;

    @BeforeEach
     void setUp() {
        MockitoAnnotations.initMocks(this);
        paymentController = new PaymentController(paymentService);
    }

    @Test
     void testCreatePayment() throws InterruptedException {
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
    void createPayment_ReturnsOkResponse() throws InterruptedException {
        // Arrange
        Integer idPenitipan = 1;
        Double total = 100.0;
        String username = "john";
        String token = "abc123";
        String method = "card";
        String code = "1234";

        PaymentRequest request = new PaymentRequest();
        request.setIdPenitipan(idPenitipan);
        request.setMethod(method);
        request.setUsername(username);
        request.setTotal(total);
        request.setToken(token);
        request.setCode(code);

        Bill bill = new Bill();
        when(paymentService.createBill(request)).thenReturn(bill);
        when(paymentService.makePayment(bill)).thenReturn(bill);

        // Act
        ResponseEntity<Bill> response = paymentController.createPayment(idPenitipan, total, username, token, method, code);

        // Assert
        assertEquals(bill, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
        verify(paymentService, times(1)).createBill(request);
        verify(paymentService, times(1)).makePayment(bill);
    }

    @Test
    void createRefund_ReturnsOkResponse() throws InterruptedException {
        // Arrange
        PaymentRequest paymentRequest = PaymentRequest.builder()
                .idPenitipan(1)
                .username("eleven")
                .token("xyz123")
                .total(-100000)
                .method("voucher")
                .code("123456")
                .build();

        Bill expectedBill = Bill.builder()
                .id(1)
                .username("eleven")
                .idCustomer(1)
                .idPenitipan(1)
                .total(-100000)
                .customerBalance(120000)
                .method(PaymentMethod.PET_WALLET_WITH_VOUCHER)
                .code("123456")
                .paid(false)
                .verified(false)
                .build();

        when(paymentService.createBill(paymentRequest)).thenReturn(expectedBill);
        when(paymentService.payRefund(expectedBill)).thenReturn(expectedBill);

        // Act
        ResponseEntity<Bill> response = paymentController.createRefund(paymentRequest);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedBill, response.getBody());
    }

    @Test
    void testCreateRefund_InvalidInputException() throws InterruptedException {
        // Arrange
        PaymentRequest request = new PaymentRequest();
        // Set up the necessary properties of the request object

        when(paymentService.createBill(request)).thenThrow(InvalidInputException.class);

        // Act
        assertThrows(InvalidInputException.class, () -> paymentController.createRefund(request));

        // Assert
        verify(paymentService, times(1)).createBill(request);
        verify(paymentService, never()).payRefund(any(Bill.class));
    }

    @Test
     void testApprovePayment() {
        Integer id = 123;
        Bill expectedBill = new Bill();
        when(paymentService.approvePayment(id)).thenReturn(expectedBill);

        ResponseEntity<Bill> response = paymentController.approvePayment(id);

        assertEquals(expectedBill, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
        verify(paymentService, times(1)).approvePayment(id);
    }

    @Test
     void testApproveAllPayments() {
        List<Bill> expectedBills = new ArrayList<>();
        when(paymentService.approveAllPayments()).thenReturn(expectedBills);

        ResponseEntity<List<Bill>> response = paymentController.approveAllPayments();

        assertEquals(expectedBills, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
        verify(paymentService, times(1)).approveAllPayments();
    }

    @Test
     void testGetAllVerified() {
        List<Bill> expectedBills = new ArrayList<>();
        when(paymentService.getAllVerified()).thenReturn(expectedBills);

        ResponseEntity<List<Bill>> response = paymentController.getAllVerified();

        assertEquals(expectedBills, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
        verify(paymentService, times(1)).getAllVerified();
    }

    @Test
     void testGetAllNotVerified() {
        List<Bill> expectedBills = new ArrayList<>();
        when(paymentService.getAllNotVerified()).thenReturn(expectedBills);

        ResponseEntity<List<Bill>> response = paymentController.getAllNotVerified();

        assertEquals(expectedBills, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
        verify(paymentService, times(1)).getAllNotVerified();
    }

    @Test
     void testGetBillById() {
        Integer id = 123;
        Bill expectedBill = new Bill();
        when(paymentService.getBillById(id)).thenReturn(expectedBill);

        ResponseEntity<Bill> response = paymentController.getBillById(id);

        assertEquals(expectedBill, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
        verify(paymentService, times(1)).getBillById(id);
    }

    @Test
     void testGetAllBills() {
        List<Bill> expectedBills = new ArrayList<>();
        when(paymentService.getAllBills()).thenReturn(expectedBills);

        ResponseEntity<List<Bill>> response = paymentController.getAllBills();

        assertEquals(expectedBills, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
        verify(paymentService, times(1)).getAllBills();
    }
}
