import java.util.Arrays;

/**
 * Tic Tac Toe Winner
 * 
 * Task: Determine if there's a winner in a Tic Tac Toe game.
 * 
 * Given a 3x3 matrix representing a Tic Tac Toe board where:
 * - 0 represents an empty space
 * - 1 represents 'X'
 * - 2 represents 'O'
 * 
 * Return:
 * - 1 if 'X' has won
 * - 2 if 'O' has won
 * - 0 if there's no winner yet
 * - -1 if the board is full with no winner (draw)
 * 
 * Time Complexity: O(1) - constant time as we always check a 3x3 board
 * Space Complexity: O(1) - constant space regardless of input
 */
public class TicTacToeWinnerCheck {
    
    public static int solution(int[][] board) {
        // Check if the board is valid
        if (board == null || board.length != 3) {
            throw new IllegalArgumentException("Board must be a 3x3 matrix");
        }
        
        for (int i = 0; i < 3; i++) {
            if (board[i] == null || board[i].length != 3) {
                throw new IllegalArgumentException("Board must be a 3x3 matrix");
            }
        }
        
        // Check for a winner
        // Check rows
        for (int row = 0; row < 3; row++) {
            if (board[row][0] != 0 && board[row][0] == board[row][1] && board[row][0] == board[row][2]) {
                return board[row][0]; // Return the winner (1 for X, 2 for O)
            }
        }
        
        // Check columns
        for (int col = 0; col < 3; col++) {
            if (board[0][col] != 0 && board[0][col] == board[1][col] && board[0][col] == board[2][col]) {
                return board[0][col]; // Return the winner
            }
        }
        
        // Check diagonals
        if (board[0][0] != 0 && board[0][0] == board[1][1] && board[0][0] == board[2][2]) {
            return board[0][0]; // Return the winner
        }
        
        if (board[0][2] != 0 && board[0][2] == board[1][1] && board[0][2] == board[2][0]) {
            return board[0][2]; // Return the winner
        }
        
        // Check if the board is full (draw)
        boolean isFull = true;
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col] == 0) {
                    isFull = false;
                    break;
                }
            }
            if (!isFull) {
                break;
            }
        }
        
        // Return -1 if it's a draw, 0 if the game is still ongoing
        return isFull ? -1 : 0;
    }
    
    /**
     * Test solution with multiple test cases
     */
    public static void main(String[] args) {
        // Test case 1: X wins (row)
        int[][] board1 = {
            {1, 1, 1},
            {0, 2, 0},
            {2, 0, 2}
        };
        testSolution(board1, 1, "X wins (row)");
        
        // Test case 2: O wins (column)
        int[][] board2 = {
            {1, 2, 0},
            {0, 2, 1},
            {1, 2, 0}
        };
        testSolution(board2, 2, "O wins (column)");
        
        // Test case 3: X wins (diagonal)
        int[][] board3 = {
            {1, 0, 2},
            {0, 1, 2},
            {0, 0, 1}
        };
        testSolution(board3, 1, "X wins (diagonal)");
        
        // Test case 4: O wins (anti-diagonal)
        int[][] board4 = {
            {1, 1, 2},
            {0, 2, 1},
            {2, 0, 0}
        };
        testSolution(board4, 2, "O wins (anti-diagonal)");
        
        // Test case 5: No winner yet
        int[][] board5 = {
            {1, 0, 2},
            {0, 1, 0},
            {0, 0, 0}
        };
        testSolution(board5, 0, "No winner yet");
        
        // Test case 6: Draw
        int[][] board6 = {
            {1, 2, 1},
            {1, 2, 2},
            {2, 1, 1}
        };
        testSolution(board6, -1, "Draw");
        
        // Test case 7: Empty board
        int[][] board7 = {
            {0, 0, 0},
            {0, 0, 0},
            {0, 0, 0}
        };
        testSolution(board7, 0, "Empty board");
    }
    
    private static void testSolution(int[][] board, int expected, String description) {
        int result = solution(board);
        boolean passed = result == expected;
        
        System.out.println("Test: " + description);
        System.out.println("Board:");
        for (int[] row : board) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println("Expected: " + getResultDescription(expected));
        System.out.println("Got: " + getResultDescription(result));
        System.out.println("Result: " + (passed ? "PASSED" : "FAILED"));
        System.out.println();
    }
    
    private static String getResultDescription(int result) {
        switch (result) {
            case 1: return "X wins (1)";
            case 2: return "O wins (2)";
            case 0: return "No winner yet (0)";
            case -1: return "Draw (-1)";
            default: return "Unknown (" + result + ")";
        }
    }
}
