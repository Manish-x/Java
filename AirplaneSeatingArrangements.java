/**
 * Solution for the airplane seating problem.
 * Maximizes the number of 4-person families that can be seated according to constraints.
 * 
 * @param N Number of rows in the airplane
 * @param S String containing reserved seats (e.g., "1A 3C 2B")
 * @return Maximum number of 4-person families that can be seated
 */
public int solution(int N, String S) {
    // Track which seats are already occupied
    boolean[][] isOccupied = new boolean[N][10];
    
    // Parse and mark all reserved seats
    if (S != null && !S.trim().isEmpty()) {
        String[] reservedSeats = S.split(" ");
        for (String seat : reservedSeats) {
            // Extract row number (1-indexed, so subtract 1 for 0-indexed array)
            int row = Integer.parseInt(seat.substring(0, seat.length() - 1)) - 1;
            
            // Extract seat letter
            char seatLetter = seat.charAt(seat.length() - 1);
            
            // Convert seat letter to column index (A=0, B=1, C=2, D=3, E=4, F=5, G=6, H=7, J=8, K=9)
            int col;
            if (seatLetter < 'I') {
                col = seatLetter - 'A';
            } else {
                // Skip 'I' since it's not used in seat labeling
                col = seatLetter - 'A' - 1;
            }
            
            // Mark the seat as occupied
            isOccupied[row][col] = true;
        }
    }
    
    int totalFamilies = 0;
    
    // Check each row for family seating possibilities
    for (int row = 0; row < N; row++) {
        // The three possible ways to seat a family:
        
        // 1. Left-middle cross arrangement: B,C + D,E (2 people on each side of first aisle)
        boolean isLeftCrossAvailable = true;
        // Check B,C seats (columns 1,2)
        if (isOccupied[row][1] || isOccupied[row][2]) {
            isLeftCrossAvailable = false;
        }
        // Check D,E seats (columns 3,4)
        if (isOccupied[row][3] || isOccupied[row][4]) {
            isLeftCrossAvailable = false;
        }
        
        // 2. Middle block: D,E,F,G (4 adjacent seats)
        boolean isMiddleAvailable = true;
        for (int col = 3; col <= 6; col++) {
            if (isOccupied[row][col]) {
                isMiddleAvailable = false;
                break;
            }
        }
        
        // 3. Right-middle cross arrangement: F,G + H,J (2 people on each side of second aisle)
        boolean isRightCrossAvailable = true;
        // Check F,G seats (columns 5,6)
        if (isOccupied[row][5] || isOccupied[row][6]) {
            isRightCrossAvailable = false;
        }
        // Check H,J seats (columns 7,8)
        if (isOccupied[row][7] || isOccupied[row][8]) {
            isRightCrossAvailable = false;
        }
        
        // Choose the optimal seating strategy for this row
        int familiesInThisRow = 0;
        
        // Strategy 1: Try to fit 2 families using both cross-aisle configurations
        if (isLeftCrossAvailable && isRightCrossAvailable) {
            familiesInThisRow = 2;
        }
        // Strategy 2: If we can't fit 2 families, try the middle block
        else if (isMiddleAvailable) {
            familiesInThisRow = 1;
        }
        // Strategy 3: If middle isn't available, try either of the cross-aisle configurations
        else if (isLeftCrossAvailable || isRightCrossAvailable) {
            familiesInThisRow = 1;
        }
        
        totalFamilies += familiesInThisRow;
    }
    
    return totalFamilies;
}

/**
 * Tests the solution with various test cases.
 * Simply call this method to run all tests and verify the solution.
 */
public static void testSolution() {
    // Create an instance to call the non-static solution method
    // If your solution method is static, you can remove this and call it directly
    Solution solver = new Solution();
    
    // Test cases with expected results
    int[][] testCases = {
        // {N, expected result, testCase number}
        {2, 2, 1},     // Test case 1: Example from problem statement
        {3, 6, 2},     // Test case 2: Empty plane
        {3, 3, 3},     // Test case 3: One seat occupied in each row
        {2, 0, 4},     // Test case 4: Multiple seats occupied in same row
        {1, 1, 5},     // Test case 5: Edge case - specific arrangements
        {2, 4, 6}      // Test case 6: Invalid seat specifications
    };
    
    // Reserved seats for each test case
    String[] reservedSeats = {
        "1A 2F 1C",         // Test case 1
        "",                  // Test case 2
        "1E 2B 3H",          // Test case 3
        "1B 1E 1H 2C 2G",    // Test case 4
        "1F",                // Test case 5
        "1Z 2X"              // Test case 6
    };
    
    // Run each test case
    int passedTests = 0;
    for (int i = 0; i < testCases.length; i++) {
        int N = testCases[i][0];
        int expected = testCases[i][1];
        int testCaseNum = testCases[i][2];
        String S = reservedSeats[i];
        
        int actual = solver.solution(N, S);
        boolean passed = (actual == expected);
        
        System.out.println("Test Case " + testCaseNum + ": " + 
                           (passed ? "PASSED" : "FAILED") + 
                           " (Expected: " + expected + ", Got: " + actual + ")");
        System.out.println("  N = " + N + ", Reserved Seats = \"" + S + "\"");
        
        if (!passed) {
            System.out.println("  ERROR: Expected " + expected + " but got " + actual);
        }
        
        if (passed) {
            passedTests++;
        }
        
        System.out.println(); // Empty line for better readability
    }
    
    // Summary
    System.out.println("Summary: " + passedTests + " out of " + testCases.length + " tests passed.");
    
    if (passedTests == testCases.length) {
        System.out.println("All tests passed! The solution appears to be correct.");
    } else {
        System.out.println("Some tests failed. Please review the solution.");
    }
}
