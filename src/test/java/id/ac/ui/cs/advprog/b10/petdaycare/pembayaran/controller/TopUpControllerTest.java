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

 class TopUpControllerTest {

    @Test
     void testCreateTopUpRequest() {
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
     void testGetDetailTopUp() {
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
     void testGetAllTopUp() {
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
     void testApprovalTopup() {
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
     void testGetAllNotAprove() {
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
     void testGetAllApprove() {
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