import java.util.Arrays;

public class ThreeWayArrayDivision {
    
    /**
     * Divide an array into three non-empty contiguous subarrays such that
     * the maximum difference between max and min elements in any group is minimized.
     * 
     * Time Complexity: O(N * log(MAX)) where MAX is the maximum possible difference
     * Space Complexity: O(1)
     * 
     * @param A The input array of integers
     * @return The minimum possible maximum difference
     */
    public static int minimumDifference(int[] A) {
        // Input validation
        if (A == null || A.length < 3) {
            throw new IllegalArgumentException("Array must have at least 3 elements");
        }
        
        // Find range for binary search
        int minElement = Integer.MAX_VALUE;
        int maxElement = Integer.MIN_VALUE;
        for (int num : A) {
            minElement = Math.min(minElement, num);
            maxElement = Math.max(maxElement, num);
        }
        
        // Binary search on the answer
        int left = 0;
        int right = maxElement - minElement;
        int result = right;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (isPossible(A, mid)) {
                result = mid;    // Found a potential answer, try to minimize it
                right = mid - 1;
            } else {
                left = mid + 1;  // Current value is too small, try larger
            }
        }
        
        return result;
    }
    
    /**
     * Check if it's possible to divide the array into three contiguous subarrays
     * such that the maximum difference in any group is at most 'maxDiff'.
     * 
     * @param A The input array
     * @param maxDiff The maximum allowed difference
     * @return true if possible, false otherwise
     */
    private static boolean isPossible(int[] A, int maxDiff) {
        int n = A.length;
        
        // Try all possible positions for the first cut
        for (int i = 1; i < n - 1; i++) {
            // Check if the first group [0...i-1] is valid
            if (!isValidGroup(A, 0, i - 1, maxDiff)) {
                continue; // First group is not valid, try next position
            }
            
            // Try all possible positions for the second cut
            for (int j = i + 1; j < n; j++) {
                // Check if the second group [i...j-1] is valid
                if (!isValidGroup(A, i, j - 1, maxDiff)) {
                    continue; // Second group is not valid, try next position
                }
                
                // Check if the third group [j...n-1] is valid
                if (isValidGroup(A, j, n - 1, maxDiff)) {
                    return true; // Found a valid division
                }
            }
        }
        
        return false; // No valid division found
    }
    
    /**
     * Check if a subarray has a max-min difference at most maxDiff.
     * 
     * @param A The input array
     * @param start Start index of the subarray (inclusive)
     * @param end End index of the subarray (inclusive)
     * @param maxDiff Maximum allowed difference
     * @return true if the difference is at most maxDiff, false otherwise
     */
    private static boolean isValidGroup(int[] A, int start, int end, int maxDiff) {
        if (start > end) return false;
        
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        
        for (int i = start; i <= end; i++) {
            min = Math.min(min, A[i]);
            max = Math.max(max, A[i]);
        }
        
        return (max - min) <= maxDiff;
    }
    
    /**
     * Test function
     */
    public static void testSolution() {
        // Example 1: A = [11, 5, 3, 12, 6, 8, 1, 7, 4]
        // Expected output: 3
        int[] example1 = {11, 5, 3, 12, 6, 8, 1, 7, 4};
        testCase(example1, 3, "Example 1");
        
        // Example 2: A = [10, 14, 12, 1000, 11, 15, 13, 1]
        // Expected output: 5
        int[] example2 = {10, 14, 12, 1000, 11, 15, 13, 1};
        testCase(example2, 5, "Example 2");
        
        // Example 3: A = [4, 5, 7, 10, 10, 12, 12, 12]
        // Expected output: 2
        int[] example3 = {4, 5, 7, 10, 10, 12, 12, 12};
        testCase(example3, 2, "Example 3");
        
        // Example 4: A = [5, 10, 10, 5, 5]
        // Expected output: 0
        int[] example4 = {5, 10, 10, 5, 5};
        testCase(example4, 0, "Example 4");
    }
    
    /**
     * Helper function to run a single test case.
     */
    private static void testCase(int[] arr, int expected, String testName) {
        int result = minimumDifference(arr);
        boolean passed = result == expected;
        
        System.out.println("Test: " + testName);
        System.out.println("Input: " + Arrays.toString(arr));
        System.out.println("Expected: " + expected + ", Got: " + result);
        System.out.println("Result: " + (passed ? "PASSED" : "FAILED"));
        System.out.println();
        
        if (!passed) {
            System.out.println("WARNING: Test case failed!");
            
            // Debug info for failed test case
            System.out.println("Debugging divisions for maxDiff = " + expected + ":");
            isPossible(arr, expected);
        }
    }
    
    public static void main(String[] args) {
        testSolution();
    }
}
