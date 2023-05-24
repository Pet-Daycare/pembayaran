package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.service;

import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.dto.topup.TopUpRequest;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.exception.InvalidInputException;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model.Customer;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model.topup.TopUp;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model.topup.TopUpMethod;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.repository.topup.TopUpRepository;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.service.customer.CustomerService;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.service.topup.TopUpServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class TopUpServiceImplTest {

    @Mock
    private TopUpRepository topUpRepository;

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private TopUpServiceImpl topUpService;

    @Captor
    private ArgumentCaptor<TopUp> topUpCaptor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createTopUpRequest_withValidRequest_shouldCreateTopUp() {
        TopUpRequest request = new TopUpRequest();
        request.setUsername("john");
        request.setToken("abc123");
        request.setNominal(100);
        request.setTypeMethod("GOPAY_TRANSFER");

        Customer customer = new Customer();
        customer.setUsername("john");
        customer.setToken("abc123");

        when(customerService.findCustomer("john")).thenReturn(null);
        when(customerService.createCustomer(any())).thenReturn(customer);

        TopUp result = topUpService.createTopUpRequest(request);

        assertNotNull(result);
        assertEquals("john", result.getUsername());
        assertEquals("GOPAY_TRANSFER", result.getTypeMethod().toString());
        assertEquals(new SimpleDateFormat("yyy-MM-dd HH:mm:ss").format(new Date()).toLowerCase(), result.getTimeTaken());
        assertEquals(100, result.getNominal());
        assertFalse(result.isValidate());

        verify(topUpRepository).save(topUpCaptor.capture());
//        verify(customerService).addBalance("john", 100);

        TopUp savedTopUp = topUpCaptor.getValue();
        assertNotNull(savedTopUp);
        assertEquals("john", savedTopUp.getUsername());
        assertEquals("GOPAY_TRANSFER", savedTopUp.getTypeMethod().toString());
        assertEquals(new SimpleDateFormat("yyy-MM-dd HH:mm:ss").format(new Date()).toLowerCase(), savedTopUp.getTimeTaken());
        assertEquals(100, savedTopUp.getNominal());
        assertFalse(savedTopUp.isValidate());

        verify(customerService).createCustomer(any());
    }

    @Test
    void createTopUpRequest_withExistingCustomer_shouldCreateTopUp() {
        TopUpRequest request = new TopUpRequest();
        request.setUsername("john");
        request.setToken("abc123");
        request.setNominal(100);
        request.setTypeMethod("GOPAY_TRANSFER");

        Customer customer = new Customer();
        customer.setUsername("john");
        customer.setToken("abc123");

        when(customerService.findCustomer("john")).thenReturn(customer);

        TopUp result = topUpService.createTopUpRequest(request);

        assertNotNull(result);
        assertEquals("john", result.getUsername());
        assertEquals("GOPAY_TRANSFER", result.getTypeMethod().toString());
        assertEquals(new SimpleDateFormat("yyy-MM-dd HH:mm:ss").format(new Date()).toLowerCase(), result.getTimeTaken());
        assertEquals(100, result.getNominal());
        assertFalse(result.isValidate());

        verify(topUpRepository).save(topUpCaptor.capture());
//        verify(customerService).addBalance("john", 100);

        TopUp savedTopUp = topUpCaptor.getValue();
        assertNotNull(savedTopUp);
        assertEquals("john", savedTopUp.getUsername());
        assertEquals("GOPAY_TRANSFER", savedTopUp.getTypeMethod().toString());
        assertEquals(new SimpleDateFormat("yyy-MM-dd HH:mm:ss").format(new Date()).toLowerCase(), savedTopUp.getTimeTaken());
        assertEquals(100, savedTopUp.getNominal());
        assertFalse(savedTopUp.isValidate());

        verify(customerService, never()).createCustomer(any());
    }

    @Test
    void createTopUpRequest_withInvalidRequest_shouldThrowInvalidInputException() {
        TopUpRequest request = new TopUpRequest();

        assertThrows(InvalidInputException.class, () -> topUpService.createTopUpRequest(request));

        verify(topUpRepository, never()).save(any());
        verify(customerService, never()).addBalance(anyString(), anyInt());
        verify(customerService, never()).createCustomer(any());
    }

    @Test
    void getDetailTopUp_withValidId_shouldReturnTopUp() {
        TopUp topUp = new TopUp();
        topUp.setUsername("john");

        when(topUpRepository.findToUpById("1")).thenReturn(java.util.Optional.of(topUp));

        TopUp result = topUpService.getDetailTopUp("1");

        assertNotNull(result);
        assertEquals("john", result.getUsername());

//        verify(topUpRepository).findToUpById("1");
    }

    @Test
    void getDetailTopUp_withInvalidId_shouldReturnNull() {
        when(topUpRepository.findToUpById("1")).thenReturn(java.util.Optional.empty());

        TopUp result = topUpService.getDetailTopUp("1");

        assertNull(result);

        verify(topUpRepository).findToUpById("1");
    }

    @Test
    void approvalTopUp_withValidId_shouldApproveTopUp() {
        TopUp topUp = new TopUp();
        topUp.setValidate(false);
        topUp.setUsername("john");
        topUp.setNominal(100.0);

        when(topUpRepository.findToUpById("1")).thenReturn(java.util.Optional.of(topUp));

        String result = topUpService.approvalTopUp("1");

        assertEquals("Success TopUp with ID: 1 ", result);
        assertTrue(topUp.isValidate());

//        verify(topUpRepository).findToUpById("1");
        verify(customerService).addBalance("john", 100);
    }

    @Test
    void approvalTopUp_withAlreadyApprovedId_shouldReturnAlreadyValidatedMessage() {
        TopUp topUp = new TopUp();
        topUp.setValidate(true);

        when(topUpRepository.findToUpById("1")).thenReturn(java.util.Optional.of(topUp));

        String result = topUpService.approvalTopUp("1");

        assertEquals("Already validated!", result);
        assertTrue(topUp.isValidate());

//        verify(topUpRepository).findToUpById("1");
        verify(customerService, never()).addBalance(anyString(), anyInt());
    }

    @Test
    void getAllNotApprove_shouldReturnNotApprovedTopUps() {
        TopUp topUp1 = new TopUp();
        topUp1.setValidate(false);

        TopUp topUp2 = new TopUp();
        topUp2.setValidate(false);

        when(topUpRepository.findAllByValidate(false)).thenReturn(List.of(topUp1, topUp2));

        List<TopUp> result = topUpService.getAllNotApprove();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertFalse(result.get(0).isValidate());
        assertFalse(result.get(1).isValidate());

        verify(topUpRepository).findAllByValidate(false);
    }

    @Test
    void getAllApprove_shouldReturnApprovedTopUps() {
        TopUp topUp1 = new TopUp();
        topUp1.setValidate(true);

        TopUp topUp2 = new TopUp();
        topUp2.setValidate(true);

        when(topUpRepository.findAllByValidate(true)).thenReturn(List.of(topUp1, topUp2));

        List<TopUp> result = topUpService.getAllApprove();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.get(0).isValidate());
        assertTrue(result.get(1).isValidate());

        verify(topUpRepository).findAllByValidate(true);
    }

    @Test
    void getAllTopup_shouldReturnAllTopUps() {
        TopUp topUp1 = new TopUp();
        topUp1.setValidate(true);

        TopUp topUp2 = new TopUp();
        topUp2.setValidate(false);

        when(topUpRepository.findAll()).thenReturn(List.of(topUp1, topUp2));

        List<TopUp> result = topUpService.getAllTopup();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.get(0).isValidate());
        assertFalse(result.get(1).isValidate());

        verify(topUpRepository).findAll();
    }
}
