package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.exception;

public class PaymentApprovalException extends RuntimeException {
    public PaymentApprovalException(Integer id) {
        super("Failed to approve payment. Bill with ID " + id + " does not exist.");
    }
}