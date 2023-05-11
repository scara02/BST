import java.util.*;

public class BST<K extends Comparable<K>, V> implements Iterable<BST.KeyValue>{
    private Node root;
    private int size;

    private class Node {
        private K key;
        private V value;
        private Node left, right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public void put(K key, V value) {
        size++;
        Node node = new Node(key, value);
        if (root == null) {
            root = node;
            return;
        }

        put(root, node);
    }

    private Node put(Node parent, Node node) {
        if (parent == null)
            return node;

        if (parent.key.compareTo(node.key) > 0) {
            parent.left = put(parent.left, node);
        }

        else {
            parent.right = put(parent.right, node);
        }

        return parent;
    }

    public V get(K key) {
        Node node = get(root, key);
        return node.value;
    }

    private Node get(Node node, K key) {
        int cmp = node.key.compareTo(key);
        if (cmp == 0) {
            return node;
        }

        else if (cmp > 0) {
            return get(node.left, key);
        }

        else {
            return get(node.right, key);
        }
    }

    public void delete(K key) {
        root = delete(root, key);
    }

    private Node delete(Node node, K key) {
        if(node == null)
            return node;

        int cmp = node.key.compareTo(key);
        if(cmp > 0){
            node.left = delete(node.left, key);
        }

        else if(cmp < 0){
            node.right = delete(node.right, key);
        }
        else {
            if (node == null)
                return null;

            if (node.left == null) {
                size--;
                return node.right;
            }

            else if (node.right == null) {
                size--;
                return node.left;
            }

            Node min = getMin(node.right);
            node.key = min.key;
            node.value = min.value;
            node.right = delete(node.right, node.key);
        }

        return node;
    }

    private Node getMin(Node node) {
        Node min = node;

        while(node.left != null) {
            min = node.left;
            node = node.left;
        }

        return min;
    }

    public int size() {
        return size;
    }

    protected class KeyValue {
        private K key;
        private V value;

        public KeyValue(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }

    public Iterator<BST.KeyValue> iterator() {
        return new MyIterator();
    }

    private class MyIterator implements Iterator<BST.KeyValue> {
        private Stack<KeyValue> traversal;

        public MyIterator() {
            this.traversal = new Stack<>();
            inOrderTraversal(root);
        }

        private void inOrderTraversal(Node node){
            if (node == null)
                return;

            inOrderTraversal(node.right);
            this.traversal.push(new KeyValue(node.key, node.value));
            inOrderTraversal(node.left);
        }

        @Override
        public boolean hasNext() {
            return !traversal.isEmpty();
        }

        @Override
        public KeyValue next() {
            return traversal.pop();
        }
    }
}
