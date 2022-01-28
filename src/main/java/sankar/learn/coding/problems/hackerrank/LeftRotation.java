package sankar.learn.coding.problems.hackerrank;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * A left rotation operation on an array of size n shifts each of the array's elements 1 unit to the left. Given an integer,
 * d, rotate the array that many steps left and return the result.
 *
 * Example
 * d = 2
 * arr = [1,2,3,4,5]
 * After 2 rotations,
 * arr' = [3,4,5,1,2]
 * .
 * Function Description
 * Complete the rotateLeft function in the editor below.
 * rotateLeft has the following parameters:
 *
 *     int d: the amount to rotate by
 *     int arr[n]: the array to rotate
 *
 * Returns
 *  int[n]: the rotated array
 *
 * Input Format
 *
 * The first line contains two space-separated integers that denote n, the number of integers, and d, the number of left rotations to perform.
 * The second line contains n space-separated integers that describe arr[]
 *
 * Constraints
 * . 1 <= n <= 10^5
 * . 1 <= d <= n
 * . 1 <= a[i] <= 10^6
 *
 * Sample Input
 * 5 4
 * 1 2 3 4 5
 *
 * Sample Output
 * 5 1 2 3 4
 *
 * Explanation
 *
 * To perform d= 4 left rotations, the array undergoes the following sequence of changes:
 * [1,2,3,4,5] -> [2,3,4,5,1] -> [3,4,5,1,2] -> [4,5,1,2,3] -> [5,1,2,3,4]
 */
public class LeftRotation {
    private static Integer[] rotate(int d, int[] arr) {
        Integer[] rotatedArr = new Integer[arr.length];
        IntStream.range(0, arr.length).forEach(idx -> {
            int newIdx = newLeftRotatedIndex(idx, d, arr.length);
            rotatedArr[newIdx] = arr[idx];
        });
        return rotatedArr;
    }

    private static int newLeftRotatedIndex(int index, int noOfLeftRotation, int arrSize) {
        int newIdx = index - noOfLeftRotation;
        if (newIdx < 0) {
            newIdx += arrSize;
        }
        return newIdx;
    }

    public static void main(String[] args) {
        int d = 4;
        int[] arr = new int[]{1,2,3,4,5};
        System.out.println(Arrays.asList(rotate(d, arr)));
    }
}
