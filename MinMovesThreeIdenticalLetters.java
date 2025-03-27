/**
 * Finds the minimum number of moves required to eliminate all instances
 * of three identical consecutive letters by swapping characters.
 * 
 * @param S A string consisting only of 'a' and/or 'b' characters
 * @return The minimum number of moves needed
 */
public int solution(String S) {
    if (S == null || S.length() < 3) {
        return 0; // No triplets possible in strings shorter than 3 characters
    }
    
    char[] chars = S.toCharArray();
    int moves = 0;
    
    for (int i = 0; i < chars.length - 2; i++) {
        if (chars[i] == chars[i+1] && chars[i+1] == chars[i+2]) {
            // Determine optimal position to swap
            int swapIndex;
            if (i + 3 < chars.length && chars[i] == chars[i+3]) {
                swapIndex = i+1; // Swap second character to avoid new triplet
            } else {
                swapIndex = i+2; // Swap third character
            }

            // Perform the swap
            chars[swapIndex] = (chars[swapIndex] == 'a') ? 'b' : 'a';
            moves++;
        }
    }
    
    return moves;
}

public class TestSolution {

    public static void main(String[] args) {
        // Create an instance of your solution class
        Solution solution = new Solution();

        // Define test cases
        String[] testInputs = {
            "baaaaa", "baaabbaabbba", "baabab", "aaa", "bbb", "ababab", "", "a", "aa",
            "aaaaaa", "bbbbbb", "abababababab", "aaaaaabbbbbb", "aaabbb", "aaaabbbb",
            "aaaaa", "bbbbb", "aaaaaaaaaaaa"
        };

        int[] expectedOutputs = {
            1, 2, 0, 1, 1, 0, 0, 0, 0,
            2, 2, 0, 4, 0, 2,
            2, 2, 4
        };

        // Run test cases
        for (int i = 0; i < testInputs.length; i++) {
            String input = testInputs[i];
            int expected = expectedOutputs[i];
            int result = solution.solution(input);

            // Print results
            System.out.println("Input: \"" + input + "\"");
            System.out.println("Expected Output: " + expected);
            System.out.println("Actual Output: " + result);
            System.out.println(result == expected ? "✅ Test Passed" : "❌ Test Failed");
            System.out.println();
        }
    }
}
