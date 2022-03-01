import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MatixProcessor {
    private static final int[] COLS = new int[] {0, 0, -1, 1};
    private static final int[] ROWS = new int[] {-1, 1, 0, 0};

    public String findPairs(String stringForMatrix, String word) {
        int dimension = (int) Math.sqrt(stringForMatrix.length());
        char[][] matrix = convertStringToArr(stringForMatrix);
        for (int row = 0; row < dimension; row++) {
            for (int col = 0; col < dimension; col++) {
                Optional<List<String>> optionalPair
                        = findChar(row, col, matrix, word, 0, new ArrayList<>());
                if (optionalPair.isPresent()) {
                    return String.join("->", optionalPair.get());
                }
            }
        }
        return "The matrix does not contain this word!";
    }

    static class Pair {
        private int x;
        private int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "[" + x + "," + y + "]";
        }
    }

    private static char[][] convertStringToArr(String str) {
        int size = (int) Math.sqrt(str.length());
        int index = 0;
        char arr[][] = new char[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                arr[i][j] = str.charAt(index++);
            }
        }
        return arr;
    }

    private Optional<List<String>> findChar(
            int row, int col, char[][] matrix, String word, int index, List<String> pair) {
        String seq = String.format("[%s,%s]", row, col);
        if (matrix[row][col] != word.charAt(index) || pair.contains(seq)) {
            return Optional.empty();
        }
        List<String> pairs = new ArrayList<>(pair);
        pairs.add(seq);
        if (pairs.size() == word.length()) {
            return Optional.of(pairs);
        }
        for (int i = 0; i < ROWS.length; i++) {
            int newRow = row + ROWS[i];
            int newCol = col + COLS[i];
            if (checkBorder(newRow, newCol, matrix.length)) {
                Optional<List<String>> optionalPair
                        = findChar(newRow, newCol, matrix, word, index + 1, pairs);
                if (optionalPair.isPresent()) {
                    return optionalPair;
                }
            }
        }
        return Optional.empty();
    }

    private boolean checkBorder(int nRow, int nCol, int dim) {
        return nRow >= 0 && nRow < dim && nCol >= 0 && nCol < dim;
    }
}
