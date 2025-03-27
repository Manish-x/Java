import java.util.Arrays;

/**
 * Codility Question: Tic Tac Toe Next Best Move
 * 
 * Task: Find the best next move for player 'X' in a Tic Tac Toe game.
 * 
 * Given a 3x3 matrix representing a Tic Tac Toe board where:
 * - 0 represents an empty space
 * - 1 represents 'X'
 * - 2 represents 'O'
 * 
 * Return an array [row, col] representing the best move coordinates for player 'X'.
 * The best move prioritizes:
 * 1. Winning the game (if possible)
 * 2. Blocking opponent from winning
 * 3. Taking the center if available
 * 4. Taking a corner if available
 * 5. Taking any available edge
 * 
 * If the board is full or player 'X' has already won, return [-1, -1].
 * 
 * Time Complexity: O(1) - constant time as we always check a 3x3 board
 * Space Complexity: O(1) - constant space regardless of input
 */
public class TicTacToeNextMove {
    
    public static int[] solution(int[][] board) {
        // Check if the board is valid
        if (board == null || board.length != 3) {
            throw new IllegalArgumentException("Board must be a 3x3 matrix");
        }
        
        for (int i = 0; i < 3; i++) {
            if (board[i] == null || board[i].length != 3) {
                throw new IllegalArgumentException("Board must be a 3x3 matrix");
            }
        }
        
        // Check if the game is already over
        if (hasWinner(board, 1) || hasWinner(board, 2) || isBoardFull(board)) {
            return new int[]{-1, -1};
        }
        
        // Priority 1: Check if X can win in the next move
        int[] winningMove = findWinningMove(board, 1); // 1 represents 'X'
        if (winningMove != null) {
            return winningMove;
        }
        
        // Priority 2: Check if X needs to block O from winning
        int[] blockingMove = findWinningMove(board, 2); // 2 represents 'O'
        if (blockingMove != null) {
            return blockingMove;
        }
        
        // Priority 3: Take the center if available
        if (board[1][1] == 0) {
            return new int[]{1, 1};
        }
        
        // Priority 4: Take a corner if available
        int[][] corners = {{0, 0}, {0, 2}, {2, 0}, {2, 2}};
        for (int[] corner : corners) {
            int row = corner[0];
            int col = corner[1];
            if (board[row][col] == 0) {
                return new int[]{row, col};
            }
        }
        
        // Priority 5: Take any available edge
        int[][] edges = {{0, 1}, {1, 0}, {1, 2}, {2, 1}};
        for (int[] edge : edges) {
            int row = edge[0];
            int col = edge[1];
            if (board[row][col] == 0) {
                return new int[]{row, col};
            }
        }
        
        // This should never happen if the board validation is correct
        return new int[]{-1, -1};
    }
    
