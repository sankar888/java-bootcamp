package sankar.learn.coding.problems.hackerrank;

import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Starting with a 1-indexed array of zeros and a list of operations, for each operation add a value to each the array element between two given indices, inclusive.
 * Once all operations have been performed, return the maximum value in the array.
 *
 * Example
 * n = 10
 * queries =[[1,5,3], [4,8,7], [6,9,1]]
 *
 * Queries are interpreted as follows:
 *     a b k
 *     1 5 3
 *     4 8 7
 *     6 9 1
 *
 * Add the values of k between the indices a and b inclusive:
 *
 *   1 2 3  4  5 6 7 8 9 10 (Index)
 * 	[0,0,0, 0, 0,0,0,0,0, 0]
 * 	[3,3,3, 3, 3,0,0,0,0, 0]
 * 	[3,3,3,10,10,7,7,7,0, 0]
 * 	[3,3,3,10,10,8,8,8,1, 0]
 *
 * The largest value is 10 after all operations are performed.
 *
 * Function Description
 * Complete the function arrayManipulation in the editor below. arrayManipulation has the following parameters:
 *     int n - the number of elements in the array
 *     int queries[q][3] - a two dimensional array of queries where each queries[i] contains three integers, a, b, and k.
 * Returns
 *     int - the maximum value in the resultant array
 *
 * Input Format
 * The first line contains two space-separated integers n and m, the size of the array and the number of operations.
 * Each of the next m lines contains three space-separated integers a,b and k the left index, right index and summand.
 *
 * Constraints
 * 1 <= m <= 2 * 10^5
 * 1 <= a <= b <= n
 * 0 <= k <= 10^9
 *
 * Sample Input
 * 5 3
 * 1 2 100
 * 2 5 100
 * 3 4 100
 *
 * Sample Output
 * 200
 *
 * Explanation
 * After the first update the list is 100 100 0 0 0.
 * After the second update list is 100 200 100 100 100.
 * After the third update list is 100 200 200 200 100.
 *
 * The maximum value is 200
 * .
 */
public class ArrayManipulation {
    public static void main(String[] args) {
        sampleInput();
    }

    static void sampleInput() {
        int n = 5;
        List<List<Integer>> queries = new ArrayList<>();
        queries.add(Arrays.asList(1, 2, 100));
        queries.add(Arrays.asList(2, 5, 100));
        queries.add(Arrays.asList(3, 5, 100));
        long res = solve(n, queries);
        Assertions.assertEquals(200, res);
    }

    private static long solve(int n, List<List<Integer>> queries) {
        long[] arr = new long[n+1];
        for(List<Integer> query : queries) {
            int fromInclusive = query.get(0);
            int toInclusive = query.get(1);
            for (int i = fromInclusive; i <= toInclusive; i++) {
                arr[i] = arr[i] + query.get(2);
            }
        }
        return findMaxInArr(arr);
    }

    private static void addToArr(long[] arr, int fromInclusive, int toInclusive, int value) {
        if (fromInclusive > toInclusive) {
            throw new IllegalArgumentException("From index should be less than to index");
        }
        for (int i = fromInclusive; i <= toInclusive; i++) {
            arr[i] = arr[i] + value;
        }
    }

    private static long findMaxInArr(long[] arr) {
        long max = 0;
        for (int i = 0; i < arr.length; i++) {
            long val = arr[i];
            if (val > max) {
                max = val;
            }
        }
        return max;
    }
}
