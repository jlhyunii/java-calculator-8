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

            if (index == -1) {
                throw new IllegalArgumentException("잘못된 커스텀 구분자 형식입니다. '\\n'이 필요합니다.");
            }

            delimiter = input.substring(2, index);
            numbers = input.substring(index + 2);
        }

        else {
            if (numbers.contains(",,") || numbers.contains("::") ||
                    numbers.contains(",:") || numbers.contains(":,")) {
                throw new IllegalArgumentException("잘못된 구분자 형식입니다. 연속된 구분자가 존재합니다.");
            }

            if (numbers.startsWith(",") || numbers.startsWith(":") ||
                    numbers.endsWith(",") || numbers.endsWith(":")) {
                throw new IllegalArgumentException("잘못된 구분자 형식입니다. 입력이 구분자로 시작하거나 끝날 수 없습니다.");
            }

            if (!numbers.matches("^[-0-9,:a-zA-Z\\s]+$")) {
                throw new IllegalArgumentException("잘못된 커스텀 구분자 형식입니다. 기본 구분자(, :) 외의 문자가 포함되어 있습니다.");
            }
        }

        String[] tokens = numbers.split(delimiter);
        int sum = 0;

        for (String token : tokens) {
            if (token.isBlank()) {
                throw new IllegalArgumentException("빈 토큰이 존재합니다.");
            }

            if (!token.matches("-?\\d+")) {
                throw new IllegalArgumentException("숫자가 아닌 값이 포함되어 있습니다.");
            }

            int number = Integer.parseInt(token);

            if (number < 0) {
                throw new IllegalArgumentException("음수는 허용되지 않습니다.");
            }

            sum += number;
        }

        return sum;
    }
}