    /**
     * Checks if the player has already won
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
     * Checks if the board is full
     */
    private static boolean isBoardFull(int[][] board) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col] == 0) {
                    return false;
                }
            }
        }
        return true;
    }
    
    /**
     * Finds a winning move for the specified player
     * Returns null if no winning move exists
     */
    private static int[] findWinningMove(int[][] board, int player) {
        // Check rows
        for (int row = 0; row < 3; row++) {
            if (countInRow(board, row, player) == 2 && countInRow(board, row, 0) == 1) {
                // Find the empty cell in this row
                for (int col = 0; col < 3; col++) {
                    if (board[row][col] == 0) {
                        return new int[]{row, col};
                    }
                }
            }
        }
        
        // Check columns
        for (int col = 0; col < 3; col++) {
            if (countInColumn(board, col, player) == 2 && countInColumn(board, col, 0) == 1) {
                // Find the empty cell in this column
                for (int row = 0; row < 3; row++) {
                    if (board[row][col] == 0) {
                        return new int[]{row, col};
                    }
                }
            }
        }
        
        // Check main diagonal
        if (countInMainDiagonal(board, player) == 2 && countInMainDiagonal(board, 0) == 1) {
            // Find the empty cell in the main diagonal
            for (int i = 0; i < 3; i++) {
                if (board[i][i] == 0) {
                    return new int[]{i, i};
                }
            }
        }
        
        // Check anti-diagonal
        if (countInAntiDiagonal(board, player) == 2 && countInAntiDiagonal(board, 0) == 1) {
            // Find the empty cell in the anti-diagonal
            for (int i = 0; i < 3; i++) {
                if (board[i][2 - i] == 0) {
                    return new int[]{i, 2 - i};
                }
            }
        }
        
        return null; // No winning move found
    }
    
    /**
     * Counts the number of cells with the specified value in a row
     */
    private static int countInRow(int[][] board, int row, int value) {
        int count = 0;
        for (int col = 0; col < 3; col++) {
            if (board[row][col] == value) {
                count++;
            }
        }
        return count;
    }
    
    /**
     * Counts the number of cells with the specified value in a column
     */
    private static int countInColumn(int[][] board, int col, int value) {
        int count = 0;
        for (int row = 0; row < 3; row++) {
            if (board[row][col] == value) {
                count++;
            }
        }
        return count;
    }
    
    /**
     * Counts the number of cells with the specified value in the main diagonal
     */
    private static int countInMainDiagonal(int[][] board, int value) {
        int count = 0;
        for (int i = 0; i < 3; i++) {
            if (board[i][i] == value) {
                count++;
            }
        }
        return count;
    }
    
    /**
     * Counts the number of cells with the specified value in the anti-diagonal
     */
    private static int countInAntiDiagonal(int[][] board, int value) {
        int count = 0;
        for (int i = 0; i < 3; i++) {
            if (board[i][2 - i] == value) {
                count++;
            }
        }
        return count;
    }
    
    /**
     * Test solution with multiple test cases
     */
    public static void main(String[] args) {
        // Test case 1: X can win in the next move (row)
        int[][] board1 = {
            {1, 1, 0},
            {0, 2, 0},
            {2, 0, 2}
        };
        testSolution(board1, new int[]{0, 2}, "X can win (row)");
        
        // Test case 2: X must block O from winning (column)
        int[][] board2 = {
            {1, 2, 0},
            {0, 1, 0},
            {0, 2, 0}
        };
        testSolution(board2, new int[]{0, 2}, "Block O from winning (column)");
        
        // Test case 3: Take center
        int[][] board3 = {
            {1, 0, 0},
            {0, 0, 0},
            {0, 0, 2}
        };
        testSolution(board3, new int[]{1, 1}, "Take center");
        
        // Test case 4: Center taken, take a corner
        int[][] board4 = {
            {0, 0, 0},
            {0, 2, 0},
            {0, 0, 1}
        };
        testSolution(board4, new int[]{0, 0}, "Take a corner");
        
        // Test case 5: Game is over, X has won
        int[][] board5 = {
            {1, 1, 1},
            {0, 2, 0},
            {0, 0, 2}
        };
        testSolution(board5, new int[]{-1, -1}, "Game over, X has won");
        
        // Test case 6: Game is over, draw
        int[][] board6 = {
            {1, 2, 1},
            {1, 2, 2},
            {2, 1, 1}
        };
        testSolution(board6, new int[]{-1, -1}, "Game over, draw");
        
        // Test case 7: X must block O from winning (diagonal)
        int[][] board7 = {
            {2, 0, 1},
            {0, 2, 0},
            {0, 0, 0}
        };
        testSolution(board7, new int[]{2, 0}, "Block O from winning (diagonal)");
        
        // Test case 8: X can win in the next move (diagonal)
        int[][] board8 = {
            {1, 0, 2},
            {0, 1, 0},
            {0, 0, 0}
        };
        testSolution(board8, new int[]{2, 2}, "X can win (diagonal)");
        
        // Test case 9: All corners and center taken, take an edge
        int[][] board9 = {
            {1, 0, 2},
            {0, 1, 0},
            {2, 0, 1}
        };
        testSolution(board9, new int[]{0, 1}, "Take an edge");
    }
    
    private static void testSolution(int[][] board, int[] expected, String description) {
        int[] result = solution(board);
        boolean passed = Arrays.equals(result, expected);
        
        System.out.println("Test: " + description);
        System.out.println("Board:");
        for (int[] row : board) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println("Expected: " + Arrays.toString(expected));
        System.out.println("Got: " + Arrays.toString(result));
        System.out.println("Result: " + (passed ? "PASSED" : "FAILED"));
        System.out.println();
    }
}