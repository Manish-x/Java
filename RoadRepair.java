/**
 * Solution to the Road Repair problem.
 * The task is to determine the minimum number of patches required to repair all potholes,
 * where each patch covers 3 consecutive segments of the road.
 */
public class RoadRepair {
    
    /**
     * Computes the minimum number of patches required to repair all potholes.
     * 
     * @param S A string representing the road, where 'X' denotes a pothole and '.' denotes a good segment.
     * @return The minimum number of patches required.
     */
    public static int solution(String S) {
        // Edge case: empty road or null input
        if (S == null || S.isEmpty()) {
            return 0;
        }
        
        char[] road = S.toCharArray();
        int patches = 0;
        
        // Traverse the road from left to right
        for (int i = 0; i < road.length; i++) {
            // If we find a pothole, apply a patch covering this and the next two segments
            if (road[i] == 'X') {
                patches++;
                
                // Mark the current and next two segments as repaired (if they exist)
                for (int j = i; j < Math.min(i + 3, road.length); j++) {
                    road[j] = '.';
                }
            }
        }
        
        return patches;
    }
    
    /**
     * Computes the minimum number of patches required using a more efficient approach
     * without modifying the input.
     * 
     * @param S A string representing the road, where 'X' denotes a pothole and '.' denotes a good segment.
     * @return The minimum number of patches required.
     */
    public static int solutionOptimized(String S) {
        // Edge case: empty road or null input
        if (S == null || S.isEmpty()) {
            return 0;
        }
        
        int patches = 0;
        int i = 0;
        int length = S.length();
        
        // Traverse the road from left to right
        while (i < length) {
            // If we find a pothole, apply a patch and skip the next two segments
            if (S.charAt(i) == 'X') {
                patches++;
                i += 3; // Skip the segments covered by this patch
            } else {
                i++; // Move to the next segment
            }
        }
        
        return patches;
    }
    
    /**
     * Main method with test cases to validate the solution.
     */
    public static void main(String[] args) {
        // Test cases for the solution
        testCase("", 0, "Empty road");
        testCase("....", 0, "No potholes");
        testCase("X...", 1, "Single pothole at the beginning");
        testCase("...X", 1, "Single pothole at the end");
        testCase("X", 1, "Single pothole only");
        testCase("XXXX", 2, "Four consecutive potholes");
        testCase("X...X...X", 3, "Potholes spaced far apart");
        testCase("XX.XX", 2, "Clustered potholes");
        testCase("X.X.X", 3, "Alternating pattern");
        testCase("XXXXX", 2, "Five consecutive potholes");
        testCase("X.X.X.X.X", 5, "Multiple alternating potholes");
        testCase("XXX.XXX.XXX", 3, "Groups of three potholes");
        testCase("X..X..X..X", 4, "Potholes with two good segments in between");
        
        // Large input test (1000 characters)
        StringBuilder largeSB = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            largeSB.append(i % 2 == 0 ? 'X' : '.');
        }
        String largeInput = largeSB.toString();
        testCase(largeInput, 334, "Large input with alternating pattern");
        
        // Very large input (100,000 characters, all potholes)
        String veryLargeInput = "X".repeat(100000);
        System.out.println("\nVery large input (100,000 potholes):");
        long startTime = System.nanoTime();
        int result = solutionOptimized(veryLargeInput);
        long endTime = System.nanoTime();
        System.out.println("Result: " + result + " (expected: 33334)");
        System.out.println("Time taken: " + ((endTime - startTime) / 1_000_000) + " ms");
    }
    
    /**
     * Helper method to run and validate test cases.
     * 
     * @param input The road string to test
     * @param expected The expected number of patches
     * @param description A description of the test case
     */
    private static void testCase(String input, int expected, String description) {
        int result = solutionOptimized(input);
        System.out.println("Test: " + description);
        System.out.println("Input: \"" + input + "\"");
        System.out.println("Result: " + result + ", Expected: " + expected);
        System.out.println("Test " + (result == expected ? "PASSED" : "FAILED") + "\n");
    }
}