package leetcode;

import java.util.*;

public class CloneGraph133 {

    class Solution {

        //value: node
        Map<Integer, Node> map = new HashMap<>();

        public Node cloneGraph(Node node) {
            if (node == null) {
                return null;
            }

            if (map.containsKey(node.val)) {
                return map.get(node.val);
            }

            Node newNode = new Node(node.val, new ArrayList<>());
            map.put(node.val, newNode);

            for (Node nei : node.neighbors) {
                Node newNei = cloneGraph(nei);
                newNode.neighbors.add(newNei);
            }

            return newNode;
        }
    }

    class NonUniqueValueSolution {

        //value: node
        Map<Node, Node> map = new HashMap<>();

        public Node cloneGraph(Node node) {
            if (node == null) {
                return null;
            }

            if (map.containsKey(node)) {
                return map.get(node);
            }

            Node newNode = new Node(node.val, new ArrayList<>());
            map.put(node, newNode);

            for (Node nei : node.neighbors) {
                Node newNei = cloneGraph(nei);
                newNode.neighbors.add(newNei);
            }

            return newNode;
        }
    }
}
