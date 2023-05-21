package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.exception;

public class InvalidInputException  extends RuntimeException{

    public InvalidInputException() {
        super("Invalid input: Input is null");
    }

}
