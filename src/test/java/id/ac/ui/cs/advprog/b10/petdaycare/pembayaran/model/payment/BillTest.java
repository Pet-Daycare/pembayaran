package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.model.payment;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BillTest {

    @Test
    void testBuilderToString() {
        Bill.BillBuilder builder = Bill.builder()
                .id(1)
                .username("john.doe")
                .idCustomer(123)
                .idPenitipan(456)
                .total(100.0)
                .customerBalance(50.0)
                .method(PaymentMethod.PET_WALLET_WITH_COUPON)
                .code("ABCD1234")
                .paid(true)
                .verified(true);

        String expectedString = "Bill.BillBuilder(id=1, username=john.doe, idCustomer=123, " +
                "idPenitipan=456, total=100.0, customerBalance=50.0, method=PET_WALLET_WITH_COUPON, " +
                "code=ABCD1234, paid=true, verified=true)";

        assertEquals(expectedString, builder.toString());
    }

    @Test
    void testSetIdCustomer() {
        Bill bill = new Bill();
        Integer idCustomer = 1;

        bill.setIdCustomer(idCustomer);

        assertEquals(idCustomer, bill.getIdCustomer());
    }

    @Test
    void testSetIdPenitipan() {
        Bill bill = new Bill();
        Integer idPenitipan = 2;

        bill.setIdPenitipan(idPenitipan);

        assertEquals(idPenitipan, bill.getIdPenitipan());
    }

    @Test
    void testCanEqual() {
        Bill bill1 = new Bill();
        bill1.setId(1);
        bill1.setUsername("user1");

        Bill bill2 = new Bill();
        bill2.setId(1);
        bill2.setUsername("user1");

        Bill bill3 = new Bill();
        bill3.setId(2);
        bill3.setUsername("user2");

        assertTrue(bill1.canEqual(bill2));
        assertTrue(bill1.canEqual(bill3));
    }

    @Test
    void testToString() {
        Bill bill = Bill.builder()
                .id(1)
                .username("user1")
                .idCustomer(2)
                .idPenitipan(3)
                .total(100.0)
                .customerBalance(50.0)
                .method(PaymentMethod.PET_WALLET_WITH_COUPON)
                .code("ABC123")
                .paid(true)
                .verified(false)
                .build();

        String expectedString = "Bill(id=1, username=user1, idCustomer=2, idPenitipan=3, total=100.0, customerBalance=50.0, method=PET_WALLET_WITH_COUPON, code=ABC123, paid=true, verified=false)";
        String actualString = bill.toString();

        assertEquals(expectedString, actualString);
    }

    @Test
    void testHashCode() {
        Bill bill1 = Bill.builder()
                .id(1)
                .username("user1")
                .idCustomer(2)
                .idPenitipan(3)
                .total(100.0)
                .customerBalance(50.0)
                .method(PaymentMethod.PET_WALLET_WITH_COUPON)
                .code("ABC123")
                .paid(true)
                .verified(false)
                .build();

        Bill bill2 = Bill.builder()
                .id(1)
                .username("user1")
                .idCustomer(2)
                .idPenitipan(3)
                .total(100.0)
                .customerBalance(50.0)
                .method(PaymentMethod.PET_WALLET_WITH_COUPON)
                .code("ABC123")
                .paid(true)
                .verified(false)
                .build();

        assertEquals(bill1.hashCode(), bill2.hashCode());
    }

    @Test
    void testEquals() {
        Bill bill1 = Bill.builder()
                .id(1)
                .username("user1")
                .idCustomer(2)
                .idPenitipan(3)
                .total(100.0)
                .customerBalance(50.0)
                .method(PaymentMethod.PET_WALLET_WITH_COUPON)
                .code("ABC123")
                .paid(true)
                .verified(false)
                .build();

        Bill bill2 = Bill.builder()
                .id(1)
                .username("user1")
                .idCustomer(2)
                .idPenitipan(3)
                .total(100.0)
                .customerBalance(50.0)
                .method(PaymentMethod.PET_WALLET_WITH_COUPON)
                .code("ABC123")
                .paid(true)
                .verified(false)
                .build();

        Bill bill3 = Bill.builder()
                .id(2)
                .username("user2")
                .idCustomer(3)
                .idPenitipan(4)
                .total(200.0)
                .customerBalance(100.0)
                .method(PaymentMethod.PET_WALLET)
                .code("DEF456")
                .paid(false)
                .verified(true)
                .build();

        assertEquals(bill1, bill2);
        assertNotEquals(bill1, bill3);
    }

    @Test
    void testEquals_SameObject_ReturnsTrue(){
        Bill bill1 = Bill.builder()
                .id(1)
                .username("user1")
                .idCustomer(2)
                .idPenitipan(3)
                .total(100.0)
                .customerBalance(50.0)
                .method(PaymentMethod.PET_WALLET_WITH_COUPON)
                .code("ABC123")
                .paid(true)
                .verified(false)
                .build();

        assertEquals(bill1, bill1);
    }
    @Test
    void testEquals_SameDetail_ReturnsTrue(){
        Bill bill1 = Bill.builder()
                .id(1)
                .username("user1")
                .idCustomer(2)
                .idPenitipan(3)
                .total(100.0)
                .customerBalance(50.0)
                .method(PaymentMethod.PET_WALLET_WITH_COUPON)
                .code("ABC123")
                .paid(true)
                .verified(false)
                .build();

        Bill bill2 = Bill.builder()
                .id(1)
                .username("user1")
                .idCustomer(2)
                .idPenitipan(3)
                .total(100.0)
                .customerBalance(50.0)
                .method(PaymentMethod.PET_WALLET_WITH_COUPON)
                .code("ABC123")
                .paid(true)
                .verified(false)
                .build();

        assertEquals(bill1, bill2);
    }

    @Test
    void testEquals_DiffUsername_ReturnsFalse(){
        Bill bill1 = Bill.builder()
                .id(1)
                .username("user1")
                .idCustomer(2)
                .idPenitipan(3)
                .total(100.0)
                .customerBalance(50.0)
                .method(PaymentMethod.PET_WALLET_WITH_COUPON)
                .code("ABC123")
                .paid(true)
                .verified(false)
                .build();

        Bill bill2 = Bill.builder()
                .id(1)
                .username("user2")
                .idCustomer(2)
                .idPenitipan(3)
                .total(100.0)
                .customerBalance(50.0)
                .method(PaymentMethod.PET_WALLET_WITH_COUPON)
                .code("ABC123")
                .paid(true)
                .verified(false)
                .build();

        assertNotEquals(bill1, bill2);
    }

    @Test
    void testEquals_DiffMethod_ReturnsFalse(){
        Bill bill1 = Bill.builder()
                .id(1)
                .username("user1")
                .idCustomer(2)
                .idPenitipan(3)
                .total(100.0)
                .customerBalance(50.0)
                .method(PaymentMethod.PET_WALLET_WITH_COUPON)
                .code("ABC123")
                .paid(true)
                .verified(false)
                .build();

        Bill bill2 = Bill.builder()
                .id(1)
                .username("user1")
                .idCustomer(2)
                .idPenitipan(3)
                .total(100.0)
                .customerBalance(50.0)
                .method(PaymentMethod.PET_WALLET_WITH_VOUCHER)
                .code("ABC123")
                .paid(true)
                .verified(false)
                .build();

        assertNotEquals(bill1, bill2);
    }

    @Test
    void testEquals_DiffTotal_ReturnsFalse(){
        Bill bill1 = Bill.builder()
                .id(1)
                .username("user1")
                .idCustomer(2)
                .idPenitipan(3)
                .total(100.0)
                .customerBalance(50.0)
                .method(PaymentMethod.PET_WALLET_WITH_COUPON)
                .code("ABC123")
                .paid(true)
                .verified(false)
                .build();

        Bill bill2 = Bill.builder()
                .id(1)
                .username("user1")
                .idCustomer(2)
                .idPenitipan(3)
                .total(120.0)
                .customerBalance(50.0)
                .method(PaymentMethod.PET_WALLET_WITH_COUPON)
                .code("ABC123")
                .paid(true)
                .verified(false)
                .build();

        assertNotEquals(bill1, bill2);
    }

    @Test
    void testEquals_DiffId_ReturnsFalse(){
        Bill bill1 = Bill.builder()
                .id(1)
                .username("user1")
                .idCustomer(2)
                .idPenitipan(3)
                .total(100.0)
                .customerBalance(50.0)
                .method(PaymentMethod.PET_WALLET_WITH_COUPON)
                .code("ABC123")
                .paid(true)
                .verified(false)
                .build();

        Bill bill2 = Bill.builder()
                .id(2)
                .username("user1")
                .idCustomer(2)
                .idPenitipan(3)
                .total(100.0)
                .customerBalance(50.0)
                .method(PaymentMethod.PET_WALLET_WITH_COUPON)
                .code("ABC123")
                .paid(true)
                .verified(false)
                .build();

        assertNotEquals(bill1, bill2);
    }

    @Test
    void testEquals_DiffIdCust_ReturnsFalse(){
        Bill bill1 = Bill.builder()
                .id(1)
                .username("user1")
                .idCustomer(1)
                .idPenitipan(3)
                .total(100.0)
                .customerBalance(50.0)
                .method(PaymentMethod.PET_WALLET_WITH_COUPON)
                .code("ABC123")
                .paid(true)
                .verified(false)
                .build();

        Bill bill2 = Bill.builder()
                .id(1)
                .username("user1")
                .idCustomer(2)
                .idPenitipan(3)
                .total(100.0)
                .customerBalance(50.0)
                .method(PaymentMethod.PET_WALLET_WITH_COUPON)
                .code("ABC123")
                .paid(true)
                .verified(false)
                .build();

        assertNotEquals(bill1, bill2);
    }

    @Test
    void testEquals_DiffIdPenitipan_ReturnsFalse(){
        Bill bill1 = Bill.builder()
                .id(1)
                .username("user1")
                .idCustomer(2)
                .idPenitipan(2)
                .total(100.0)
                .customerBalance(50.0)
                .method(PaymentMethod.PET_WALLET_WITH_COUPON)
                .code("ABC123")
                .paid(true)
                .verified(false)
                .build();

        Bill bill2 = Bill.builder()
                .id(1)
                .username("user1")
                .idCustomer(2)
                .idPenitipan(3)
                .total(100.0)
                .customerBalance(50.0)
                .method(PaymentMethod.PET_WALLET_WITH_COUPON)
                .code("ABC123")
                .paid(true)
                .verified(false)
                .build();

        assertNotEquals(bill1, bill2);
    }

    @Test
    void testEquals_DiffCode_ReturnsFalse(){
        Bill bill1 = Bill.builder()
                .id(1)
                .username("user1")
                .idCustomer(2)
                .idPenitipan(3)
                .total(100.0)
                .customerBalance(50.0)
                .method(PaymentMethod.PET_WALLET_WITH_COUPON)
                .code("ABC123")
                .paid(true)
                .verified(false)
                .build();

        Bill bill2 = Bill.builder()
                .id(1)
                .username("user1")
                .idCustomer(2)
                .idPenitipan(3)
                .total(100.0)
                .customerBalance(50.0)
                .method(PaymentMethod.PET_WALLET_WITH_COUPON)
                .code("DEF456")  // Different code
                .paid(true)
                .verified(false)
                .build();

        assertNotEquals(bill1, bill2);
    }

    @Test
    void testEquals_DiffPaid_ReturnsFalse(){
        Bill bill1 = Bill.builder()
                .id(1)
                .username("user1")
                .idCustomer(2)
                .idPenitipan(3)
                .total(100.0)
                .customerBalance(50.0)
                .method(PaymentMethod.PET_WALLET_WITH_COUPON)
                .code("ABC123")
                .paid(true)
                .verified(false)
                .build();

        Bill bill2 = Bill.builder()
                .id(1)
                .username("user1")
                .idCustomer(2)
                .idPenitipan(3)
                .total(100.0)
                .customerBalance(50.0)
                .method(PaymentMethod.PET_WALLET_WITH_COUPON)
                .code("ABC123")
                .paid(false)  // Different paid value
                .verified(false)
                .build();

        assertNotEquals(bill1, bill2);
    }

    @Test
    void testEquals_DiffVerified_ReturnsFalse(){
        Bill bill1 = Bill.builder()
                .id(1)
                .username("user1")
                .idCustomer(2)
                .idPenitipan(3)
                .total(100.0)
                .customerBalance(50.0)
                .method(PaymentMethod.PET_WALLET_WITH_COUPON)
                .code("ABC123")
                .paid(true)
                .verified(false)
                .build();

        Bill bill2 = Bill.builder()
                .id(1)
                .username("user1")
                .idCustomer(2)
                .idPenitipan(3)
                .total(100.0)
                .customerBalance(50.0)
                .method(PaymentMethod.PET_WALLET_WITH_COUPON)
                .code("ABC123")
                .paid(true)
                .verified(true)  // Different verified value
                .build();

        assertNotEquals(bill1, bill2);
    }

    @Test
    void testEquals_DiffCustomerBalance_ReturnsFalse() {
        Bill bill1 = Bill.builder()
                .id(1)
                .username("user1")
                .idCustomer(2)
                .idPenitipan(3)
                .total(100.0)
                .customerBalance(50.0)
                .method(PaymentMethod.PET_WALLET_WITH_COUPON)
                .code("ABC123")
                .paid(true)
                .verified(false)
                .build();

        Bill bill2 = Bill.builder()
                .id(1)
                .username("user1")
                .idCustomer(2)
                .idPenitipan(3)
                .total(100.0)
                .customerBalance(75.0)  // Different customer balance
                .method(PaymentMethod.PET_WALLET_WITH_COUPON)
                .code("ABC123")
                .paid(true)
                .verified(false)
                .build();

        assertNotEquals(bill1, bill2);
    }

    @Test
    void testEquals_DiffWithObject_ReturnsFalse(){
        Bill bill1 = Bill.builder()
                .id(1)
                .username("user1")
                .idCustomer(2)
                .idPenitipan(3)
                .total(100.0)
                .customerBalance(50.0)
                .method(PaymentMethod.PET_WALLET_WITH_COUPON)
                .code("ABC123")
                .paid(true)
                .verified(false)
                .build();

        Object obj = new Object();
        assertNotEquals(bill1, obj);
    }
    @Test
    void testEquals_DiffWithNull_ReturnsFalse(){
        Bill bill1 = Bill.builder()
                .id(1)
                .username("user1")
                .idCustomer(2)
                .idPenitipan(3)
                .total(100.0)
                .customerBalance(50.0)
                .method(PaymentMethod.PET_WALLET_WITH_COUPON)
                .code("ABC123")
                .paid(true)
                .verified(false)
                .build();

        assertNotEquals(bill1, null);
    }


//
//    @Test
//     void testEquals_SameObject_ReturnsTrue() {
//        // Arrange
//        Bill bill = new Bill();
//
//        // Act
//        boolean result = bill.equals(bill);
//
//        // Assert
//        Assertions.assertTrue(result);
//    }
//
//    @Test
//     void testEquals_NullObject_ReturnsFalse() {
//        // Arrange
//        Bill bill = new Bill();
//
//        // Act
//        boolean result = bill.equals(null);
//
//        // Assert
//        Assertions.assertFalse(result);
//    }
//
//    @Test
//     void testEquals_DifferentClass_ReturnsFalse() {
//        // Arrange
//        Bill bill = new Bill();
//        Object obj = new Object();
//
//        // Act
//        boolean result = bill.equals(obj);
//
//        // Assert
//        Assertions.assertFalse(result);
//    }
//
//    @Test
//     void testEquals_EqualBills_ReturnsTrue() {
//        // Arrange
//        Bill bill1 = new Bill();
//        Bill bill2 = new Bill();
//
//        // Act
//        boolean result = bill1.equals(bill2);
//
//        // Assert
//        Assertions.assertTrue(result);
//    }
//
//    @Test
//     void testEquals_DifferentIds_ReturnsFalse() {
//        // Arrange
//        Bill bill1 = new Bill();
//        Bill bill2 = new Bill();
//        bill2.setId(2);
//
//        // Act
//        boolean result = bill1.equals(bill2);
//
//        // Assert
//        Assertions.assertFalse(result);
//    }
//
//    @Test
//     void testEquals_DifferentTotal_ReturnsFalse() {
//        // Arrange
//        Bill bill1 = new Bill();
//        Bill bill2 = new Bill();
//        bill2.setTotal(99.99);
//
//        // Act
//        boolean result = bill1.equals(bill2);
//
//        // Assert
//        Assertions.assertFalse(result);
//    }


}
