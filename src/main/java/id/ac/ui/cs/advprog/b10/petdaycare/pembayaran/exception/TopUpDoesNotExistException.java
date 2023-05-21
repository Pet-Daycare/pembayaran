package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.exception;

public class TopUpDoesNotExistException extends RuntimeException{
    public TopUpDoesNotExistException(Integer id) {
        super("Top Up detail with ID " + id + " does not exist");
    }

}
