import java.util.HashMap;

class shortestUniqueSubstring {
    public int shortestUniqueSubstring(String S) {
        int n = S.length();
        
        // Check substrings of increasing lengths
        for (int length = 1; length <= n; length++) {
            HashMap<String, Integer> substrCount = new HashMap<>();
            
            // Count all substrings of current length
            for (int i = 0; i <= n - length; i++) {
                String substring = S.substring(i, i + length);
                substrCount.put(substring, substrCount.getOrDefault(substring, 0) + 1);
            }
            
            // Check for a substring with exactly one occurrence
            for (int count : substrCount.values()) {
                if (count == 1) {
                    return length;
                }
            }
        }
        
        return -1; // Return -1 if no unique substring exists (edge case)
    }

    // Testing the solution
    public static void main(String[] args) {
        shortestUniqueSubstring solution = new shortestUniqueSubstring();
        
        System.out.println(solution.shortestUniqueSubstring("abaaba")); // Output: 2
        System.out.println(solution.shortestUniqueSubstring("zyzyzyz")); // Output: 5
        System.out.println(solution.shortestUniqueSubstring("aabbbabaaa")); // Output: 3
    }
}
