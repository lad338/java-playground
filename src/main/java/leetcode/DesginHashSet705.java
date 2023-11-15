package leetcode;

public class DesginHashSet705 {

    class MyChessedHashSet {

        boolean[] set;

        public MyChessedHashSet() {
            set = new boolean[(int) 10e6 + 1];
        }

        public void add(int key) {
            set[key] = true;
        }

        public void remove(int key) {
            set[key] = false;
        }

        public boolean contains(int key) {
            return set[key];
        }
    }

    /**
     * Your MyHashSet object will be instantiated and called as such:
     * MyHashSet obj = new MyHashSet();
     * obj.add(key);
     * obj.remove(key);
     * boolean param_3 = obj.contains(key);
     */

    class MyHashSet {

        private final int HASH_VALUE = 10_007;

        Node[] set = new Node[HASH_VALUE];

        class Node {

            public int val;
            public Node next;
            public Node previous;

            public Node(int val, Node previous) {
                this.val = val;
                this.previous = previous;
            }
        }

        public MyHashSet() {
            for (int i = 0; i < HASH_VALUE; i++) {
                set[i] = new Node(-1, null);
            }
        }

        public void add(int key) {
            Node head = set[key % HASH_VALUE];

            while (head.next != null) {
                head = head.next;
                if (head.val == key) {
                    return;
                }
            }

            Node newNode = new Node(key, head);
            head.next = newNode;
        }

        public void remove(int key) {
            Node head = set[key % HASH_VALUE];
            while (head.next != null) {
                head = head.next;
                if (head.val == key) {
                    head.previous.next = head.next;
                    if (head.next != null) {
                        head.next.previous = head.previous;
                    }
                    return;
                }
            }
        }

        public boolean contains(int key) {
            Node head = set[key % HASH_VALUE];
            while (head.next != null) {
                head = head.next;
                if (head.val == key) {
                    return true;
                }
            }
            return false;
        }
    }

    /**
     * Your MyHashSet object will be instantiated and called as such:
     * MyHashSet obj = new MyHashSet();
     * obj.add(key);
     * obj.remove(key);
     * boolean param_3 = obj.contains(key);
     */

    class NeetCodeMyHashSet {

        private final int HASH_VALUE = 10_007;

        Node[] set = new Node[HASH_VALUE];

        class Node {

            public int val;
            public Node next;

            public Node(int val) {
                this.val = val;
            }
        }

        public NeetCodeMyHashSet() {
            for (int i = 0; i < HASH_VALUE; i++) {
                set[i] = new Node(-1);
            }
        }

        public void add(int key) {
            Node head = set[key % HASH_VALUE];
            while (head.next != null) {
                if (head.next.val == key) {
                    return;
                }
                head = head.next;
            }

            head.next = new Node(key);
        }

        public void remove(int key) {
            Node head = set[key % HASH_VALUE];
            while (head.next != null) {
                if (head.next.val == key) {
                    head.next = head.next.next;
                    return;
                }
                head = head.next;
            }
        }

        public boolean contains(int key) {
            Node head = set[key % HASH_VALUE];
            while (head.next != null) {
                head = head.next;
                if (head.val == key) {
                    return true;
                }
            }
            return false;
        }
    }
}
