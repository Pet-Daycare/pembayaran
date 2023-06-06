package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.controller;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.http.ResponseEntity;
import java.util.List;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.dto.topup.TopUpRequest;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model.AprovalTopUpResponse;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model.topup.TopUp;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.service.topup.TopUpService;

public class TopUpControllerTest {

    @Test
    public void testCreateTopUpRequest() {
        // Arrange
        TopUpRequest topUpRequest = new TopUpRequest();
        TopUpService topUpService = mock(TopUpService.class);
        TopupController topupController = new TopupController(topUpService);
        TopUp expectedTopUp = new TopUp();
        when(topUpService.createTopUpRequest(topUpRequest)).thenReturn(expectedTopUp);

        // Act
        ResponseEntity<TopUp> response = topupController.createTopUpRequest(topUpRequest);

        // Assert
        assertEquals(expectedTopUp, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    public void testGetDetailTopUp() {
        // Arrange
        String id = "123";
        TopUpService topUpService = mock(TopUpService.class);
        TopupController topupController = new TopupController(topUpService);
        TopUp expectedTopUp = new TopUp();
        when(topUpService.getDetailTopUp(id)).thenReturn(expectedTopUp);

        // Act
        ResponseEntity<TopUp> response = topupController.getDetailTopUp(id);

        // Assert
        assertEquals(expectedTopUp, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    public void testGetAllTopUp() {
        // Arrange
        TopUpService topUpService = mock(TopUpService.class);
        TopupController topupController = new TopupController(topUpService);
        List<TopUp> expectedTopUpList = List.of(new TopUp(), new TopUp());
        when(topUpService.getAllTopup()).thenReturn(expectedTopUpList);

        // Act
        ResponseEntity<List<TopUp>> response = topupController.getDetailTopUp();

        // Assert
        assertEquals(expectedTopUpList, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    public void testApprovalTopup() {
        // Arrange
        String id = "123";
        TopUpService topUpService = mock(TopUpService.class);
        TopupController topupController = new TopupController(topUpService);
        AprovalTopUpResponse expectedResponse = new AprovalTopUpResponse();
        when(topUpService.approvalTopUp(id)).thenReturn(expectedResponse);

        // Act
        ResponseEntity<AprovalTopUpResponse> response = topupController.approvalTopup(id);

        // Assert
        assertEquals(expectedResponse, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    public void testGetAllNotAprove() {
        // Arrange
        TopUpService topUpService = mock(TopUpService.class);
        TopupController topupController = new TopupController(topUpService);
        List<TopUp> expectedTopUpList = List.of(new TopUp(), new TopUp());
        when(topUpService.getAllNotApprove()).thenReturn(expectedTopUpList);

        // Act
        ResponseEntity<List<TopUp>> response = topupController.getAllNotAprove();

        // Assert
        assertEquals(expectedTopUpList, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    public void testGetAllApprove() {
        // Arrange
        TopUpService topUpService = mock(TopUpService.class);
        TopupController topupController = new TopupController(topUpService);
        List<TopUp> expectedTopUpList = List.of(new TopUp(), new TopUp());
        when(topUpService.getAllApprove()).thenReturn(expectedTopUpList);

        // Act
        ResponseEntity<List<TopUp>> response = topupController.getAllApprove();

        // Assert
        assertEquals(expectedTopUpList, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
    }
}


//package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.controller;
//
//import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.controller.TopupController;
//import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.dto.topup.TopUpRequest;
//import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model.AprovalTopUpResponse;
//import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model.topup.TopUp;
//import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.repository.topup.TopUpRepository;
//import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.service.topup.TopUpService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.http.ResponseEntity;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.*;
//
//public class TopUpControllerTest {
//
//    private TopupController topupController;
//
//    @Mock
//    private TopUpService topUpService;
//
//    @Mock
//    private TopUpRepository topupRepo;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.initMocks(this);
//        topupController = new TopupController(topUpService);
//    }
//
//    @Test
//    public void testCreateTopUpRequest() {
//        TopUpRequest topUpRequest = new TopUpRequest();
//        TopUp expectedTopUp = new TopUp();
//        when(topUpService.createTopUpRequest(topUpRequest)).thenReturn(expectedTopUp);
//
//        ResponseEntity<TopUp> response = topupController.createTopUpRequest(topUpRequest);
//
//        assertEquals(expectedTopUp, response.getBody());
//        assertEquals(200, response.getStatusCodeValue());
//        verify(topUpService, times(1)).createTopUpRequest(topUpRequest);
//    }
//
//    @Test
//    public void testGetDetailTopUp() {
//        String id = "123456";
//        TopUp expectedTopUp = new TopUp();
//        when(topUpService.getDetailTopUp(id)).thenReturn(expectedTopUp);
//
//        ResponseEntity<TopUp> response = topupController.getDetailTopUp(id);
//
//        assertEquals(expectedTopUp, response.getBody());
//        assertEquals(200, response.getStatusCodeValue());
//        verify(topUpService, times(1)).getDetailTopUp(id);
//    }
//
//    @Test
//    public void testAprovalTopUp() {
//        TopUp topup = new TopUp();
//        topup.setId("123456");
//        String id = "123456";
//        AprovalTopUpResponse expectedResponse = AprovalTopUpResponse.builder()
//                .message(String.format("Success approval TopUp with ID: %s to username: %s", id, topup.getUsername()))
//                .detailTopup(topup)
//                .build();
//        when(topUpService.approvalTopUp(id)).thenReturn(
//                AprovalTopUpResponse.builder()
//                        .message(String.format("Success approval TopUp with ID: %s to username: %s", id, topup.getUsername()))
//                        .detailTopup(topup)
//                        .build());
//
//        ResponseEntity<AprovalTopUpResponse> response = topupController.approvalTopup(id);
//
//        assertEquals(expectedResponse, response.getBody());
//        assertEquals(200, response.getStatusCodeValue());
//        verify(topUpService, times(1)).approvalTopUp(id);
//    }
//
//    @Test
//    public void testGetAllNotAprove() {
//        List<TopUp> expectedTopUps = new ArrayList<>();
//        when(topUpService.getAllNotApprove()).thenReturn(expectedTopUps);
//
//        ResponseEntity<List<TopUp>> response = topupController.getAllNotAprove();
//
//        assertEquals(expectedTopUps, response.getBody());
//        assertEquals(200, response.getStatusCodeValue());
//        verify(topUpService, times(1)).getAllNotApprove();
//    }
//}
