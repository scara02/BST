public class Main {
    public static void main(String[] args) {
        BST<Integer, String> bst = new BST<>();
        bst.put(23, "ice-cream");
        bst.put(13, "cookie");
        bst.put(4, "white wine");
        bst.put(11, "red wine");
        bst.put(45, "chocolate");
        bst.put(31, "cherry");
        bst.put(3, "strawberry");
        bst.put(17, "cheese");

        System.out.println("Size of a tree: " + bst.size());
        System.out.println("Tree: ");
        print(bst);

        System.out.println("\nValue of element with key that is 17: " + bst.get(17));
        System.out.println("Delete element with key that is 17..." + '\n');
        bst.delete(17);

        System.out.println("Size of a tree: " + bst.size());
        System.out.println("Tree: ");
        print(bst);

    }

    public static void print(BST<Integer, String> bst) {
        for(var el : bst) {
            System.out.println(el.getKey() + " " + el.getValue());
        }
    }
}