package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

 class AuthTransactionDtoTest {

    @Test
     void testBuild() {
        AuthTransactionDto authTransactionDto = AuthTransactionDto.builder()
                .idCustomer(1)
                .username("john_doe")
                .token("abc123")
                .build();

        assertEquals(1, authTransactionDto.getIdCustomer());
        assertEquals("john_doe", authTransactionDto.getUsername());
        assertEquals("abc123", authTransactionDto.getToken());
    }

    @Test
     void testToString() {
        AuthTransactionDto authTransactionDto = AuthTransactionDto.builder()
                .idCustomer(1)
                .username("john_doe")
                .token("abc123")
                .build();

        String expectedToString = "AuthTransactionDto(idCustomer=1, username=john_doe, token=abc123)";
        assertEquals(expectedToString, authTransactionDto.toString());
    }

    @Test
     void testIdCustomer() {
        AuthTransactionDto authTransactionDto = AuthTransactionDto.builder()
                .idCustomer(1)
                .username("john_doe")
                .token("abc123")
                .build();

        assertEquals(1, authTransactionDto.getIdCustomer());
    }

    @Test
     void testUsername() {
        AuthTransactionDto authTransactionDto = AuthTransactionDto.builder()
                .idCustomer(1)
                .username("john_doe")
                .token("abc123")
                .build();

        assertEquals("john_doe", authTransactionDto.getUsername());
    }

    @Test
     void testToken() {
        AuthTransactionDto authTransactionDto = AuthTransactionDto.builder()
                .idCustomer(1)
                .username("john_doe")
                .token("abc123")
                .build();

        assertEquals("abc123", authTransactionDto.getToken());
    }

     @Test
      void testEquals() {
         AuthTransactionDto dto1 = AuthTransactionDto.builder()
                 .idCustomer(1)
                 .username("john_doe")
                 .token("abc123")
                 .build();

         AuthTransactionDto dto2 = AuthTransactionDto.builder()
                 .idCustomer(1)
                 .username("john_doe")
                 .token("abc123")
                 .build();

         AuthTransactionDto dto3 = AuthTransactionDto.builder()
                 .idCustomer(2)
                 .username("jane_doe")
                 .token("def456")
                 .build();

         // Test equals
         assertEquals(dto1, dto2);
         assertNotEquals(dto1, dto3);
     }

     @Test
     void testHashCode() {
         AuthTransactionDto dto1 = AuthTransactionDto.builder()
                 .idCustomer(1)
                 .username("john_doe")
                 .token("abc123")
                 .build();

         AuthTransactionDto dto2 = AuthTransactionDto.builder()
                 .idCustomer(1)
                 .username("john_doe")
                 .token("abc123")
                 .build();

         assertEquals(dto1.hashCode(), dto2.hashCode());

         // Test consistent hash code
         assertEquals(dto1.hashCode(), dto1.hashCode());
         assertEquals(dto2.hashCode(), dto2.hashCode());

         // Test hash code for null fields
         AuthTransactionDto dto3 = AuthTransactionDto.builder()
                 .idCustomer(1)
                 .username("john_doe")
                 .build();

         assertNotEquals(dto1.hashCode(), dto3.hashCode());
//
         // Test hash code with different values
         AuthTransactionDto dto4 = AuthTransactionDto.builder()
                 .idCustomer(2)
                 .username("jane_doe")
                 .token("def456")
                 .build();

         assertNotEquals(dto1.hashCode(), dto4.hashCode());
     }


     @Test
      void testCanEqual() {
         AuthTransactionDto dto1 = AuthTransactionDto.builder()
                 .idCustomer(1)
                 .username("john_doe")
                 .token("abc123")
                 .build();

         AuthTransactionDto dto2 = AuthTransactionDto.builder()
                 .idCustomer(1)
                 .username("john_doe")
                 .token("abc123")
                 .build();

         assertTrue(dto1.canEqual(dto2));
     }

     @Test
      void testSetToken() {
         AuthTransactionDto dto = AuthTransactionDto.builder()
                 .idCustomer(1)
                 .username("john_doe")
                 .token("abc123")
                 .build();

         assertEquals("abc123", dto.getToken());

         dto.setToken("def456");

         assertEquals("def456", dto.getToken());
     }

    @Test
     void testAuthTransactionDtoBuilder() {
        AuthTransactionDto.AuthTransactionDtoBuilder builder = AuthTransactionDto.builder();

        assertNotNull(builder);
    }
     @Test
      void testBuilderToString() {
         AuthTransactionDto.AuthTransactionDtoBuilder builder = AuthTransactionDto.builder();

         String expectedToString = "AuthTransactionDto.AuthTransactionDtoBuilder(idCustomer=null, username=null, token=null)";
         assertEquals(expectedToString, builder.toString());

         // Set values
         builder.idCustomer(1)
                 .username("john_doe")
                 .token("abc123");

         expectedToString = "AuthTransactionDto.AuthTransactionDtoBuilder(idCustomer=1, username=john_doe, token=abc123)";
         assertEquals(expectedToString, builder.toString());
     }
}

