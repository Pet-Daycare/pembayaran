package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.exception;

public class VoucherDoesNotExistException extends RuntimeException {
    public VoucherDoesNotExistException(String code) {
        super("Voucher with code " + code + " does not exist");
    }

}
