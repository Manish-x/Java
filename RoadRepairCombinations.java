public class RoadRepairCombinations {

    /**
     * Problem 1: Pothole Fixing with Two Roads
     * Repair both roads one at a time and calculate the total number of patches required.
     */
    public static int repairTwoRoads(String L1, String L2) {
        return countPatches(L1) + countPatches(L2);
    }

    /**
     * Helper method to count patches for a single road.
     */
    private static int countPatches(String road) {
        int patches = 0;
        int i = 0;
        while (i < road.length()) {
            if (road.charAt(i) == 'X') {
                patches++;
                i += 3; // Skip the next two segments
            } else {
                i++;
            }
        }
        return patches;
    }

    /**
     * Problem 2: Shared Patching Machine with Limited Capacity
     * Determine if it's possible to repair both roads within a given capacity K.
     */
    public static boolean canRepairWithinCapacity(String L1, String L2, int K) {
        int totalPatches = countPatches(L1) + countPatches(L2);
        return totalPatches <= K;
    }

    /**
     * Problem 3: Simultaneous Patching on Two Roads
     * Repair both roads simultaneously, allowing patches to cover overlapping segments.
     */
    public static int simultaneousPatching(String L1, String L2) {
        int patches = 0;
        int i = 0;
        while (i < Math.max(L1.length(), L2.length())) {
            boolean needsPatchL1 = (i < L1.length() && L1.charAt(i) == 'X');
            boolean needsPatchL2 = (i < L2.length() && L2.charAt(i) == 'X');

            if (needsPatchL1 || needsPatchL2) {
                patches++;
                i += 3; // Skip the next two segments
            } else {
                i++;
            }
        }
        return patches;
    }

    /**
     * Problem 4: Prioritizing One Road Over Another
     * Repair L1 first, then repair L2.
     */
    public static int prioritizeOneRoad(String L1, String L2) {
        return countPatches(L1) + countPatches(L2);
    }

    /**
     * Problem 5: Dynamic Patch Size
     * Repair both roads with a variable patch size P.
     */
    public static int dynamicPatchSize(String L1, String L2, int P) {
        return countPatchesWithSize(L1, P) + countPatchesWithSize(L2, P);
    }

    /**
     * Helper method to count patches for a single road with a dynamic patch size.
     */
    private static int countPatchesWithSize(String road, int P) {
        int patches = 0;
        int i = 0;
        while (i < road.length()) {
            if (road.charAt(i) == 'X') {
                patches++;
                i += P; // Skip P-1 segments
            } else {
                i++;
            }
        }
        return patches;
    }

    /**
     * Problem 6: Weighted Costs for Patches
     * Calculate the minimum total cost to repair both roads with different costs per patch.
     */
    public static int weightedCost(String L1, String L2, int C1, int C2) {
        int patchesL1 = countPatches(L1);
        int patchesL2 = countPatches(L2);
        return patchesL1 * C1 + patchesL2 * C2;
    }

    /**
     * Problem 7: Parallel Roads with Overlapping Segments
     * Repair both roads simultaneously, allowing patches to cover overlapping segments.
     */
    public static int parallelRoadsWithOverlap(String L1, String L2) {
        int patches = 0;
        int i = 0;
        while (i < Math.max(L1.length(), L2.length())) {
            boolean needsPatchL1 = (i < L1.length() && L1.charAt(i) == 'X');
            boolean needsPatchL2 = (i < L2.length() && L2.charAt(i) == 'X');

            if (needsPatchL1 || needsPatchL2) {
                patches++;
                i += 3; // Skip the next two segments
            } else {
                i++;
            }
        }
        return patches;
    }

    /**
     * Main method to test all the solutions.
     */
    public static void main(String[] args) {
        // Test inputs
        String L1 = ".X..X";
        String L2 = "X.X.X";

        // Problem 1: Pothole Fixing with Two Roads
        System.out.println("Problem 1: " + repairTwoRoads(L1, L2)); // Output: 4

        // Problem 2: Shared Patching Machine with Limited Capacity
        System.out.println("Problem 2: " + canRepairWithinCapacity(L1, L2, 5)); // Output: true

        // Problem 3: Simultaneous Patching on Two Roads
        System.out.println("Problem 3: " + simultaneousPatching(L1, L2)); // Output: 3

        // Problem 4: Prioritizing One Road Over Another
        System.out.println("Problem 4: " + prioritizeOneRoad(L1, L2)); // Output: 4

        // Problem 5: Dynamic Patch Size
        System.out.println("Problem 5: " + dynamicPatchSize(L1, L2, 4)); // Output: 3

        // Problem 6: Weighted Costs for Patches
        System.out.println("Problem 6: " + weightedCost(L1, L2, 2, 3)); // Output: 8

        // Problem 7: Parallel Roads with Overlapping Segments
        System.out.println("Problem 7: " + parallelRoadsWithOverlap(L1, L2)); // Output: 3
		
		// Edge Case 1: Empty Roads
    System.out.println("Edge Case 1: Empty Roads");
    System.out.println(repairTwoRoads("", "")); // Output: 0
    System.out.println(canRepairWithinCapacity("", "", 0)); // Output: true
    System.out.println(simultaneousPatching("", "")); // Output: 0
    System.out.println(prioritizeOneRoad("", "")); // Output: 0
    System.out.println(dynamicPatchSize("", "", 3)); // Output: 0
    System.out.println(weightedCost("", "", 2, 3)); // Output: 0
    System.out.println(parallelRoadsWithOverlap("", "")); // Output: 0

    // Edge Case 2: No Potholes
    String L1_noPotholes = "....";
    String L2_noPotholes = "....";
    System.out.println("Edge Case 2: No Potholes");
    System.out.println(repairTwoRoads(L1_noPotholes, L2_noPotholes)); // Output: 0
    System.out.println(canRepairWithinCapacity(L1_noPotholes, L2_noPotholes, 0)); // Output: true
    System.out.println(simultaneousPatching(L1_noPotholes, L2_noPotholes)); // Output: 0
    System.out.println(prioritizeOneRoad(L1_noPotholes, L2_noPotholes)); // Output: 0
    System.out.println(dynamicPatchSize(L1_noPotholes, L2_noPotholes, 3)); // Output: 0
    System.out.println(weightedCost(L1_noPotholes, L2_noPotholes, 2, 3)); // Output: 0
    System.out.println(parallelRoadsWithOverlap(L1_noPotholes, L2_noPotholes)); // Output: 0

    // Edge Case 3: Single Segment Roads
    String L1_singleSegment = "X";
    String L2_singleSegment = "X";
    System.out.println("Edge Case 3: Single Segment Roads");
    System.out.println(repairTwoRoads(L1_singleSegment, L2_singleSegment)); // Output: 2
    System.out.println(canRepairWithinCapacity(L1_singleSegment, L2_singleSegment, 1)); // Output: false
    System.out.println(simultaneousPatching(L1_singleSegment, L2_singleSegment)); // Output: 1
    System.out.println(prioritizeOneRoad(L1_singleSegment, L2_singleSegment)); // Output: 2
    System.out.println(dynamicPatchSize(L1_singleSegment, L2_singleSegment, 1)); // Output: 2
    System.out.println(weightedCost(L1_singleSegment, L2_singleSegment, 2, 3)); // Output: 5
    System.out.println(parallelRoadsWithOverlap(L1_singleSegment, L2_singleSegment)); // Output: 1

    // Edge Case 4: Mismatched Road Lengths
    String L1_long = "XXXXXXX";
    String L2_short = "X";
    System.out.println("Edge Case 4: Mismatched Road Lengths");
    System.out.println(repairTwoRoads(L1_long, L2_short)); // Output: 3 + 1 = 4
    System.out.println(canRepairWithinCapacity(L1_long, L2_short, 4)); // Output: true
    System.out.println(simultaneousPatching(L1_long, L2_short)); // Output: 3
    System.out.println(prioritizeOneRoad(L1_long, L2_short)); // Output: 4
    System.out.println(dynamicPatchSize(L1_long, L2_short, 4)); // Output: 2
    System.out.println(weightedCost(L1_long, L2_short, 2, 3)); // Output: 8
    System.out.println(parallelRoadsWithOverlap(L1_long, L2_short)); // Output: 3

    // Edge Case 5: Overlapping Potholes
    String L1_overlap = "X.X";
    String L2_overlap = "X.X";
    System.out.println("Edge Case 5: Overlapping Potholes");
    System.out.println(repairTwoRoads(L1_overlap, L2_overlap)); // Output: 2 + 2 = 4
    System.out.println(canRepairWithinCapacity(L1_overlap, L2_overlap, 3)); // Output: false
    System.out.println(simultaneousPatching(L1_overlap, L2_overlap)); // Output: 2
    System.out.println(prioritizeOneRoad(L1_overlap, L2_overlap)); // Output: 4
    System.out.println(dynamicPatchSize(L1_overlap, L2_overlap, 3)); // Output: 2
    System.out.println(weightedCost(L1_overlap, L2_overlap, 2, 3)); // Output: 10
    System.out.println(parallelRoadsWithOverlap(L1_overlap, L2_overlap)); // Output: 2

    // Edge Case 6: Dynamic Patch Size
    String L1_dynamic = "XXXX";
    String L2_dynamic = "XX";
    System.out.println("Edge Case 6: Dynamic Patch Size");
    System.out.println(dynamicPatchSize(L1_dynamic, L2_dynamic, 1)); // Output: 6
    System.out.println(dynamicPatchSize(L1_dynamic, L2_dynamic, 10)); // Output: 1

    // Edge Case 7: Weighted Costs
    String L1_weighted = "X.X";
    String L2_weighted = "X.X";
    System.out.println("Edge Case 7: Weighted Costs");
    System.out.println(weightedCost(L1_weighted, L2_weighted, 100, 1)); // Output: 202
    System.out.println(weightedCost(L1_weighted, L2_weighted, 1, 1)); // Output: 4

    // Edge Case 8: Large Inputs
    StringBuilder largeRoad = new StringBuilder();
    for (int i = 0; i < 1_000_000; i++) {
        largeRoad.append(i % 3 == 0 ? "X" : ".");
    }
    String L1_large = largeRoad.toString();
    String L2_large = largeRoad.toString();
    System.out.println("Edge Case 8: Large Inputs");
    System.out.println(repairTwoRoads(L1_large, L2_large)); // Output: ~666666 * 2
    }
	
}