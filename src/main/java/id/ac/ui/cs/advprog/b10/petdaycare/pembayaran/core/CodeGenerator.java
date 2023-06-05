package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core;

import java.util.Random;

public class CodeGenerator {

    private CodeGenerator() {
        // This class is not meant to be instantiated!
    }

    public static String generate() {
        // Taken from : https://www.baeldung.com/java-random-string
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
