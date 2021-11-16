package sankar.learn.coding.problems;

import com.google.common.base.Preconditions;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Implement an algorithm to print all valid (eg: properly opened and closed) combinations of n pairs of parentheses
 *
 * Example:-
 * input: 3
 * Output: ((())), (()()), (())(), ()(()), ()()()
 */
public class Q89Parens {
    /**
     * Solution: we could use crossing logic to print all valid combinations of the parantheses but it would get us all the possible combinations, we need only
     * combinations with all the number of input. The output elements should have all the brackets in it.
     *
     * Applying that logic : (, (, (, ), ), ) and consider the numbers for each braces: 0 1 2 3 4 5 6
     * the 6 length elements are : 0123456, 1023456, 2013456 rtc..
     * It result in : ((())), ((())), ((())))
     * This is not the right approach
     *
     * On second thought, the combinations of x pair can be arrived by crossing the combinations of (x-1) pair and (,)
     * Lets consider set of elements for x pair , pair = [e1, e2, en]
     * and to arrive at elements for x+1 cross the pair set with ( and )
     *
     * if p(x) = [e0, e1] is elements for input x, then elements for x+1 would be
     * a p(x) b , p(X) a b, a b p(x)
     *
     * Example:- input x = 2
     * for x=1 => p(x-1) = [], following the a p(x) b | p(X) a b | a b p(x) pattern and then de duplicating, we get
     * p(1)= () | () | (), after de-duplicating we get, p(1) = ()
     *
     * for x=2 => p(x-1) = () , following the a p(x) b | p(X) a b | a b p(x) pattern and then de duplicating, we get
     * p(2) = (()) | ()() | ()() after de-duplicating we get, p(2) = (()), ()()
     *
     * for x=3 => p(x-1) = (()), ()(),  following the a p(x) b | p(X) a b | a b p(x) pattern and then de duplicating, we get
     * p(3) = ((())), (()()) | (())(), ()()() | ()(()), ()()() after de-duplicating , p(3) = ((())), (()()), (())(), ()()(), ()(())
     */

    private static void solve(int numOfPairs) {
        System.out.println(printNParensRecursive(numOfPairs));
    }

    private static Set<String> printNParensRecursive(int n) {
        Preconditions.checkArgument(n > 0, "Input should be greater than zero");
        char a = '(';
        char b = ')';
        if (n == 1) {
            Set<String> pre = new HashSet<>();
            pre.add(String.format("%s%s",a,b));
            return pre;
        }
        Set<String> pre = printNParensRecursive(--n);
        //pattern a pre b | pre a b | a b pre
        Set<String> pairs = new HashSet<>();
        for (String p : pre) {
            pairs.add(String.format("%s%s%s", a,p,b));
            pairs.add(String.format("%s%s%s", p,a,b));
            pairs.add(String.format("%s%s%s", a,b,p));
        }
        pairs.addAll(pairs);
        return Collections.unmodifiableSet(pairs);
    }

    public static void main(String[] args) {
        solve(2);
        solve(3);
        solve(4);
        //TODO: can we achieve the same without using recursion,
        //TODO: could we avoid using set which might check every time of insertion. could we overcome this duplicate checking,
        //TODO: big(0) calculation for different types of solutions
    }
}

