package sankar.learn;

import org.junit.jupiter.api.Test;
import sankar.learn.coding.problems.Q1614BestLine;

import java.util.ArrayList;
import java.util.List;

public class Snippets {

    @Test
    public void twoForLoopLogic() {
        List<Integer> list = new ArrayList<>();
        list.add(0);
        list.add(1);
        list.add(2);
        list.add(3);
        int length = list.size();
        for (int i = 0; i < length; i++) {
            for (int j = i+1; j < length; j++) {
                System.out.printf("(%s,%s)",list.get(i), list.get(j));
            }
        }
    }
}
