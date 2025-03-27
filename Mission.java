import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {
    public int solution(int[] D, int X) {
        int days = 0;
        int n = D.length;

        int i = 0; // Start from the first mission
        while (i < n) {
            days++;
            int currentMin = D[i];
            int currentMax = D[i];

            // Group missions for the current day
            while (i < n && currentMax - currentMin <= X) {
                currentMin = Math.min(currentMin, D[i]);
                currentMax = Math.max(currentMax, D[i]);
                if (currentMax - currentMin > X) {
                    break;
                }
                i++;
            }
        }

        return days;
    }
}
