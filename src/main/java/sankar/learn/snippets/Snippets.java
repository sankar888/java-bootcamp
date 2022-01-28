package sankar.learn.snippets;

import org.junit.jupiter.api.Test;
import sankar.learn.datastructures.tree.BinaryMaxHeap;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Snippets {

    @Test
    public void testArrListInsertion() {
        List<Integer> list = new ArrayList<>(Arrays.asList(1,2,3,4,5,6));
        list.add(0, 0);
        System.out.println(list);
        //if an element e is inserted ar index i Arraylist shifts all elements right of index i by one step
    }

    @Test
    public void testArrayInsertion() {
        int[] arr = {1,2,3,4,5,6};
        arr[0] = 0;
        System.out.println(Arrays.toString(arr));
    }

    @Test
    public void printBinaryHeap() {
        int[] arr = {7,5,3,2,1,0,-1,-2,-3,-3,-4,-5,-6,-7,-8, -9};
        for (int i=0; i < arr.length; i=(2*i)+1) {
            for (int j=i; j < (2*i)+1 && j < arr.length; j++) {
                System.out.printf("%s\t",arr[j]);
            }
            System.out.println("");
        }
    }

    @Test
    public void testBinaryHeap() {
        List<Integer> list = Arrays.asList(1,2,10,4,5,6,8);
        BinaryMaxHeap<Integer> tree = new BinaryMaxHeap<>(list);
        System.out.println(tree.toString());
        System.out.println(tree.size());
        System.out.println(tree.isEmpty());
        System.out.println(tree.peekMax());
    }

    @Test
    public void testParentIndexFindingAlgo() {
        System.out.println(0%2);
        int res = 1/2;
        System.out.println(res);
    }

    @Test
    public void testDivision() {
        int res = 7/2;
        System.out.println(res);
        System.out.printf("4/2 = %d \n", 4/2);
        System.out.printf("5/2 = %d \n", 5/2);
    }

    @Test
    public void getHelmChartName() {
        //List<String> charts = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(Paths.get("src/main/resources/charts.yaml"))) {
            String line;
            while((line = reader.readLine()) != null) {
                String[] parts = line.split("/");
                int partsLength = parts.length;
                String chartName = parts[partsLength-2];
                System.out.println(chartName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
