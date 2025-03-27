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
            
            if (canCreateThreeGroups(A, mid)) {
                result = mid;    // Found a potential answer, try to minimize it
                right = mid - 1;
            } else {
                left = mid + 1;  // Current value is too small, try larger
            }
        }
        
        return result;
    }
    
    /**
     * Check if the array can be divided into at least 3 groups with max difference <= maxDiff
     */
    private static boolean canCreateThreeGroups(int[] A, int maxDiff) {
        int groups = 1;  // Start with one group
        int min = A[0], max = A[0];
        
        for (int i = 1; i < A.length; i++) {
            // If adding this element would exceed maxDiff, start a new group
            if (Math.max(max, A[i]) - Math.min(min, A[i]) > maxDiff) {
                groups++;
                min = A[i];
                max = A[i];
            } else {
                min = Math.min(min, A[i]);
                max = Math.max(max, A[i]);
            }
            
            // Early termination when we find 3 groups
            if (groups >= 3) {
                return true;
            }
        }
        
        return groups >= 3;
    }
    
    /**
     * Test function
     */
    public static void testSolution() {
        int[][] testCases = {
            {11, 5, 3, 12, 6, 8, 1, 7, 4},        // Example 1 - Expected: 3
            {10, 14, 12, 1000, 11, 15, 13, 1},    // Example 2 - Expected: 5
            {4, 5, 7, 10, 10, 12, 12, 12},        // Example 3 - Expected: 2
            {5, 10, 10, 5, 5}                      // Example 4 - Expected: 0
        };
        int[] expected = {3, 5, 2, 0};
        
        for (int i = 0; i < testCases.length; i++) {
            int[] arr = testCases[i];
            int result = minimumDifference(arr);
            System.out.println("Test " + (i+1) + ": " + 
                    (result == expected[i] ? "PASSED" : "FAILED") + 
                    " (Expected: " + expected[i] + ", Got: " + result + ")");
        }
    }
    
    public static void main(String[] args) {
        testSolution();
    }
}