import java.util.Iterator;
import java.util.Vector;

public class BinarySearchTree<E extends Comparable<? super E>> extends BinaryTree<E> {
    public void insert(E data) {
        root = RecursiveInsert(root, data);
    }

    private Node<E> RecursiveInsert(Node<E> node, E data) {
        if(node == null) {
            return new Node<>(data);
        }

        int compare = data.compareTo(node.data);
        if(compare <= 0) {
            node.left = RecursiveInsert(node.left, data);
        }
        else {
            node.right = RecursiveInsert(node.right, data);
        }
        return node;
    }

    public void remove(E data) {
        root = RecursiveRemove(root, data);
    }

    private Node<E> RecursiveRemove(Node<E> node, E data) {
        if(node == null) {
            return null;
        }

        int compare = data.compareTo(node.data);
        if(compare < 0) {
            node.left = RecursiveRemove(node.left, data);
        }
        else if(compare > 0) {
            node.right = RecursiveRemove(node.right, data);
        }
        else {
            if(node.left == null) {
                return node.right;
            }
            else if(node.right == null) {
                return node.left;
            }
            node.data = findSmallest(node.right).data;
            node.right = RecursiveRemove(node.right, node.data);
        }
        return node;
    }

    private Node<E> findSmallest(Node<E> node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    public boolean search(E data) {
        return RecursiveSearch(root, data);
    }

    private boolean RecursiveSearch(Node<E> node, E data) {
        if(node == null) {
            return false;
        }

        int compare = data.compareTo(node.data);
        if(compare == 0) {
            return true;
        }
        else if(compare < 0) {
            return RecursiveSearch(node.left, data);
        }
        else {
            return RecursiveSearch(node.right, data);
        }
    }

    public Iterator<E> iterator() {
        Vector<E> items = new Vector<>();
        inorderTraversal(root,items);
        return items.iterator();
    }

    private void inorderTraversal(Node<E> node, Vector<E> items) {
        if(node != null) {
            inorderTraversal(node.left, items);
            items.add(node.data);
            inorderTraversal(node.right, items);
        }
    }
}