package sankar.learn.datastructures.tree;

import com.google.common.base.Preconditions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class SimpleBinarySearchTree {

    class Node<V extends Comparable> {
        private V value;
        private Node<V> left;
        private Node<V> right;

        public Node(V data) {
            Objects.requireNonNull(data, "data cannot be NULL");
            this.value = data;
        }

        public V value() {
            return value;
        }

        public Node<V> left() {
            return left;
        }

        public Node<V> right() {
            return right;
        }

        public void addChild(V value) {
            Objects.requireNonNull(value, "child cannot be NULL");
            int res = value.compareTo(value());
            if (res <= 0) { //TODO: how to parse this logic more elegant and without code repetition
                if (left == null) {
                    left = new Node<>(value);
                } else {
                    left.addChild(value);
                }
            } else {
                if (right == null) {
                    right = new Node<>(value);
                } else {
                    right.addChild(value);
                }
            }
        }
    }

    class BinarySearchTree<V extends Comparable> {
        private Node<V> root;
        public BinarySearchTree(Collection<V> collection) {
            Preconditions.checkArgument(collection != null, "Cannot create tree with NULL data");
            /**
             * TODO: if we add the data sequentially the tree might not be balanced,
             * TODO: depending on the order in which the elements are added the tree might become
             * TODO: balanced or unbalanced
             */
            initTree(collection);
        }

        private void initTree(Collection<V> collection) {
            if (!collection.isEmpty()) {
                Iterator<V> itr = collection.iterator();
                V first = itr.next();
                if (root == null) {
                    root = new Node<>(first);
                } else {
                    root.addChild(first);
                }
                while (itr.hasNext()) {
                    root.addChild(itr.next());
                }
            }
        }

        //TODO: implement the various traversal method and understand it
        //TODO: DFS :- inorder, pre-order, postorder,
        //TODO: BFS :- level order
        //TODO: Learn more about other tree traversal algorithms , ex:- Monte Carlo tree search

        //inorder is left - self - right
        public List<V> inOrderTraversal() {
            List<V> result = new ArrayList<>();
            internalInOrder(root, result);
            return result;
        }
        //preorder is self - left - right
        public List<V> preOrderTraversal() {
            List<V> result = new ArrayList<>();
            internalPreOrder(root, result);
            return result;

        }
        //postorder is left - right - self
        public List<V> postOrderTraversal() {
            List<V> result = new ArrayList<>();
            internalPostOrder(root, result);
            return result;
        }

        private void internalInOrder(Node<V> root, List<V> addTo) {
            if (root == null) {
                return;
            }
            internalInOrder(root.left(), addTo);
            addTo.add(root.value());
            internalInOrder(root.right(), addTo);
        }

        private void internalPreOrder(Node<V> root, List<V> addTo) {
            if (root == null) {
                return;
            }
            addTo.add(root.value());
            internalInOrder(root.left(), addTo);
            internalInOrder(root.right(), addTo);
        }

        private void internalPostOrder(Node<V> root, List<V> addTo) {
            if (root == null) {
                return;
            }
            internalInOrder(root.left(), addTo);
            internalInOrder(root.right(), addTo);
            addTo.add(root.value());
        }

        public List<V> levelOrderTraversal() {
            List<V> result = new ArrayList<>();
            Queue<Node<V>> queue = new LinkedList<>();
            queue.add(root);

            while (!queue.isEmpty()) {
                Node<V> root = queue.poll();
                result.add(root.value());

                Node<V> left = root.left();
                if(left != null) {
                    queue.add(left);
                }

                Node<V> right = root.right();
                if (right != null) {
                    queue.add(right);
                }
            }
            return result;
        }
    }

    @Test
    public void createTree() {
        List<Integer> data =  Arrays.asList(new Integer[]{34, 34, 3, 5, 7, 2, 98, 4,});
        BinarySearchTree<Integer> tree = new BinarySearchTree<>(data);
        System.out.println(tree.inOrderTraversal());
        System.out.println(tree.preOrderTraversal());
        System.out.println(tree.postOrderTraversal());
        System.out.println(tree.levelOrderTraversal());
    }
}
