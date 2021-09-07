package lab4;

public class BinaryTree<T extends Comparable<T>> {
    private class Node {
        public T key;
        public Node left;
        public Node right;

        public Node(T key) {
            this.key = key;
            this.right = null;
            this.left = null;
        }
    }
    private Node root;
    
    public void insert(T x) {
        Node node = root;
        Node parent = null;
        while(node != null) {
            parent = node;
            if(x == node.key){
                return;
            }
            else {
                node = x.compareTo(node.key) < 0 ? node.left : node.right;
            }
        }
        if (parent == null)
            root = new Node(x);
        else {
            if (x.compareTo(parent.key) < 0)
                parent.left = new Node(x);
            else
                parent.right = new Node(x);
        }
    }

    public void delete(T value) {
        root = delete(root, value);
    }

    private Node delete(Node current, T key) {
        if (current == null)
            return null;
        if (key == current.key) {
            if(current.left == null && current.right == null) {
                return null;
            }
            if(current.left != null && current.right != null) {
                T small = findMin(current.right);
                current.key = small;
                current.right = delete(current.right, small);
                return current;
            }
            return current.left == null ? current.right : current.left;
        }
        if (key.compareTo(current.key) > 0) {
            current.right = delete(current.right, key);
            return current;
        }
        current.left = delete(current.left, key);
        return current;
    }

    private T findMin(Node root) {
        return root.left == null ? root.key : findMin(root.left);
    }

    public boolean search(T value) {
        Node node = root;
        while(node != null) {
            if(value == node.key){
                return true;
            }
            else {
                node = value.compareTo(node.key) < 0 ? node.left : node.right;
            }
        }
        return false;
    }

    public void traverseInOrder() {
        traverseInOrder(root);
    }

    public void traversePreOrder() {
        traversePreOrder(root);
    }

    public void traversePostOrder() {
        traversePostOrder(root);
    }

    private void traverseInOrder(Node node) {
        if (node != null) {
            traverseInOrder(node.left);
            System.out.print(" " + node.key);
            traverseInOrder(node.right);
        }
    }

    private void traversePreOrder(Node node) {
        if (node != null) {
            System.out.print(" " + node.key);
            traversePreOrder(node.left);
            traversePreOrder(node.right);
        }
    }

    private void traversePostOrder(Node node) {
        if (node != null) {
            traversePostOrder(node.left);
            traversePostOrder(node.right);
            System.out.print(" " + node.key);
        }
    }
}
