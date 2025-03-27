import java.util.Arrays;

public class ThreeWayArrayDivision {
    
    /**
     * Divide an array into three non-empty contiguous subarrays such that
     * the maximum difference between max and min elements in any group is minimized.
     * 
     * Time Complexity: O(N^2 * log(MAX)) where MAX is the maximum possible difference
     * Space Complexity: O(1)
     * 
     * @param A The input array of integers
     * @return The minimum possible maximum difference
     */
    public static int minimumDifference(int[] A) {
        int n = A.length;
        
        // Find range for binary search
        int minVal = Integer.MAX_VALUE;
        int maxVal = Integer.MIN_VALUE;
        for (int num : A) {
            minVal = Math.min(minVal, num);
            maxVal = Math.max(maxVal, num);
        }
        
        // Binary search on the answer
        int left = 0;
        int right = maxVal - minVal;
        int result = right;  // Default to maximum possible difference
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (canDivide(A, mid)) {
                result = mid;    // Found a potential answer, try to minimize it
                right = mid - 1; // Try to find a smaller threshold
            } else {
                left = mid + 1;  // Current threshold is too small, try larger
            }
        }
        
        return result;
    }
    
    /**
     * Check if it's possible to divide the array into three contiguous subarrays
     * such that the maximum difference in any group is at most 'threshold'.
     */
    private static boolean canDivide(int[] A, int threshold) {
        int n = A.length;
        
        // Try all possible first cuts
        for (int i = 1; i < n - 1; i++) {
            // Try all possible second cuts
            for (int j = i + 1; j < n; j++) {
                // Check if all three groups are valid with the given threshold
                if (isValidGroup(A, 0, i - 1, threshold) && 
                    isValidGroup(A, i, j - 1, threshold) && 
                    isValidGroup(A, j, n - 1, threshold)) {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    /**
     * Check if a subarray has a max-min difference at most 'threshold'.
     */
    private static boolean isValidGroup(int[] A, int start, int end, int threshold) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        
        for (int i = start; i <= end; i++) {
            min = Math.min(min, A[i]);
            max = Math.max(max, A[i]);
        }
        
        return (max - min) <= threshold;
    }
    
    /**
     * Test function to verify the solution
     */
    public static void testSolution() {
        // Example 1: [11, 5, 3, 12, 6, 8, 1, 7, 4]
        int[] example1 = {11, 5, 3, 12, 6, 8, 1, 7, 4};
        int result1 = minimumDifference(example1);
        System.out.println("Example 1: " + result1);
        
        // Example 2: [10, 14, 12, 1000, 11, 15, 13, 1]
        int[] example2 = {10, 14, 12, 1000, 11, 15, 13, 1};
        int result2 = minimumDifference(example2);
        System.out.println("Example 2: " + result2);
        
        // Example 3: [4, 5, 7, 10, 10, 12, 12, 12]
        int[] example3 = {4, 5, 7, 10, 10, 12, 12, 12};
        int result3 = minimumDifference(example3);
        System.out.println("Example 3: " + result3);
        
        // Example 4: [5, 10, 10, 5, 5]
        int[] example4 = {5, 10, 10, 5, 5};
        int result4 = minimumDifference(example4);
        System.out.println("Example 4: " + result4);
    }
    
    /**
     * Main method
     */
    public static void main(String[] args) {
        testSolution();
    }
}
