import java.util.Arrays;

/**
 * Tic Tac Toe Game State Validation
 * 
 * Task: Check if a given Tic Tac Toe board represents a valid game state.
 * 
 * Given a 3x3 matrix representing a Tic Tac Toe board where:
 * - 0 represents an empty space
 * - 1 represents 'X'
 * - 2 represents 'O'
 * 
 * Return:
 * - true if the board represents a valid game state
 * - false if the board represents an impossible game state
 * 
 * A board state is valid if:
 * 1. The number of X's and O's is balanced (X's count - O's count is 0 or 1)
 * 2. If there's a winner, the game must have stopped at that point (no extra moves after win)
 * 3. There cannot be two winners (X and O cannot both have winning lines)
 * 
 * Time Complexity: O(1) - constant time as we always check a 3x3 board
 * Space Complexity: O(1) - constant space regardless of input
 */
public class TicTacToeGameStateValidation {
    
    public static boolean solution(int[][] board) {
        // Check if the board is valid
        if (board == null || board.length != 3) {
            throw new IllegalArgumentException("Board must be a 3x3 matrix");
        }
        
        for (int i = 0; i < 3; i++) {
            if (board[i] == null || board[i].length != 3) {
                throw new IllegalArgumentException("Board must be a 3x3 matrix");
            }
        }
        
        // Count X's and O's
        int countX = 0;
        int countO = 0;
        
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col] == 1) {
                    countX++;
                } else if (board[row][col] == 2) {
                    countO++;
                } else if (board[row][col] != 0) {
                    return false; // Invalid cell value
                }
            }
        }
        
        // Rule 1: Check if the number of X's and O's is balanced
        // X starts, so X's count - O's count should be 0 or 1
        if (countX - countO < 0 || countX - countO > 1) {
            return false;
        }
        
        // Check for winners
        boolean xWins = hasWinner(board, 1);
        boolean oWins = hasWinner(board, 2);
        
        // Rule 3: Both players cannot win
        if (xWins && oWins) {
            return false;
        }
        
        // Rule 2: If there's a winner, the game must have stopped at that point
        if (xWins && countX - countO != 1) {
            // If X wins, X must have placed the last move, so countX = countO + 1
            return false;
        }
        
        if (oWins && countX != countO) {
            // If O wins, it must be O's turn that ended the game, so countX = countO
            return false;
        }
        
        return true;
    }
    
    /**
     * Checks if the player has won
     */
    private static boolean hasWinner(int[][] board, int player) {
        // Check rows
        for (int row = 0; row < 3; row++) {
            if (board[row][0] == player && board[row][1] == player && board[row][2] == player) {
                return true;
            }
        }
        
        // Check columns
        for (int col = 0; col < 3; col++) {
            if (board[0][col] == player && board[1][col] == player && board[2][col] == player) {
                return true;
            }
        }
        
        // Check diagonals
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
            return true;
        }
        
        if (board[0][2] == player && board[1][1] == player && board[2][0] == player) {
            return true;
        }
        
        return false;
    }
    
    /**
     * Test solution with multiple test cases
     */
    public static void main(String[] args) {
        // Test case 1: Valid board - X's turn, balanced counts
        int[][] board1 = {
            {1, 0, 2},
            {0, 1, 0},
            {0, 0, 2}
        };
        testSolution(board1, true, "Valid board - X's turn, balanced counts");
        
        // Test case 2: Valid board - X wins
        int[][] board2 = {
            {1, 1, 1},
            {0, 2, 0},
            {0, 2, 0}
        };
        testSolution(board2, true, "Valid board - X wins");
        
        // Test case 3: Valid board - O wins
        int[][] board3 = {
            {1, 0, 2},
            {1, 0, 2},
            {0, 1, 2}
        };
        testSolution(board3, true, "Valid board - O wins");
        
        // Test case 4: Invalid board - too many X's
        int[][] board4 = {
            {1, 1, 1},
            {0, 1, 0},
            {0, 2, 2}
        };
        testSolution(board4, false, "Invalid board - too many X's");
        
        // Test case 5: Invalid board - too many O's
        int[][] board5 = {
            {1, 0, 2},
            {0, 2, 0},
            {0, 2, 2}
        };
        testSolution(board5, false, "Invalid board - too many O's");
        
        // Test case 6: Invalid board - both X and O win
        int[][] board6 = {
            {1, 1, 1},
            {2, 2, 2},
            {0, 0, 0}
        };
        testSolution(board6, false, "Invalid board - both X and O win");
        
        // Test case 7: Invalid board - X wins but O played after
        int[][] board7 = {
            {1, 1, 1},
            {0, 2, 0},
            {0, 2, 2}
        };
        testSolution(board7, false, "Invalid board - X wins but O played after");
        
        // Test case 8: Invalid board - O wins but X played after
        int[][] board8 = {
            {1, 0, 2},
            {1, 1, 2},
            {0, 0, 2}
        };
        testSolution(board8, false, "Invalid board - O wins but X played after");
        
        // Test case 9: Valid board - empty
        int[][] board9 = {
            {0, 0, 0},
            {0, 0, 0},
            {0, 0, 0}
        };
        testSolution(board9, true, "Valid board - empty");
        
        // Test case 10: Valid board - full, no winner (draw)
        int[][] board10 = {
            {1, 2, 1},
            {1, 1, 2},
            {2, 1, 2}
        };
        testSolution(board10, true, "Valid board - full, no winner (draw)");
    }
    
    private static void testSolution(int[][] board, boolean expected, String description) {
        boolean result = solution(board);
        boolean passed = result == expected;
        
        System.out.println("Test: " + description);
        System.out.println("Board:");
        for (int[] row : board) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println("Expected: " + expected);
        System.out.println("Got: " + result);
        System.out.println("Result: " + (passed ? "PASSED" : "FAILED"));
        System.out.println();
    }
}
