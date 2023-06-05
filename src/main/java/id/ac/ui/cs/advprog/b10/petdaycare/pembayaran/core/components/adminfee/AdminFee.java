package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.components.adminfee;

public interface AdminFee {
    public Double getFee();

    public default String getName(){return this.getClass().getSimpleName();}
}
