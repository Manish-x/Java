/**
 * Determines the maximum number of slices into which array A can be divided,
 * such that after sorting each slice and joining them, the entire array is sorted.
 * 
 * @param A An array of distinct integers
 * @return The maximum number of possible slices
 */
public int solution(int[] A) {
    // Handle edge cases
    if (A == null || A.length == 0) {
        return 0; // Empty array has 0 slices
    }
    if (A.length == 1) {
        return 1; // Single element array always has 1 slice
    }
    
    int n = A.length;
    
    // Create a sorted copy of the array
    int[] sortedA = A.clone();
    Arrays.sort(sortedA);
    
    // Map each value to its position in the sorted array
    Map<Integer, Integer> positionMap = new HashMap<>();
    for (int i = 0; i < n; i++) {
        positionMap.put(sortedA[i], i);
    }
    
    // Count valid slice boundaries
    int slices = 0;
    int maxPositionSeen = -1;
    
    for (int i = 0; i < n; i++) {
        // Get the position this element should have in the sorted array
        int currentPosition = positionMap.get(A[i]);
        // Update the maximum position seen so far
        maxPositionSeen = Math.max(maxPositionSeen, currentPosition);
        
        // A valid slice boundary exists when the maximum position seen
        // equals the current index
        if (maxPositionSeen == i) {
            slices++;
        }
    }
    
    return slices;
}