package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.payment;

import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model.payment.Bill;

public interface Payment {
    Bill pay(Bill bill) throws InterruptedException;
}
