package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.exception;

public class CouponDoesNotExistException extends RuntimeException {
    public CouponDoesNotExistException(String code) {
        super("Coupon with code " + code + " does not exist");
    }

}
