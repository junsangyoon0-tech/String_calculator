package calculator;

import camp.nextstep.edu.missionutils.Console;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Application {

    // 숫자의 합
    static int add(String text) {
        if (text == null || text.isEmpty()) {
            return 0;
        }
        String[] values = split(text);
        int[] numbers = toInt(values);
        return sum(numbers);
    }

    // int[] 합 계산
    static int sum(int[] numbers) {
        int sum = 0;
        for (int n : numbers) sum += n;
        return sum;
    }

    // String[] -> int[]로 변환
    static int[] toInt(String[] values) {
        int[] numbers = new int[values.length];
        for (int i = 0; i < values.length; i++) {
            String s = values[i];
            int num = parsePositive(s);
            numbers[i] = num;
        }
        return numbers;
    }

    // 구분자를 기준으로 분리
    static private String[] split(String text) {
        // ^//(.) : // 다음 한 글자 커스텀 구분자
        // (?:\\\\n|\\n) : '리터럴 \n' 또는 '실제 개행' 모두 허용
        // (.*) : 그 뒤 본문 전부
        Matcher m = Pattern.compile("^//(.)(?:\\\\n|\\n)(.*)$").matcher(text);
        if (m.find()) {
            String customDelimiter = m.group(1);
            return m.group(2).split(customDelimiter);
        }
        return text.split(",|:");
    }
    // 예외 처리(음수 금지)
    private static int parsePositive(String s) {
        int number;
        try {
            number = Integer.parseInt(s);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자가 아닙니다: " + s);
        }
        if (number < 0) {
            throw new IllegalArgumentException("음수는 허용되지 않습니다: " + number);
        }
        return number;
    }

    public static void main(String[] args) {
        System.out.println("덧셈할 문자열을 입력해 주세요.");
        String input = Console.readLine();

        int result = add(input);

        System.out.println("결과 : " + result);
    }
}
