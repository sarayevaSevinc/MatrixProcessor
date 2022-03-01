import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MatixProcessor matixProcessor = new MatixProcessor();
        String stringForMatrix = readStringFromConsole("Please enter the string that describes square matrix :");
        String word = readStringFromConsole("Please enter the word you want to search for:");
        System.out.println(matixProcessor.findPairs(stringForMatrix, word));
    }

    private static String readStringFromConsole(String message) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(message);
        return scanner.next().toUpperCase();
    }
}
