package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core;

import id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core.CodeGenerator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CodeGeneratorTest {

    @Test
    public void testGenerate() {
        String code = CodeGenerator.generate();

        // Check if the generated code has the correct length
        assertEquals(10, code.length());

        // Check if the generated code only contains alphanumeric characters
        assertTrue(code.matches("^[a-zA-Z0-9]*$"));
    }
}
