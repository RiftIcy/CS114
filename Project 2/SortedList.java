/*
 *
 *  SortedList.java
 *
 */
import java.util.Iterator;
public class SortedList<E extends Comparable<? super E>> extends List<E> {
    
    public void insert(E data) {

        Node<E> temp = new Node<E>(data);
        head = insert(head, temp);
        
    }

    private Node<E> insert(Node<E> curr, Node<E> temp) { //head is the current node that is being traversed in the list and curr is the new node we are inserting
        if(curr == null) { //If the list is empty or we are at the end of the list
            //If the list is empty then insert first node otherwise insert node at the end of the list
            temp.next = null;
            curr = temp;//can return temp instead
        }
        else if(temp.data.compareTo(curr.data) < 0) { //If data that we are sorting is less than the current node then insert it
            //Insert new node before current node
            temp.next = curr;
            curr = temp;//can return temp instead
        }
        else {//If the list is not empty, or if the end of the list has not been reached, or if the insert point has not been reached
            //Traverse the list by going to the next node
            curr.next = insert(curr.next, temp);
        }
        //returning the reference back to head
        return curr;
    }

    public Iterator<E> iterator() {
        return new Iterator<E>() {
            public boolean hasNext() {
                return curr != null;
            }
            public E next() {
                E temp = curr.data;
                curr = curr.next;
                return temp;
            }
        private Node<E> curr = head;
        };
    }

    public void remove(E data) {
        head = remove(head, data);
    }

    private Node<E> remove(Node<E> curr, E data) {
        //If the head is the one you want to remove unrefrence it and make the next node head
        if(curr.data.compareTo(data) == 0) {
            curr = curr.next;
            return curr; //Return the new refrence
        }
       //If curr is not empty and our curr next data is equal to each other than point to curr.next and remove curr form the linked list
        if(curr.next != null) {
            curr.next = remove(curr.next, data);
        }
        return curr;
    }

    public E retrieve(int index) { //Giving the value of the data at the index you give it
        return retrieve(index, head); //uses head refrence from retrieve function
    }

    private E retrieve(int index, Node<E> curr) {
        if(curr != null && index == 0) { //if the first index is a value and its the first it returns the data
            return curr.data;
        }
        if(curr.next != null) { //if the next index is a value then check the next one until you find the index
            return retrieve(index - 1, curr.next);
        }
        return null;
    }
    
    public boolean search(E data) { //returns true if the data is in the list
        return search(data, head);
    }

    private boolean search(E data, Node<E> curr) {
        if(curr != null && curr.data.compareTo(data) == 0) { //if the current node exists and the current data is equal to currs data then return true
            return true;
        }
        if(curr.next != null) { //if curr.next has a value then continue to search for it
            return search(data, curr.next);
        }
        return false;//if its not in the list return false
    }

}