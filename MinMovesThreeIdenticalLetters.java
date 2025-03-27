public class MinMovesThreeIdenticalLetters {

    public int solution(String S) {
        // Handle edge cases
        if (S == null || S.length() < 3) {
            return 0; // No triplets possible in strings shorter than 3 characters
        }

        char[] chars = S.toCharArray();
        int moves = 0;

        // Traverse the string from left to right
        for (int i = 0; i < chars.length - 2; i++) {
            // Check for triplet pattern (three consecutive identical letters)
            if (chars[i] == chars[i + 1] && chars[i + 1] == chars[i + 2]) {
                // Perform a swap to break the triplet
                // Always swap the third character to simplify logic
                chars[i + 2] = (chars[i + 2] == 'a') ? 'b' : 'a';
                moves++;

                // Skip the next two characters to avoid reprocessing the same triplet
                i += 2;
            }
        }

        return moves;
    }

    public static void main(String[] args) {
        // Test cases
        Solution solution = new Solution();

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
        boolean allTestsPassed = true;
        for (int i = 0; i < testInputs.length; i++) {
            String input = testInputs[i];
            int expected = expectedOutputs[i];
            int result = solution.solution(input);

            System.out.println("Input: \"" + input + "\"");
            System.out.println("Expected Output: " + expected);
            System.out.println("Actual Output: " + result);
            if (result == expected) {
                System.out.println("✅ Test Passed");
            } else {
                System.out.println("❌ Test Failed");
                allTestsPassed = false;
            }
            System.out.println();
        }

        if (allTestsPassed) {
            System.out.println("🎉 All tests passed!");
        } else {
            System.out.println("⚠️ Some tests failed. Please review the implementation.");
        }
    }
}
