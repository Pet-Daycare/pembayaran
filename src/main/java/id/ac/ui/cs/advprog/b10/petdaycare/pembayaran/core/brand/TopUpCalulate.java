package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.brand;

import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.components.adminfee.AdminFee;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.components.cashback.Cashback;

public abstract class TopUpCalulate {

    private AdminFee adminFee;
    private Cashback cashback;
    private double summaryTopUp;

    public abstract  void create();
    public abstract void calculateSummaryAmount();

    public AdminFee getAdminFee(){ return adminFee;}
    public void setAdminFee(AdminFee fee){ this.adminFee = fee; }
    public Cashback getCashback(){ return cashback; }
    public void setCashback (Cashback cashbacks){ this.cashback = cashbacks; }
    public double getSummaryTopUp(){
        return summaryTopUp;
    }
    public void setSummaryTopUp(double summary) {
        this.summaryTopUp = summary;
    }
}
