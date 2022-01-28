package sankar.learn.coding.problems.hackerrank;

import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

/**
 * There is a collection of input strings and a collection of query strings.
 * For each query string, determine how many times it occurs in the list of input strings.
 * Return an array of the results.
 *
 * Example
 * strings = [ab, ab, abc]
 * queries = [ab, abc, bc]
 *
 * There are 2 instances of ab, 1 of abc and 0 of bc.
 * For each query, add an element to the return array, results = [2,1,0]
 *
 * Function Description
 * Complete the function matchingStrings in the editor below.
 * The function must return an array of integers representing the frequency of occurrence of each query string in strings.
 * matchingStrings has the following parameters:
 *     string strings[n] - an array of strings to search
 *     string queries[q] - an array of query strings
 *
 * Returns
 *     int[q]: an array of results for each query
 *
 * Input Format
 * The first line contains an integer n, the size of strings[]
 * Each of the next n lines contains a string strings[i]
 * The next line contains q, the size of queries[]
 * Each of the next q lines contains a string queries[i]
 *
 * Constraints
 * 1 <= n <= 1000
 * 1 <= q <= 1000
 * 1 <= |strings[i]|, |queries[i]| <= 20
 *
 * Sample Input
 * 4
 * aba
 * baba
 * aba
 * xzxb
 * 3
 * aba
 * xzxb
 * ab
 *
 * Sample Output
 * 2
 * 1
 * 0
 */
public class SparseArray {
    public static void main(String[] args) {
        testInput();
    }

    static void inputFromConsole() {
        List<String> strings = null;
        List<String> queries = null;
        try (
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ) {
            strings = readInput(reader);
            queries = readInput(reader);
            Integer[] result = Solution.stringMatches(strings, queries);
            System.out.println(Arrays.asList(result));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static List<String> readInput(BufferedReader reader) throws IOException {
        List<String> input = new ArrayList<>();
        int inputSize = Integer.parseInt(reader.readLine().trim());
        for(int i=0; i < inputSize; i++) {
            input.add(reader.readLine().trim());
        }
        return Collections.unmodifiableList(input);
    }

    static void testInput() {
        List<String> strings = Arrays.asList("aba", "baba", "aba", "xzxb");
        List<String> queries = Arrays.asList("aba", "xzxb", "ab");
        Integer[] result = Solution.stringMatches(strings, queries);
        System.out.println(Arrays.asList(result));
    }
}

class Solution {
    public static Integer[] stringMatches(List<String> strings, List<String> queries) {
        //convert input string to maps that look up can be fast
        Map<String, Integer> stringsCount = new HashMap<>();
        for (String s : strings) {
            stringsCount.merge(s, 1, (oldV, newV) -> oldV + newV);
        }
        //get result for queries
        Integer[] result = new Integer[queries.size()];
        IntStream.range(0, queries.size()).forEach(i -> {
            result[i] = stringsCount.getOrDefault(queries.get(i), 0);
        });
        return result;
    }
}
