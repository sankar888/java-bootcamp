package sankar.learn.coding.problems.hackerrank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Declare a two dimensional array, arr, of n empty arrays. All arrays are zero indexed.
 * Declare an integer, lastAnswer, and initialize it to 0.
 * There are 2 types of queries, given as an array of strings for you to parse:
 *
 * 1. Query 1 x y
 *    1. Let idx = ((x @ lastAnswer) % n)
 *    2. Append the integer y to arr[idx]
 *
 * 2. Query: 2 x y
 *    1. Let idx = ((x@ lastAnswer) %n)
 *    2. Assign the value arr[idx][y % size(arr[idx])] to lastAnswer
 *    3. Store the new value to lastAnswer to an answers array
 *
 *  Note: @ is the bitwise XOR operation, which corresponds to the ^ operator in most languages.
 *  Finally, size(arr[idx]) is the number of elements in arr[idx]
 *
 *  Input format
 *  The first line contains two space separated integers n, the size of arr to create and q the number of queries respectively.
 *  Each of the q subsequent lines contains a query string, queries[i]
 *
 *  Constrains:
 *  . 1 <=n, q <- 10^5
 *  . 0 <=x, y <= 10^9
 *  . it is guaranteed that the query type 2 will never query ans empty array or index
 *
 *  Sample Input
 *  2 5
 *  1 0 5
 *  1 1 7
 *  1 0 3
 *  2 1 0
 *  2 1 1
 *
 *  Sample Output
 *  7
 *  3
 */
public class DynamicArray {
    public static void main(String[] args) {

        try (
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

                ) {
            String[] inp = reader.readLine().split(" ");
            int n = Integer.parseInt(inp[0]);
            int q = Integer.parseInt(inp[1]);

            List<List<Integer>> queries = new ArrayList<>();
            for (int i = 0; i < q; i++) {
                List<Integer> query = Arrays.stream(reader.readLine().split(" "))
                        .map(Integer::parseInt)
                        .collect(Collectors.toList());
                queries.add(query);
            }
            Solution.solve(n, queries);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class Solution {
        public static void solve(int n, List<List<Integer>> queries) {
            int[][] arr = new int[n][n];
            int lastAnswer = 0;
            for (List<Integer> query : queries) {
                int queryType = query.get(0);
                int x = query.get(1);
                int y = query.get(2);
                if (queryType == 1) {
                    int idx = (x ^ lastAnswer) % n;

                } else if (queryType == 2) {

                } else {
                    throw new IllegalArgumentException("Unknown Query Type "+queryType);
                }
            }
        }
    }
}
