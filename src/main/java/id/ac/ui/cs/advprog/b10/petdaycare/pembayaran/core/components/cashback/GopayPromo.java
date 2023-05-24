package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.components.cashback;

public class GopayPromo implements Cashback{
    @Override
    public double getCashback(){
        return 5.0 / 100.0;
    }
}
