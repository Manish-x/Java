public class TableFormatter {
    public static void formatTable(int[] array, int K) {
        if (array == null || array.length == 0 || K <= 0) {
            System.out.println("Invalid input!");
            return;
        }

        // Find the maximum number of digits in the array
        int maxDigits = 0;
        for (int num : array) {
            maxDigits = Math.max(maxDigits, String.valueOf(num).length());
        }

        // Create the horizontal border
        String horizontalBorder = "+";
        for (int i = 0; i < K; i++) {
            horizontalBorder += "-".repeat(maxDigits) + "+";
        }

        // Display the formatted table
        int count = 0;
        System.out.println(horizontalBorder);
        for (int num : array) {
            if (count == 0) {
                System.out.print("|");
            }
            System.out.printf("%" + maxDigits + "d|", num);
            count++;
            if (count == K) {
                System.out.println();
                System.out.println(horizontalBorder);
                count = 0;
            }
        }
        if (count != 0) {
            while (count < K) {
                System.out.printf("%" + maxDigits + "s|", " ");
                count++;
            }
            System.out.println();
            System.out.println(horizontalBorder);
        }
    }

    public static void main(String[] args) {
        // Test cases
        System.out.println("Test Case 1:");
        formatTable(new int[]{123, 5, 6, 14}, 2);

        System.out.println("Test Case 2:");
        formatTable(new int[]{1, 22, 333, 4444, 55555, 666666}, 3);

        System.out.println("Test Case 3:");
        formatTable(new int[]{10, 20, 30}, 4); // Less than K numbers in the last row

        System.out.println("Test Case 4:");
        formatTable(new int[]{}, 3); // Empty array

        System.out.println("Test Case 5:");
        formatTable(null, 3); // Null input

        System.out.println("Test Case 6:");
        formatTable(new int[]{1, 2, 3}, 0); // Invalid K
    }
}
