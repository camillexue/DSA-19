import java.util.*;

public class Problems {

    public static BinarySearchTree<Integer> minimalHeight(List<Integer> values) {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        Collections.sort(values);
        // first call with whole list of values
        balanceAdd(bst, values, 0, values.size());
        return bst;
    }

    private static void balanceAdd(BinarySearchTree<Integer> bst, List<Integer> values, int start, int end) {
        if (end <= start) return;
        int med = (start + end) / 2; // median value
        bst.add(values.get(med)); // add to tree
        balanceAdd(bst, values, start, med); // repeat with left side
        balanceAdd(bst, values, med+1, end); // repeat with right side
    }


    public static boolean isIsomorphic(TreeNode n1, TreeNode n2) {
        // TODO
        return false;
    }
}
