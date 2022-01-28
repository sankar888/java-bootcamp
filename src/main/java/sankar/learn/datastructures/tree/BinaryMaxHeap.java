package sankar.learn.datastructures.tree;

import com.google.common.base.Preconditions;
import sankar.learn.Utils;

import java.lang.reflect.Array;
import java.util.*;

/**
 * See: https://www.cpp.edu/~ftang/courses/CS241/notes/heap.htm
 * A heap is a specialized tree-based data structure that satisfied the heap property:
 * if B is a child node of A, then key(A) ≥ key(B). This implies that an element with the greatest key is always in the root node,
 * and so such a heap is sometimes called a max-heap.
 * There's also a min-heap, in which the root node is the minimum element
 *
 * Heap has many variants
 * 2-3 heap
 * Binary heap
 * Many many others
 *
 * Binary heap is a common data structure which is used to order elements by priority. A binary heap has only two children
 */
public class BinaryMaxHeap<E extends Comparable> {
    /**
     * All nodes in Binary heap should have a max of two children.
     * Heap is also a complete binary tree, which means the new elements are inserted in a way that the level is always complete before moving to next level
     * The new elements are inserted at the left most open position on the last level of the tree.
     *
     * The most common implementation of Heap is using an array.
     * [a , lc(a), rc(a), lc(lc(a)), rc(lc(a)), lc(rc(a)), rc(rc(A)), ...]
     * lc(a) -> left child of a
     * rc(a) -> right child of a
     * lc(lc(a)) -> left child of left child of a, this element is at 2nd level, if we consider root as 0th level
     *
     * Arranging the elements as above,
     * if i is the index if element a, then the index of left and right child of a is
     * index(lc(a)) = 2i + 1
     * index(rc(a)) = 2i + 2
     *
     * In java, array is a constant length data structure, what happens if the size of binary heap overcomes the size of the array
     * We create a new array with double the size of the current array and copy the elements to the new array. (same as logic used in arraylist)
     */
    private static final int INITIAL_ARRAY_SIZE = 100;
    private Object[] elements = new Object[INITIAL_ARRAY_SIZE];
    private int heapSize;

    /**
     * Common Operations involved in Heap
     * Basic
     *     find-max (or find-min): find a maximum item of a max-heap, or a minimum item of a min-heap, respectively (a.k.a. peek)
     *     insert: adding a new key to the heap (a.k.a., push[4])
     *     extract-max (or extract-min): returns the node of maximum value from a max heap [or minimum value from a min heap] after removing it from the heap (a.k.a., pop[5])
     *     delete-max (or delete-min): removing the root node of a max heap (or min heap), respectively
     *     replace: pop root and push a new key. More efficient than pop followed by push, since only need to balance once, not twice, and appropriate for fixed-size heaps.[6]
     *
     * Creation
     *     create-heap: create an empty heap
     *     heapify: create a heap out of given array of elements
     *     merge (union): joining two heaps to form a valid new heap containing all the elements of both, preserving the original heaps.
     *     meld: joining two heaps to form a valid new heap containing all the elements of both, destroying the original heaps.
     *
     * Inspection
     *     size: return the number of items in the heap.
     *     is-empty: return true if the heap is empty, false otherwise.
     *
     * Internal
     *     increase-key or decrease-key: updating a key within a max- or min-heap, respectively
     *     delete: delete an arbitrary node (followed by moving last node and sifting to maintain heap)
     *     sift-up: move a node up in the tree, as long as needed; used to restore heap condition after insertion. Called "sift" because node moves up the tree until it reaches the correct level, as in a sieve.
     *     sift-down: move a node down in the tree, similar to sift-up; used to restore heap condition after deletion or replacement.
     */
    public BinaryMaxHeap() {}

    public BinaryMaxHeap(E[] data) {
        Objects.requireNonNull(data, "Cannot create BinaryMinHeap from NULL");
        //can insert one by one and then shift-down to form max heap (or)
        //since the tree would be empty at this state we could sort the array in descending order and then insert sequentially to form a complete binary tree
        projectAndIncreaseCapacityIfNecessary(data.length);
        Arrays.sort(data, Collections.reverseOrder());
        System.arraycopy(data, 0, elements, 0, data.length);
        this.heapSize = data.length;
    }

    public BinaryMaxHeap(List<E> list) {
        Objects.requireNonNull(list, "Cannot create BinaryMinHeap from NULL");
        int noOfElements = list.size();
        projectAndIncreaseCapacityIfNecessary(noOfElements);
        list.sort(Collections.reverseOrder());
        System.arraycopy(list.toArray(), 0, elements, 0, noOfElements);
        this.heapSize = noOfElements;
    }

    public void insert(E element) {
        projectAndIncreaseCapacityIfNecessary(1);
        insertAtOpenPosition(element);
        
    }

    public int size() {
        return heapSize;
    }

    public boolean isEmpty(){
        return heapSize == 0;
    }

    @SuppressWarnings("unchecked")
    public E peekMax() {
        return (E)elements[0];
    }

    public E popMax() {
        return null;
    }

    public E popMaxAndPush(E element) {
        return null;
    }

    @Override
    public boolean equals(Object obj) {
        return false;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public String toString() {
        /**
         * represent each depth in different line
         * a
         * lc(a), rc(a)
         * lc(lc(a)), rc(lc(a)), lc(rc(a)), rc(rc(a))
         * ...
         */
        StringBuilder builder = new StringBuilder();
        for (int i=0; i < heapSize; i = (2*i)+1) {
            for (int j=i; j < (2*i)+1 && j < heapSize; j++) {
                builder.append(elements[j]).append("\t");
            }
            builder.append("\n");
        }
        return builder.toString();
    }

    private void shiftUp(int elementPosition) {

    }

    private void shiftDown() {}

    private void projectAndIncreaseCapacityIfNecessary(int elementsToInsert) {
        //if there is no need to increase size do nothing
        int projectedCapacity = heapSize +  elementsToInsert;
        if (projectedCapacity > elements.length) {
            //create new array with double the size of initial one and copy all the original elements
            int newCapacity = projectedCapacity * 2;
            Object[] newArr = new Object[newCapacity];
            System.arraycopy(elements, 0, newArr, 0, heapSize);
            this.elements = newArr;
        }
    }
    
    private void insertAtOpenPosition(E e) {
        elements[heapSize] = e;
        ++heapSize;
    }

    //TODO: Implement all methods, figure out the internal methods needed
}
