package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.adapter;

import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.payment.Payment;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model.payment.Bill;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model.payment.Voucher;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.service.customer.CustomerService;

public class VoucherAdapter implements Payment {
    private final Voucher adaptedVoucher;
    private final CustomerService customerService;

    public VoucherAdapter(Voucher voucher, CustomerService customerService) {
        this.adaptedVoucher = voucher;
        this.customerService = customerService;
    }

    @Override
    public Bill pay(Bill bill) {
        if (adaptedVoucher.isRedeemed()) {
            return bill;
        }
        return adaptedVoucher.redeem(bill, customerService);
    }
}
