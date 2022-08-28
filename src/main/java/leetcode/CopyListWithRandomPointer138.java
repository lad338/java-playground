package leetcode;

import java.util.HashMap;
import java.util.Map;

public class CopyListWithRandomPointer138 {

    // Definition for a Node.
    class Node {

        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    class Solution {

        Map<Node, Integer> originalMap = new HashMap<>();
        Map<Integer, Node> nodeMap = new HashMap<>();

        public Node copyRandomList(Node head) {
            if (head == null) {
                return null;
            }

            Node newListNode = copyBaseList(head, 0);

            fillRandom(newListNode, head);
            return newListNode;
        }

        public Node copyBaseList(Node node, int index) {
            Node newNode = new Node(node.val);
            originalMap.put(node, index);
            nodeMap.put(index, newNode);

            if (node.next != null) {
                newNode.next = copyBaseList(node.next, index + 1);
            }
            return newNode;
        }

        public void fillRandom(Node node, Node original) {
            if (original.random != null) {
                Integer originalRandomIndex = originalMap.get(original.random);
                node.random = nodeMap.get(originalRandomIndex);
            }

            if (node.next != null) {
                fillRandom(node.next, original.next);
            }
        }
    }
}
