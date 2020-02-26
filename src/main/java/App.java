/**
 * Дана строка, состоящая из букв A-Z:
 * "AAAABBBCCXYZDDDDEEEFFFAAAAAABBBBBBBBBBBBBBBBBBBBBBBBBBBB"
 * Нужно написать функцию RLE, которая на выходе даст строку вида:
 * "A4B3C2XYZD4E3F3A6B28"
 * И сгенерирует любую ошибку, если на вход пришла невалидная строка.
 *
 * Пояснения:
 * 1. Если символ встречается 1 раз, он остается без изменений
 * 2. Если символ повторяется более 1 раза, к нему добавляется количество повторений
 */

public class App {
    public static void main(String[] args) {
        test("A4B3C2XYZD4E3F3A6B28", "AAAABBBCCXYZDDDDEEEFFFAAAAAABBBBBBBBBBBBBBBBBBBBBBBBBBBB");
        test("A3", "AAA");
        test("A", "A");
        test("A3R3BC2", "AAARRRBCC");
        test("", "");
        test("", null);
        testException( "A1B1");
        testException( "A%D");
        testException( "aaa");
    }

    public static void test(String expected, String raw) {
        String res = rle(raw);
        System.out.println(res + " eq = " + (expected.equals(res)));
    }

    public static void testException(String raw){
        try {
            rle(raw);
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }

    public static String rle(String str) {
        if (str == null || str.length() == 0) {
            return "";
        }
        char currVal = str.charAt(0);
        checkLetter(String.valueOf(currVal));
        int count = 1;
        StringBuilder res = new StringBuilder();
        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i) == currVal) {
                count++;
            } else {
                append(currVal, count, res);
                count = 1;
                currVal = str.charAt(i);
                checkLetter(String.valueOf(currVal));
            }
        }

        append(currVal, count, res);
        return res.toString();
    }

    private static void append(char currVal, int count, StringBuilder res) {
        if (count == 1) {
            res.append(currVal);
        } else {
            res.append(currVal).append(count);
        }
    }

    public static void checkLetter(String letter) {
        if (!letter.matches("^[A-Z]")) {
            throw new RuntimeException("Invalid letter has been provided");
        }
    }
}

