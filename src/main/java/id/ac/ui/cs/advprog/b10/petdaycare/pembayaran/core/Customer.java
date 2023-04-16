package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Customer {
    private String id;
    private String name;
    private double balance;

    public Customer(String name, double balance) {
        this.id = id;
        this.name = name;
        this.balance = balance;
    }

    public void setBalance(double balance) throws InterruptedException {
        Thread.sleep(2000);
        this.balance = balance;
    }
}

