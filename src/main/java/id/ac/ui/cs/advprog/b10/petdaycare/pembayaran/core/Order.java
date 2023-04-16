package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Order {
    private String id;
    private String customerName;
    private String petName;
    private double discount;
    private double timeTaken;

    public Order(String customerName, String petName, double discount) {
        this.customerName = customerName;
        this.discount = discount;
        this.petName = petName;
    }
}