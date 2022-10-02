package asciimirror;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static String reverseString(String str) {
        if (str == null) {
            return null;
        }
        String result = "";
        for (int i = str.length() - 1; i >= 0; i--) {
            result = switch (str.charAt(i)) {
                case '<' -> result + '>';
                case '[' -> result + ']';
                case '{' -> result + '}';
                case '(' -> result + ')';
                case '/' -> result + '\\';
                case '>' -> result + '<';
                case ']' -> result + '[';
                case '}' -> result + '{';
                case ')' -> result + '(';
                case '\\' -> result + '/';
                default -> result + str.charAt(i);
            };
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> result = new ArrayList<>();
        int longestString = 0;

        System.out.println("Input the file path:");

        String file = scanner.nextLine();

        try (Scanner scan = new Scanner(Paths.get(file))) {
            while (scan.hasNext()) {
                result.add(scan.nextLine());
            }
        } catch (Exception e) {
            System.out.println("File not found!");
        }

        for (String line : result) {
            if (line.length() > longestString) {
                longestString = line.length();
            }
        }

        for (String line : result) {
            String firstPart = String.format("%1$-" + longestString + "s", line);
            String secondPart = String.format("%1$" + longestString + "s", reverseString(line));

            System.out.println(firstPart + " | " + secondPart);
        }
    }
}