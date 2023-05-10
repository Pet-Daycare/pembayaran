package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.adapter;

import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.payment.Payment;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model.Bill;
import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model.Voucher;

public class VoucherAdapter implements Payment {
    private final Voucher adaptedVoucher;

    public VoucherAdapter(Voucher voucher ) {
        this.adaptedVoucher = voucher;
    }

    @Override
    public Bill pay(Bill bill) {
        if (adaptedVoucher.isRedeemed()) {
            return bill;
        }
        return adaptedVoucher.redeem(bill);
    }
}
