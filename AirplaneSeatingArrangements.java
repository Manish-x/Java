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