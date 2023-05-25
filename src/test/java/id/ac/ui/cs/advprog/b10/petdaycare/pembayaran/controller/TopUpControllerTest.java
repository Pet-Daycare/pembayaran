package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.controller;

import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.controller.TopupController;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.dto.topup.TopUpRequest;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model.AprovalTopUpResponse;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model.topup.TopUp;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.repository.topup.TopUpRepository;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.service.topup.TopUpService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TopUpControllerTest {

    private TopupController topupController;

    @Mock
    private TopUpService topUpService;

    @Mock
    private TopUpRepository topupRepo;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        topupController = new TopupController(topUpService);
    }

    @Test
    public void testCreateTopUpRequest() {
        TopUpRequest topUpRequest = new TopUpRequest();
        TopUp expectedTopUp = new TopUp();
        when(topUpService.createTopUpRequest(topUpRequest)).thenReturn(expectedTopUp);

        ResponseEntity<TopUp> response = topupController.createTopUpRequest(topUpRequest);

        assertEquals(expectedTopUp, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
        verify(topUpService, times(1)).createTopUpRequest(topUpRequest);
    }

    @Test
    public void testGetDetailTopUp() {
        String id = "123456";
        TopUp expectedTopUp = new TopUp();
        when(topUpService.getDetailTopUp(id)).thenReturn(expectedTopUp);

        ResponseEntity<TopUp> response = topupController.getDetailTopUp(id);

        assertEquals(expectedTopUp, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
        verify(topUpService, times(1)).getDetailTopUp(id);
    }

    @Test
    public void testAprovalTopUp() {
        TopUp topup = new TopUp();
        topup.setId("123456");
        String id = "123456";
        AprovalTopUpResponse expectedResponse = AprovalTopUpResponse.builder()
                .message(String.format("Success approval TopUp with ID: %s to username: %s", id, topup.getUsername()))
                .detail_topup(topup)
                .build();
        when(topUpService.approvalTopUp(id)).thenReturn(
                AprovalTopUpResponse.builder()
                        .message(String.format("Success approval TopUp with ID: %s to username: %s", id, topup.getUsername()))
                        .detail_topup(topup)
                        .build());

        ResponseEntity<AprovalTopUpResponse> response = topupController.approvalTopup(id);

        assertEquals(expectedResponse, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
        verify(topUpService, times(1)).approvalTopUp(id);
    }

    @Test
    public void testGetAllNotAprove() {
        List<TopUp> expectedTopUps = new ArrayList<>();
        when(topUpService.getAllNotApprove()).thenReturn(expectedTopUps);

        ResponseEntity<List<TopUp>> response = topupController.getAllNotAprove();

        assertEquals(expectedTopUps, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
        verify(topUpService, times(1)).getAllNotApprove();
    }
}
