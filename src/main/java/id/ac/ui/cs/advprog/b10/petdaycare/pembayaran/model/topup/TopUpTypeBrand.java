package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model.topup;

public enum TopUpTypeBrand {
    BCA_BANK_TRANSFER, DANA, GOPAY, MANDIRI_BANK_TRANSFER, QRIS, INVALID;

    @Override
    public String toString() {
        switch(this) {
            case BCA_BANK_TRANSFER: return "BCA Transfer";
            case DANA: return "Dana";
            case GOPAY: return "Gopay";
            case MANDIRI_BANK_TRANSFER: return "Mandiri Transfer";
            case QRIS: return "Qris";
            default: throw new IllegalArgumentException();
        }
    }

}
