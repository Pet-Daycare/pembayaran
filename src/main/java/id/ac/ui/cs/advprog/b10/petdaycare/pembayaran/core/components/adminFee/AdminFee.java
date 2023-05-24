package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.components.adminFee;

public interface AdminFee {
    public Double getFee();

    public default String getName(){return this.getClass().getSimpleName();}
}
