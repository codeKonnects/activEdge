package com.example.activedge.activedge.othertests;

import java.util.Arrays;

public class SmallestNonOccurringInteger {
    public static int findSmallestNonOccurring(int[] array) {
        // Sort the array in ascending order
        Arrays.sort(array);

        int smallestNonOccurring = 1; // Initialize the smallest non-occurring integer to 1

        for (int num : array) {
            if (num > smallestNonOccurring) {
                return smallestNonOccurring; // Found the smallest non-occurring integer
            } else if (num == smallestNonOccurring) {
                // If the current number is equal to the smallest non-occurring integer, increment it
                smallestNonOccurring++;
            }
        }

        return smallestNonOccurring; // In case all elements are smaller than or equal to the smallestNonOccurring
    }

    public static void main(String[] args) {
        int[] array1 = {1, 3, 6, 4, 1, 2};
        int[] array2 = {5, -1, -3};

        System.out.println("Smallest non-occurring integer in Array1: " + findSmallestNonOccurring(array1)); // Output: 5
        System.out.println("Smallest non-occurring integer in Array2: " + findSmallestNonOccurring(array2)); // Output: 1
    }
}
