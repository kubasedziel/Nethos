package pl.nethos.rekrutacja.utils;

import java.util.stream.IntStream;

public class StringHelper {

    public static String formatAccountNumberIfValid(String number) {
        if (number.matches("[0-9]{26}")) {
            StringBuilder sb = new StringBuilder().append(number);
            IntStream.of(2, 7, 12, 17, 22, 27).forEach(i -> sb.insert(i, ' '));
            return sb.toString();
        }
        return number;
    }
}
