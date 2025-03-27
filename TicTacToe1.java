/*
You are tasked with implementing a function to determine the winner of a Tic-Tac-Toe game. The game is played on an N×N board, where N≥3. Players take turns marking a cell with their symbol ('X' or 'O'). The first player to align N consecutive symbols in a row, column, or diagonal wins the game.

Your task is to implement a function that:

Determines if there is a winner.
Returns the winning symbol ('X' or 'O') if a winner exists.
Returns 'D' (for "Draw") if the board is full and no winner exists.
Returns 'I' (for "Incomplete") if the game is still ongoing.
Input:
A 2D array board of size N×N, where each cell contains one of the following:
'X': Marked by Player 1.
'O': Marked by Player 2.
'.': Unmarked cell.
Output:
A single character:
'X' if Player 1 wins.
'O' if Player 2 wins.
'D' if the game is a draw.
'I' if the game is incomplete.
*/

public class TicTacToe {

    // Function to determine the winner of the Tic-Tac-Toe game
    public static char solution(char[][] board) {
        int n = board.length;

        // Check rows and columns
        boolean hasEmptyCell = false;
        for (int i = 0; i < n; i++) {
            // Check row i
            if (isUniform(board[i])) {
                return board[i][0];
            }

            // Check column i
            char[] column = new char[n];
            for (int j = 0; j < n; j++) {
                column[j] = board[j][i];
                if (board[j][i] == '.') {
                    hasEmptyCell = true;
                }
            }
            if (isUniform(column)) {
                return column[0];
            }
        }

        // Check main diagonal
        char[] mainDiagonal = new char[n];
        for (int i = 0; i < n; i++) {
            mainDiagonal[i] = board[i][i];
        }
        if (isUniform(mainDiagonal)) {
            return mainDiagonal[0];
        }

        // Check anti-diagonal
        char[] antiDiagonal = new char[n];
        for (int i = 0; i < n; i++) {
            antiDiagonal[i] = board[i][n - 1 - i];
        }
        if (isUniform(antiDiagonal)) {
            return antiDiagonal[0];
        }

        // Check for draw or incomplete game
        return hasEmptyCell ? 'I' : 'D';
    }

    // Helper function to check if all elements in an array are the same and not '.'
    private static boolean isUniform(char[] arr) {
        char first = arr[0];
        if (first == '.') {
            return false;
        }
        for (char c : arr) {
            if (c != first) {
                return false;
            }
        }
        return true;
    }

    // Test function to verify the solution
    public static void testSolution() {
        // Test Case 1: Player X wins in a row
        char[][] board1 = {
            {'X', 'X', 'X'},
            {'O', 'O', '.'},
            {'.', '.', '.'}
        };
        System.out.println("Test Case 1: " + (solution(board1) == 'X' ? "Passed" : "Failed"));

        // Test Case 2: Player O wins in a column
        char[][] board2 = {
            {'X', 'O', 'X'},
            {'X', 'O', '.'},
            {'.', 'O', '.'}
        };
        System.out.println("Test Case 2: " + (solution(board2) == 'O' ? "Passed" : "Failed"));

        // Test Case 3: Player X wins in the main diagonal
        char[][] board3 = {
            {'X', 'O', '.'},
            {'O', 'X', '.'},
            {'.', '.', 'X'}
        };
        System.out.println("Test Case 3: " + (solution(board3) == 'X' ? "Passed" : "Failed"));

        // Test Case 4: Draw
        char[][] board4 = {
            {'X', 'O', 'X'},
            {'X', 'X', 'O'},
            {'O', 'X', 'O'}
        };
        System.out.println("Test Case 4: " + (solution(board4) == 'D' ? "Passed" : "Failed"));

        // Test Case 5: Incomplete game
        char[][] board5 = {
            {'X', 'O', '.'},
            {'.', '.', '.'},
            {'.', '.', '.'}
        };
        System.out.println("Test Case 5: " + (solution(board5) == 'I' ? "Passed" : "Failed"));
    }

    public static void main(String[] args) {
        testSolution();
    }
}