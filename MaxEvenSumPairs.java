public class MaxEvenSumPairs {

    // Function to determine the maximum number of neighboring pairs with even sums
    public static int solution(int[] A) {
        int n = A.length;

        // Edge case: If the array has less than 2 elements, return 0
        if (n < 2) {
            return 0;
        }

        boolean[] used = new boolean[n]; // Track used elements
        int maxPairs = 0;

        // Iterate through the array to form pairs
        for (int i = 0; i < n; i++) {
            // Skip if the current element is already used
            if (used[i]) {
                continue;
            }

            // Determine the next index (circular nature)
            int nextIndex = (i + 1) % n;

            // Skip if the next element is already used
            if (used[nextIndex]) {
                continue;
            }

            // Check if the sum of the pair is even
            if ((A[i] + A[nextIndex]) % 2 == 0) {
                // Form a pair and mark both elements as used
                maxPairs++;
                used[i] = true;
                used[nextIndex] = true;
            }
        }

        return maxPairs;
    }

    // Test function to verify the solution
    public static void testSolution() {
        // Test Case: {1, 3, 5, 2, 4, 7}
        int[] A = {1, 3, 5, 2, 4, 7};
        System.out.println("Test Case: " + (solution(A) == 2 ? "Passed" : "Failed"));
    }

    public static void main(String[] args) {
        testSolution();
    }
}

/**
 * Solution for finding maximum number of neighboring pairs with even sums in a circular array.
 * Each element can belong to at most one pair.
 */
public class CircularArrayPairs {
    
    /**
     * Finds the maximum number of neighboring pairs with even sums in a circular array.
     * A pair sum is even if both elements are even or both are odd (have same parity).
     * 
     * @param nums The input array of integers
     * @return The maximum number of pairs with even sums
     */
    public static int maxEvenSumPairs(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }
        
        int n = nums.length;
        int maxPairs = 0;
        
        // We need to try all possible starting positions to ensure we find the optimal pairing
        for (int startIdx = 0; startIdx < n; startIdx++) {
            boolean[] used = new boolean[n];
            int pairCount = 0;
            
            // Try forming pairs starting from startIdx
            for (int i = 0; i < n; i++) {
                int currentIdx = (startIdx + i) % n;
                int nextIdx = (currentIdx + 1) % n;
                
                // Skip if either element is already used
                if (used[currentIdx] || used[nextIdx]) continue;
                
                // Check if the pair has an even sum
                if ((nums[currentIdx] + nums[nextIdx]) % 2 == 0) {
                    used[currentIdx] = true;
                    used[nextIdx] = true;
                    pairCount++;
                }
            }
            
            // Update the maximum number of pairs found
            maxPairs = Math.max(maxPairs, pairCount);
        }
        
        return maxPairs;
    }
    
    /**
     * Tests the solution with various test cases.
     */
    public static void testSolution() {
        // Test case 1: Alternating even/odd pattern
        int[] test1 = {1, 2, 3, 4, 5, 6};
        int expected1 = 0; // No valid pairs (all adjacent elements have different parity)
        int result1 = maxEvenSumPairs(test1);
        System.out.println("Test 1: " + (result1 == expected1 ? "PASS" : "FAIL") + 
                           " - Expected: " + expected1 + ", Actual: " + result1);
        
        // Test case 2: All even numbers
        int[] test2 = {2, 4, 6, 8};
        int expected2 = 2; // All pairs have even sums (can pair 2-4 and 6-8)
        int result2 = maxEvenSumPairs(test2);
        System.out.println("Test 2: " + (result2 == expected2 ? "PASS" : "FAIL") + 
                           " - Expected: " + expected2 + ", Actual: " + result2);
        
        // Test case 3: All odd numbers
        int[] test3 = {1, 3, 5, 7};
        int expected3 = 2; // All pairs have even sums (can pair 1-3 and 5-7)
        int result3 = maxEvenSumPairs(test3);
        System.out.println("Test 3: " + (result3 == expected3 ? "PASS" : "FAIL") + 
                           " - Expected: " + expected3 + ", Actual: " + result3);
        
        // Test case 4: Mixed parity with clear optimal pairing
        int[] test4 = {1, 1, 2, 2, 3, 3};
        int expected4 = 3; // Can pair (1-1), (2-2), (3-3) for maximum 3 pairs
        int result4 = maxEvenSumPairs(test4);
        System.out.println("Test 4: " + (result4 == expected4 ? "PASS" : "FAIL") + 
                           " - Expected: " + expected4 + ", Actual: " + result4);
        
        // Test case 5: Edge case - array with 2 elements (same parity)
        int[] test5 = {1, 3};
        int expected5 = 1; // One valid pair
        int result5 = maxEvenSumPairs(test5);
        System.out.println("Test 5: " + (result5 == expected5 ? "PASS" : "FAIL") + 
                           " - Expected: " + expected5 + ", Actual: " + result5);
        
        // Test case 6: Edge case - empty array
        int[] test6 = {};
        int expected6 = 0; // No pairs possible
        int result6 = maxEvenSumPairs(test6);
        System.out.println("Test 6: " + (result6 == expected6 ? "PASS" : "FAIL") + 
                           " - Expected: " + expected6 + ", Actual: " + result6);
        
        // Test case 7: Edge case - single element
        int[] test7 = {5};
        int expected7 = 0; // No pairs possible
        int result7 = maxEvenSumPairs(test7);
        System.out.println("Test 7: " + (result7 == expected7 ? "PASS" : "FAIL") + 
                           " - Expected: " + expected7 + ", Actual: " + result7);
        
        // Test case 8: Circular consideration important
        int[] test8 = {1, 3, 5, 2, 4, 6};
        int expected8 = 2; // Optimal is to pair (1-3) and (2-4) or similar combinations
        int result8 = maxEvenSumPairs(test8);
        System.out.println("Test 8: " + (result8 == expected8 ? "PASS" : "FAIL") + 
                           " - Expected: " + expected8 + ", Actual: " + result8);
        
        // Test case 9: Another circular case where starting point matters
        int[] test9 = {2, 3, 4, 5};
        int expected9 = 0; // No valid pairs (all adjacent elements have different parity)
        int result9 = maxEvenSumPairs(test9);
        System.out.println("Test 9: " + (result9 == expected9 ? "PASS" : "FAIL") + 
                           " - Expected: " + expected9 + ", Actual: " + result9);
        
        // Test case 10: Case requiring optimal starting position
        int[] test10 = {1, 3, 5, 2, 4, 7};
        int expected10 = 3; // Optimal is to start from index 1: (3-5), (2-4), (7-1)
        int result10 = maxEvenSumPairs(test10);
        System.out.println("Test 10: " + (result10 == expected10 ? "PASS" : "FAIL") + 
                           " - Expected: " + expected10 + ", Actual: " + result10);
    }
    
    /**
     * Main method to run the solution and tests.
     */
    public static void main(String[] args) {
        testSolution();
        
        // Time Complexity Analysis:
        // O(n²) where n is the length of the array.
        // - We try all n possible starting positions.
        // - For each starting position, we iterate through the array once: O(n)
        // - Total: O(n) * O(n) = O(n²)
        
        // Space Complexity Analysis:
        // O(n) for the used[] array to track paired elements.
        
        // Note: While an O(n) solution would be desirable, for this specific problem
        // we need to try all possible starting positions to guarantee the optimal answer.
        // As shown by test case 10, choosing specific starting points like 0 or 1 
        // doesn't always yield the maximum number of pairs.
    }
}