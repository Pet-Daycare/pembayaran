package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.components.cashback;

public interface Cashback {
    public double getCashback();
    public default String getName() {
        return this.getClass().getSimpleName();
    }
}
