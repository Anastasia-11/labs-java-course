package lab4;

public class Main {
    public static void main(String[] args) {
        BinaryTree<Integer> tree = new BinaryTree<>();
        tree.traverseInOrder();
        tree.insert(4);
        tree.insert(10);
        tree.insert(-5);
        tree.insert(-3);
        tree.insert(6);
        tree.insert(-1);
        tree.insert(17);
        tree.insert(14);
        tree.insert(-9);

        System.out.println("\nIn order\n");
        tree.traverseInOrder();

        System.out.println("\nPost order\n");
        tree.traversePostOrder();

        System.out.println("\nPre order\n");
        tree.traversePreOrder();

        tree.delete(4);
        tree.delete(-5);
        tree.delete(17);
        tree.delete(-9);
        tree.delete(-1);

        System.out.println(tree.search(4));
        System.out.println(tree.search(17));
        System.out.println(tree.search(-5));
        System.out.println(tree.search(-9));
        System.out.println(tree.search(14));
        System.out.println(tree.search(1));
        System.out.println(tree.search(-1));
        System.out.println(tree.search(6));
        System.out.println(tree.search(-3));
        System.out.println(tree.search(10));

        BinaryTree<Student> tree2 = new BinaryTree<>();
        tree2.insert(new Student(1, "a", 4, 5));
        tree2.insert(new Student(-1, "b", 3, 6));
        tree2.insert(new Student(3, "c", 2, 7));
        tree2.insert(new Student(4, "d", 1, 8));
    }
}
