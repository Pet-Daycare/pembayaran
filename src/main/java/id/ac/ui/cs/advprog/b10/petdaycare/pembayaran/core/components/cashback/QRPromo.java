package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.components.cashback;

import java.util.Random;

public class QRPromo implements Cashback{
    @Override
    public double getCashback(){
        Random r = new Random();
        return 0.0 / 100.0;
    }
}
