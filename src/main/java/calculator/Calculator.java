package calculator;

public class Calculator {
    public int add(String input) {
        if (input == null || input.isBlank()) {
            return 0;
        }

        String delimiter = "[,:]";
        String numbers = input;

        if (input.startsWith("//")) {
            int index = input.indexOf("\\n");
            delimiter = input.substring(2, index);
            numbers = input.substring(index + 2);
        }
        String[] tokens = numbers.split(delimiter);
        int sum = 0;

        for (String token : tokens) {
            int number = Integer.parseInt(token);

            if (number < 0) {
                throw new IllegalArgumentException("음수는 허용되지 않습니다 : " + number);
            }

            sum += number;
        }

        return sum;
    }
}
