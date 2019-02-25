import java.util.*;

public class Problems {

    public static class Node {
        int val;
        Node next;

        Node(int d) {
            this.val = d;
            next = null;
        }
    }

    public static List<Integer> removeKDigits(int[] A, int k) {
        List<Integer> l = new LinkedList<>();
        for (int i = 0; i < A.length; i++) {
            // if element in A is less than last element in l, pop element off
            while(!l.isEmpty() && k > 0 && (A[i] < ((LinkedList<Integer>) l).getLast())) {
                ((LinkedList<Integer>) l).removeLast();
                k -= 1;
            }
            // add element in A to l
            if (l.size() < (A.length - k)) {
                l.add(A[i]);
            }
        }

        return l;
    }

    public static boolean isPalindrome(Node n) {
        if(n == null || n.next == null) {
            return true;    // if it's zero/one element long, return true
        }

        // Find length of linked list
        int length = 0;
        Node node = n;
        while(node.next != null) {
            length++;
            node = node.next;
        }
        // Find left of middle of list
        Node current = n;
        int midindex = (length / 2) - 1;
        while (midindex > 0 && current != null) {
            current = current.next;
            midindex -= 1;
        }

        // Remove the middle element if it length is odd
        if (length % 2 != 0) {
            current.next = current.next.next;
        }

        // Cut list in half and reverse last half

        Node nextHalf = current.next;
        Node prev = null;
        Node curr;
        while (nextHalf != null) {
            curr = nextHalf;
            nextHalf = nextHalf.next;
            curr.next = prev;
            prev = curr;
        }
        Node lastHalf = prev;
        current.next = null;

        // Compare front half with reversed back half of list
        while(n != null && lastHalf != null) {
            if (n.val != lastHalf.val) {
                return false;
            }
            n = n.next;
            lastHalf = lastHalf.next;
        }

        return true;
    }

    public static String infixToPostfix(String s) {
        String[] chars = s.split(" ");
        List<String> converted = new ArrayList<>();
        Stack<String> stack = new Stack<>();

        for (int i = 0; i < chars.length; i++) {
            if (chars[i].equals("(")) {
            }
            else if (chars[i].equals(")")) {
                converted.add(stack.pop());
            }
            else if (chars[i].equals("+") || chars[i].equals("-") || chars[i].equals("/") || chars[i].equals("*")) {
                stack.push(chars[i]);
            }
            else {
                converted.add(chars[i]);
            }
        }
        return String.join(" ", converted);
    }

}
