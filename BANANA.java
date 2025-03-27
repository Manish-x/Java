public class MaxBananaCount {
    public static int maxBananaMoves(String S) {
        if (S == null || S.length() < 6) return 0; // Edge case: null or too short strings
        
        // Count the frequency of each character
        int[] charCounts = new int[26];
        for (char c : S.toCharArray()) {
            charCounts[c - 'A']++;
        }
        
        // Calculate the maximum number of times "BANANA" can be formed
        int countB = charCounts['B' - 'A'];
        int countA = charCounts['A' - 'A'];
        int countN = charCounts['N' - 'A'];

        // "BANANA" requires 1 'B', 3 'A's, and 2 'N's
        return Math.min(countB, Math.min(countA / 3, countN / 2));
    }

    public static void main(String[] args) {
        // Test cases
        System.out.println(maxBananaMoves("BANANABANANA")); // Output: 2
        System.out.println(maxBananaMoves("BANANA"));       // Output: 1
        System.out.println(maxBananaMoves("AAABBBNNN"));    // Output: 0
        System.out.println(maxBananaMoves(""));             // Output: 0
        System.out.println(maxBananaMoves(null));           // Output: 0
    }
}
