package jClient.utilities;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public class Identity {

    public static String generateId() {
        return generateId(8);
    }

    public static String generateId(int length) {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        // int targetStringLength = 8;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
