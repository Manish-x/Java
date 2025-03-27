class Solution {
    /**
     * Finds the minimum number of moves required to make each value X in the array appear exactly X times.
     * A move can be either removing an integer or inserting an integer.
     * 
     * @param A The input array sorted in non-decreasing order
     * @return The minimum number of moves required
     */
    public int solution(int[] A) {
        if (A == null || A.length == 0) {
            return 0; // Empty array already satisfies the condition
        }

        // Count frequencies of each element in the array
        java.util.Map<Integer, Integer> frequency = new java.util.HashMap<>();
        for (int num : A) {
            frequency.put(num, frequency.getOrDefault(num, 0) + 1);
        }
        
        // Calculate the minimum moves needed
        int moves = 0;
        
        // Analyze each value in the array
        for (java.util.Map.Entry<Integer, Integer> entry : frequency.entrySet()) {
            int value = entry.getKey();
            int count = entry.getValue();
            
            // Special case: value 0 should appear 0 times, so remove all
            if (value == 0) {
                moves += count;
                continue;
            }
            
            // For each value, we have two options:
            // 1. Adjust the count to match the value exactly 
            // 2. Remove all occurrences of this value
            
            // Option 1: Make exactly 'value' occurrences
            int option1 = Math.abs(value - count); // Add/remove to get exactly 'value' occurrences
            
            // Option 2: Remove all occurrences
            int option2 = count;
            
            // Choose the option with fewer moves
            moves += Math.min(option1, option2);
        }
        
        return moves;
    }
    
    /**
     * Main method to test the solution with all the examples from the problem statement
     */
    public static void main(String[] args) {
        Solution sol = new Solution();
        
        // Test Example 1: A=[1,1,3,4,4,4]
        int[] example1 = {1, 1, 3, 4, 4, 4};
        int result1 = sol.solution(example1);
        System.out.println("Example 1: A=[1,1,3,4,4,4]");
        System.out.println("Result: " + result1);
        System.out.println("Expected: 3");
        System.out.println("Test " + (result1 == 3 ? "PASSED" : "FAILED") + "\n");
        
        // Test Example 2: A=[1,2,2,2,5,5,5,8]
        int[] example2 = {1, 2, 2, 2, 5, 5, 5, 8};
        int result2 = sol.solution(example2);
        System.out.println("Example 2: A=[1,2,2,2,5,5,5,8]");
        System.out.println("Result: " + result2);
        System.out.println("Expected: 4");
        System.out.println("Test " + (result2 == 4 ? "PASSED" : "FAILED") + "\n");
        
        // Test Example 3: A=[1,1,1,1,3,3,4,4,4,4,4]
        int[] example3 = {1, 1, 1, 1, 3, 3, 4, 4, 4, 4, 4};
        int result3 = sol.solution(example3);
        System.out.println("Example 3: A=[1,1,1,1,3,3,4,4,4,4,4]");
        System.out.println("Result: " + result3);
        System.out.println("Expected: 5");
        System.out.println("Test " + (result3 == 5 ? "PASSED" : "FAILED") + "\n");
        
        // Test Example 4: A=[10,10,10]
        int[] example4 = {10, 10, 10};
        int result4 = sol.solution(example4);
        System.out.println("Example 4: A=[10,10,10]");
        System.out.println("Result: " + result4);
        System.out.println("Expected: 3");
        System.out.println("Test " + (result4 == 3 ? "PASSED" : "FAILED") + "\n");
        
        // Additional test cases
        
        // Empty array
        int[] emptyArray = {};
        int resultEmpty = sol.solution(emptyArray);
        System.out.println("Empty Array Test: A=[]");
        System.out.println("Result: " + resultEmpty);
        System.out.println("Expected: 0");
        System.out.println("Test " + (resultEmpty == 0 ? "PASSED" : "FAILED") + "\n");
        
        // Array with zeros
        int[] zeroArray = {0, 0, 1, 1, 2};
        int resultZero = sol.solution(zeroArray);
        System.out.println("Zero Array Test: A=[0,0,1,1,2]");
        System.out.println("Result: " + resultZero);
        System.out.println("Expected: 3"); // Remove 2 zeros and remove 1 occurrence of value 1
        System.out.println("Test " + (resultZero == 3 ? "PASSED" : "FAILED") + "\n");
        
        // Large value single occurrence
        int[] largeValueArray = {100000};
        int resultLarge = sol.solution(largeValueArray);
        System.out.println("Large Value Test: A=[100000]");
        System.out.println("Result: " + resultLarge);
        System.out.println("Expected: 1"); // Remove the large value (1 move)
        System.out.println("Test " + (resultLarge == 1 ? "PASSED" : "FAILED") + "\n");
    }
}