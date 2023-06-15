package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.core;

import java.security.SecureRandom;
import java.util.Random;

public class CodeGenerator {
    private static final String CHARSET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final int CODE_LENGTH = 10;
    private static final Random RANDOM = new SecureRandom();

    private CodeGenerator() {
        // This class is not meant to be instantiated!
    }

    public static String generate() {
        StringBuilder sb = new StringBuilder(CODE_LENGTH);
        for (int i = 0; i < CODE_LENGTH; i++) {
            sb.append(CHARSET.charAt(RANDOM.nextInt(CHARSET.length())));
        }
        return sb.toString();
    }
}
