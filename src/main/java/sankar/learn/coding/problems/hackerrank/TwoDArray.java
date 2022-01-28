package sankar.learn.coding.problems.hackerrank;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Given a 2D Array (6x6),
 * 1 1 1 0 0 0
 * 0 1 0 0 0 0
 * 1 1 1 0 0 0
 * 0 0 0 0 0 0
 * 0 0 0 0 0 0
 * 0 0 0 0 0 0
 *
 * An hourglass is a subset of values with indices falling in this pattern in
 * graphical representation:
 * a b c
 *   d
 * e f g
 *
 * There are 16 hourglasses in arr. An hourglass sum is the sum of an hourglass' values. Calculate the hourglass sum for every hourglass in ,
 * then print the maximum hourglass sum. The array will always be
 * Example
 *
 * -9 -9 -9  1 1 1
 *  0 -9  0  4 3 2
 * -9 -9 -9  1 2 3
 *  0  0  8  6 6 0
 *  0  0  0 -2 0 0
 *  0  0  1  2 4 0
 *
 * The hourglass sums are:
 *
 * -63, -34, -9, 12,
 * -10,   0, 28, 23,
 * -27, -11, -2, 10,
 *   9,  17, 25, 18
 *
 * The highest hourglass sum is 28
 * from the hourglass beginning at row 1 , column 2
 *
 * 0 4 3
 *   1
 * 8 6 6
 *
 *
 * Input
 * int arr[6][6]: an array of integers
 *
 * Returns
 *  int: the maximum hourglass sum
 *
 * Input Format
 * Each of the
 * lines of inputs contains space-separated integers
 *
 * Constraints
 * -9 <= arr[i][j] <=9
 * 0 <= i,j <= 5
 *
 * Output Format
 * Print the largest (maximum) hourglass sum found in arr
 * Sample Input
 *
 * 1 1 1 0 0 0
 * 0 1 0 0 0 0
 * 1 1 1 0 0 0
 * 0 0 2 4 4 0
 * 0 0 0 2 0 0
 * 0 0 1 2 4 0
 *
 * Sample Output
 * 19
 *
 */
public class TwoDArray {
    private static final Logger LOG = LoggerFactory.getLogger(TwoDArray.class);

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int arrSize = 6;
            Integer[][] arr = new Integer[6][6];
            for (int i = 0; i < arrSize; i++) {
                arr[i] = Arrays.stream(reader.readLine().trim().replaceAll("\\s+$", "").split(" "))
                        .map(Integer::parseInt).toArray(Integer[]::new);
            }
            int res = TwoDArray.maxHourGlassSum(arr);
            LOG.info("The maximum hour glass sum is {}", res);
        } catch (IOException e) {
            LOG.error("Exception while reading input from console", e);
        }
    }

    private static int maxHourGlassSum(Integer[][] arr) {
        LOG.info("got input arr, {}", Arrays.asList(arr));
        List<Integer> hourGlassesSum = new ArrayList<>();
        int arrSize = arr.length;
        int loopUtil = arrSize - 2;
        for (int i = 1; i <= loopUtil; i++) {
            for (int j = 1; j <= loopUtil; j++) {
                hourGlassesSum.add(hourGlassSumWithCenterIndex(arr, i, j));
            }
        }
        LOG.info("the sum of all hourglass {}", hourGlassesSum);
        return Collections.max(hourGlassesSum);
    }

    private static int hourGlassSumWithCenterIndex(Integer[][] arr, int i, int j) {
        int topRow = i - 1;
        int bottomRow = i + 1;
        return arr[topRow][j - 1] + arr[topRow][j] + arr[topRow][j + 1]
                + arr[i][j]
                + arr[bottomRow][j - 1] + arr[bottomRow][j] + arr[bottomRow][j + 1];
    }

}
