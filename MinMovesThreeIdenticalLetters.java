/**
 * Finds the minimum number of moves required to eliminate all instances
 * of three identical consecutive letters by swapping characters.
 * 
 * @param S A string consisting only of 'a' and/or 'b' characters
 * @return The minimum number of moves needed
 */
public int solution(String S) {
    // Handle edge cases
    if (S == null || S.length() < 3) {
        return 0; // No triplets possible in strings shorter than 3 characters
    }
    
    // Create a copy of the input string as a character array for manipulation
    char[] chars = S.toCharArray();
    int moves = 0;
    
    // Process the string from left to right
    for (int i = 0; i < chars.length - 2; i++) {
        // Check for triplet pattern (three consecutive identical letters)
        if (chars[i] == chars[i+1] && chars[i+1] == chars[i+2]) {
            // Determine optimal position to swap
            int swapIndex;
            
            // Look ahead to see if swapping the third character might create a new triplet
            if (i + 3 < chars.length && chars[i] == chars[i+3]) {
                // If the fourth character matches the triplet, swap the second character
                // This prevents creating a new triplet
                swapIndex = i+1;
            } else {
                // Otherwise, swap the third character
                swapIndex = i+2;
            }
            
            // Perform the swap (change 'a' to 'b' or 'b' to 'a')
            chars[swapIndex] = (chars[swapIndex] == 'a') ? 'b' : 'a';
            moves++;
        }
    }
    
    return moves;
}