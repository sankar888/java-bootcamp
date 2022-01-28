package sankar.learn.coding.problems.hackerrank;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * An array is a type of data structure that stores elements of the same type in a contiguous block of memory.
 * In an array, , of size , each memory location has some unique index, (where ), that can be referenced as or
 * Reverse an array of integers.
 *
 * Function Description
 * Complete the function reverseArray in the editor below.
 *
 * reverseArray has the following parameter(s):
 *     int A[n]: the array to reverse
 * Returns
 *     int[n]: the reversed array
 *
 * Input Format:
 * The first line contains an integer, the number of integers in the array
 * The second line contains space-separated integers that make up the input array
 * 4
 * 1 4 3 2
 *
 * output: 2 3 4 1
 */
public class ArraysDs {
    private static final Logger LOG = LoggerFactory.getLogger(ArraysDs.class);
    private static final String DELIMITER = " ";

    public static void main(String[] args) {
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int arrSize = Integer.parseUnsignedInt(reader.readLine());
            List<Integer> inputArr = Arrays.stream(reader.readLine().trim().split(DELIMITER))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            List<Integer> reversed = reverseArray(inputArr);
            System.out.println(reversed);
        } catch (IOException e) {
            LOG.error("Got exception while trying to read input values", e);
        }
    }

    private static List<Integer> reverseArray(List<Integer> inputArr) {
        //how to reverse an array
        //loop through an array,have a temp
        int length = inputArr.size();
        int loopUntil = length / 2;
        for (int i = 0; i < loopUntil; i++) {
            int temp = inputArr.get(i);
            int swapIndex = length - i - 1;
            inputArr.set(i, inputArr.get(swapIndex));
            inputArr.set(swapIndex, temp);
        }
        return inputArr;
    }
}
