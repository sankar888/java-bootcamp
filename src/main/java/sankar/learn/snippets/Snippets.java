package sankar.learn.snippets;

import org.junit.jupiter.api.Test;

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
}
