package pricticum_structures.sprint3;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Combination {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[] input = scanner.nextLine().toCharArray();
        List<String> result = new ArrayList<>();
        getCombinationRecursion(result, new StringBuilder(), 0, input);
        result.forEach(x -> System.out.print(x + " "));
    }

    private static void getCombinationRecursion(List<String> arr, StringBuilder builder, int index, char[] sequence) {
        if (index == sequence.length) {
            arr.add(builder.toString());
            return;
        }
        char digit = sequence[index];
        char[] digitSeq = getCharSequence(digit);

        for (char elem : digitSeq) {
            builder.append(elem);
            getCombinationRecursion(arr, builder, index + 1, sequence);
            builder.deleteCharAt(builder.length() - 1);
        }
    }


    private static char[] getCharSequence(char digit) {
        switch (digit) {
            case '2': {
                return new char[]{'a', 'b', 'c'};
            }
            case '3': {
                return new char[]{'d', 'e', 'f'};
            }
            case '4': {
                return new char[]{'g', 'h', 'i'};
            }
            case '5': {
                return new char[]{'j', 'k', 'l'};
            }
            case '6': {
                return new char[]{'m', 'n', 'o'};
            }
            case '7': {
                return new char[]{'p', 'q', 'r', 's'};
            }
            case '8': {
                return new char[]{'t', 'u', 'v'};
            }
            case '9': {
                return new char[]{'w', 'x', 'y', 'z'};
            }
            default: {
                return new char[]{};
            }
        }
    }
}
