package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.controller;

import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.controller.CouponController;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.dto.couponVoucher.CouponRequest;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.dto.couponVoucher.VoucherRequest;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model.payment.Coupon;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model.payment.Voucher;
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

public class CouponControllerTest {

    private CouponController couponController;

    @Mock
    private PaymentService paymentService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        couponController = new CouponController(paymentService);
    }

    @Test
    public void testAddCoupon() throws InterruptedException {
        CouponRequest couponRequest = new CouponRequest();
        Coupon expectedCoupon = new Coupon();
        when(paymentService.addCoupon(couponRequest)).thenReturn(expectedCoupon);

        ResponseEntity<Coupon> response = couponController.addCoupon(couponRequest);

        assertEquals(expectedCoupon, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
        verify(paymentService, times(1)).addCoupon(couponRequest);
    }

    @Test
    public void testGetCouponByCode() {
        String code = "ABC123";
        Coupon expectedCoupon = new Coupon();
        when(paymentService.getCouponByCode(code)).thenReturn(expectedCoupon);

        ResponseEntity<Coupon> response = couponController.getCouponByCode(code);

        assertEquals(expectedCoupon, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
        verify(paymentService, times(1)).getCouponByCode(code);
    }

    @Test
    public void testGetAllCoupons() {
        List<Coupon> expectedCoupons = new ArrayList<>();
        when(paymentService.getAllCoupons()).thenReturn(expectedCoupons);

        ResponseEntity<List<Coupon>> response = couponController.getAllCoupons();

        assertEquals(expectedCoupons, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
        verify(paymentService, times(1)).getAllCoupons();
    }

    @Test
    public void testAddVoucher() throws InterruptedException {
        VoucherRequest voucherRequest = new VoucherRequest();
        Voucher expectedVoucher = new Voucher();
        when(paymentService.addVoucher(voucherRequest)).thenReturn(expectedVoucher);

        ResponseEntity<Voucher> response = couponController.addVoucher(voucherRequest);

        assertEquals(expectedVoucher, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
        verify(paymentService, times(1)).addVoucher(voucherRequest);
    }

    @Test
    public void testGetVoucherByCode() {
        String code = "XYZ456";
        Voucher expectedVoucher = new Voucher();
        when(paymentService.getVoucherByCode(code)).thenReturn(expectedVoucher);

        ResponseEntity<Voucher> response = couponController.getVoucherByCode(code);

        assertEquals(expectedVoucher, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
        verify(paymentService, times(1)).getVoucherByCode(code);
    }

    @Test
    public void testGetAllVouchers() {
        List<Voucher> expectedVouchers = new ArrayList<>();
        when(paymentService.getAllVouchers()).thenReturn(expectedVouchers);

        ResponseEntity<List<Voucher>> response = couponController.getAllVouchers();

        assertEquals(expectedVouchers, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
        verify(paymentService, times(1)).getAllVouchers();
    }
}
