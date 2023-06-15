package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CodeGeneratorTest {

    @Test
    void generate_ShouldReturnRandomCodeWithLength10() {
        // Act
        String code = CodeGenerator.generate();

        // Assert
        assertNotNull(code);
        assertEquals(10, code.length());
    }

    @Test
    void generate_ShouldNotReturnNull() {
        // Act
        String code = CodeGenerator.generate();

        // Assert
        assertNotNull(code);
    }
}
