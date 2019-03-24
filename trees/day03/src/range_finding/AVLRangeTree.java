package range_finding;

import java.util.LinkedList;
import java.util.ArrayList;
import java.util.List;

public class AVLRangeTree extends BinarySearchTree<Integer> {

    /**
     * Delete a key from the tree rooted at the given node.
     */
    @Override
    RangeNode<Integer> delete(RangeNode<Integer> n, Integer key) {
        n = super.delete(n, key);
        if (n != null) {
            n.height = 1 + Math.max(height(n.leftChild), height(n.rightChild));
            return balance(n);
        }
        return null;
    }

    /**
     * Insert a key into the tree rooted at the given node.
     */
    @Override
    RangeNode<Integer> insert(RangeNode<Integer> n, Integer key) {
        n = super.insert(n, key);
        if (n != null) {
            n.height = 1 + Math.max(height(n.leftChild), height(n.rightChild));
            return balance(n);
        }
        return null;
    }

    /**
     * Delete the minimum descendant of the given node.
     */
    @Override
    RangeNode<Integer> deleteMin(RangeNode<Integer> n) {
        n = super.deleteMin(n);
        if (n != null) {
            n.height = 1 + Math.max(height(n.leftChild), height(n.rightChild));
            return balance(n);
        }
        return null;
    }

    // Return the height of the given node. Return -1 if null.
    private int height(RangeNode x) {
        if (x == null) return -1;
        return x.height;
    }

    public int height() {
        return Math.max(height(root), 0);
    }

    // Restores the AVL tree property of the subtree.
    RangeNode<Integer> balance(RangeNode<Integer> x) {
        if (balanceFactor(x) > 1) {
            if (balanceFactor(x.rightChild) < 0) {
                x.rightChild = rotateRight(x.rightChild);
            }
            x = rotateLeft(x);
        } else if (balanceFactor(x) < -1) {
            if (balanceFactor(x.leftChild) > 0) {
                x.leftChild = rotateLeft(x.leftChild);
            }
            x = rotateRight(x);
        }
        return x;
    }

    // Return all keys that are between [lo, hi] (inclusive).
    // TODO: runtime = O(logN + k)
    public List<Integer> rangeIndex(int lo, int hi) {
        // TODO
        List<Integer> l = new ArrayList<>();
//        System.out.println(root.key);
        RangeNode<Integer> lo_node = find(root, lo);

        if (lo == hi) {
            if(lo_node != null) {
                l.add(lo);
                return l;
            }
            return l;
        }

        rangeTraverse(root, lo, hi, l);

        return l;
    }

    public void rangeTraverse(RangeNode<Integer> node, int lo, int hi, List<Integer> list) {
        if (node != null) {
            if(node.key >= lo) {
                rangeTraverse(node.leftChild, lo, hi, list);
            }
            if (node.key <= hi && node.key >= lo) {
                list.add(node.key);
//                System.out.println(node.key);
            }
            if(node.key <= hi) {
                rangeTraverse(node.rightChild, lo, hi, list);
            }
        }
    }

    // return the number of keys between [lo, hi], inclusive
    // TODO: runtime = O(logN + k)
    public int rangeCount(int lo, int hi) {

        return count(root, lo, hi);
    }

    public int count(RangeNode<Integer> n, int lo, int hi) {
        if (n == null) { return 0; }
        if (n.key <= hi && n.key >= lo) {
            return 1 + count(n.leftChild, lo, hi) + count(n.rightChild, lo, hi);
        }
        if (n.key < lo) {
            return count(n.rightChild, lo, hi);
        } else {
            return count(n.leftChild, lo, hi);
        }
    }

/**
 * Returns the balance factor of the subtree. The balance factor is defined
 * as the difference in height of the left subtree and right subtree, in
 * this order. Therefore, a subtree with a balance factor of -1, 0 or 1 has
 * the AVL property since the heights of the two child subtrees differ by at
 * most one.
 */
    private int balanceFactor(RangeNode x) {
        return height(x.rightChild) - height(x.leftChild);
    }

    /**
     * Perform a right rotation on node `n`. Return the head of the rotated tree.
     */
    private RangeNode<Integer> rotateRight(RangeNode<Integer> x) {
        RangeNode<Integer> y = x.leftChild;
        x.leftChild = y.rightChild;
        y.rightChild = x;
        x.height = 1 + Math.max(height(x.leftChild), height(x.rightChild));
        y.height = 1 + Math.max(height(y.leftChild), height(y.rightChild));
        return y;
    }

    /**
     * Perform a left rotation on node `n`. Return the head of the rotated tree.
     */
    private RangeNode<Integer> rotateLeft(RangeNode<Integer> x) {
        RangeNode<Integer> y = x.rightChild;
        x.rightChild = y.leftChild;
        y.leftChild = x;
        x.height = 1 + Math.max(height(x.leftChild), height(x.rightChild));
        y.height = 1 + Math.max(height(y.leftChild), height(y.rightChild));
        return y;
    }
}
