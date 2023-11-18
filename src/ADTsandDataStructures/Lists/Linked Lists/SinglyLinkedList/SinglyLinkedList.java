
/**
 * The LinkedList class is an implementation of the List ADT as a Singly Linked List.
 * @implSpec This Linked List compares the node's internal data reference (not the Node's reference itself) denoted dataFind, in order to add and remove from certain appropriate positions.
 * @implNote keeps track of the tail Node, but is still O(n) because this is applied through a Singly-Node
 * @author Faycal Kilali, Dylan Kim
 * @version 1.2
 */
public class SinglyLinkedList<T> implements ListADT<T>, LinkedListADT<T>
{
    private int size = 0;
    private Node<T> head = null; // Recall that this variable actually points to the node. Possibly maybe the head node should always have an objectReference of null
    private Node<T> tail = null; // For optimization purposes (we don't wanna traverse the Linked List every single time we wish to remove or add to tail)

    /**
     * Note here the inNode only shows the visibility to the inner-class, we have to actually call the constructor and so on.
     * This adds a new node to the front of the list (this node becomes the new head node).
     * If the list is empty (head is null): You create a new node and set it as the head. Since the list is empty, this new node is both the first and last node in the list.
     * If the list is not empty: You create a new node with the current head as its reference. This new node becomes the new head, and its reference is set to the previous head, preserving the rest of the list.
     * @param inData the data pointer of the added node
     * @throws NullPointerException if inData is null. The Exception should be caught and the method re-called with a non-null object.
     */
    @Override
    public void add(T inData) throws NullPointerException
    {
        if (inData == null){
            throw new NullPointerException("Parameterized object is null");
        }
        if(isEmpty()){
            Node<T> newNode = new Node<T>(inData); // New head
            head = newNode;
        }
        else{
            Node<T> newNode = new Node<T>(inData, head);
            head = newNode;
        }

        // O(1) optimization for removals and additions to back
        if (getSize() == 1) {
            // If the list was empty before the addition, update the tail to the new node
            tail = head;
        }

    }

    /**
     * Same as add method, required due to abstract implementation of interface
     * @param inData the new node's data reference that we will add to the front of the Linked List
     * @throws NullPointerException if inData is null. The Exception should be caught and the method re-called with a non-null object.
     */
    @Override
    public void addToFront(T inData) throws NullPointerException
    {
        add(inData);
    }

    /**
     * Adds a node to the tail of the linked list
     * @param inData the added node's data reference
     * @throws NullPointerException if inData is null. The Exception should be caught and the method re-called with a non-null object.
     */
    @Override
    public void addToBack(T inData)throws NullPointerException{
        if (inData == null){
            throw new NullPointerException("Parameterized object is null");
        }


        // O(1) optimization
        Node<T> insertNode = new Node<>(inData);

        if (isEmpty()) {
            // If the list is empty, the new node becomes both the head and the tail
            head = insertNode;
            tail = insertNode;
        }

        else {
            // Otherwise, add the new node to the tail and update the tail reference
            tail.setNext(insertNode);
            tail = insertNode;
        }

        // O(n) unoptimized
        //Node<T> currNode = traverseLinkedList(null);
        //Node<T> insertNode = new Node(inData);
        //currNode.setNext(insertNode);
        //tail = insertNode;
    }


    /**
     * Adds a node after a particular node (usually between two nodes, but not necessarily between two nodes because it could be the last node)
     * @param inData the data of the newly added Node, dataFind the data inside the node that we'll add after.
     * @throws NullPointerException if dataFind is null. The Exception should be caught and the method re-called with a non-null object.
     */
    @Override
    public void addAfter(T inData, T dataFind) throws NullPointerException{
        addAfterHelper(inData, dataFind);
    }

    /**
     * Helper method to achieve the requirements of addAfter
     * @param inData the data of the newly added Node, dataFind the reference of the Node we'll add after
     * @throws NullPointerException if dataFind is null. The Exception should be caught and the method re-called with a non-null object.
     */
    private void addAfterHelper(T inData, T dataFind) throws NullPointerException {
        if (inData == null){
            throw new NullPointerException("Parameterized object is null");
        }

        // Case where the Linked List is empty
        if(head == null){
            add(inData);
            return;
        }

        Node<T> insertNode = new Node<T>(inData);
        Node<T> currNode = traverseLinkedList(dataFind);

        if (currNode.getNext() != null){
            insertNode.setNext(currNode.getNext());
        }
        else{
            tail = insertNode; // the new node we added becomes the new tail
        }

        currNode.setNext(insertNode); // Updates the previous node's pointer to the newly added node

    }


    /**
     * Adds a Node at a particular position from 0 to n, inclusive.
     * @param inData is the data inside the node we are adding, inPosition
     * @param inPosition the position we are adding the new Node to
     * @throws NullPointerException if inData is null. The Exception should be caught and the method re-called with a non-null object. IllegalArgumentException if the position is illegal.
     */
    public void addToPosition(T inData, int inPosition) throws NullPointerException, IllegalArgumentException
    {

        if (inPosition == 0){
            addToFront(inData);
            return;
        }

        if (inPosition == getSize()){
            addToBack(inData);
            return;
        }


        if (inData == null){
            throw new NullPointerException("Parameterized object is null");
        }

        if ( (inPosition > getSize()) || (inPosition < 0) ){
            throw new IllegalArgumentException("Illegal position in Data Structure");
        }

        Node<T> positionNode = traverseLinkedListToPosition(inPosition);
        Node<T> currNode;
        if (positionNode != head){
            currNode = traverseLinkedListPriorToReference(positionNode.getData());
        }
        else{
            currNode = positionNode;
        }

        Node<T> insertNode = new Node(inData);
        if (  (currNode.getNext() != null) ){
            insertNode.setNext(currNode.getNext()); // Sets the correct reference for the move we are adding so the chain isn't broken
        }

        currNode.setNext(insertNode); // Updates the previous move's pointer to the newly added move
    }


    /**
     * Helper method to traverse a Linked List up to the node at dataFind (inclusive)
     * @param dataFind
     * @return Node<T>, the first Node that holds object reference dataFind.
     */
    protected Node<T> traverseLinkedList(T dataFind) throws NullPointerException {
        if (isEmpty() == true) {
            throw new NullPointerException();
        }

        Node<T> currNode = head;

        // Check if head is null
        if (currNode == null) {
            System.out.println("Head is null");
            return null;
        }

        //System.out.println("Initial currNode: " + currNode);



        while (currNode.getNext() != null && currNode.getData() != dataFind) { // Adjustment: separate cases. We want to move forward by 1 if its dataFind, and not move if its null.
            //System.out.println(currNode);
            currNode = currNode.getNext();

        }
        return currNode;
    }


    /**
     * Helper method to traverse a Linked List before the node of data dataFind (inclusive)
     * @param dataFind
     * @return Node<T>, the Node before the first Node that holds object reference dataFind.
     */
    protected Node<T> traverseLinkedListPriorToReference(T dataFind) throws NullPointerException {
        if (isEmpty() == true) {
            throw new NullPointerException();
        }

        Node<T> currNode = head;

        // Check if head is null
        if (currNode == null) {
            System.out.println("Head is null");
            return null;
        }

        //System.out.println("Initial currNode: " + currNode);



            while ((currNode.getNext() != null && (currNode.getNext().getData() != dataFind))){ // Adjustment: separate cases. We want to move forward by 1 if its dataFind, and not move if its null.
                //System.out.println(currNode);
                currNode = currNode.getNext();
            }
        return currNode;
    }

    /**
     * Helper method to traverse a Linked List up to the position of inPosition inclusive (assuming the head node is at position 0, and incrementing towards more nodes).
     * Position starts from 0 to n, for some n Integer.
     * @implNote This requires that a LinkedList has at least one element in it already.
     * @Param inPosition the position starting from the head node
     * @return Node, returns the node at the inPosition'th position.
     */
    protected Node<T> traverseLinkedListToPosition(int inPosition) throws IllegalArgumentException{

        // No position when LL is empty
        if (isEmpty()){
            throw new IllegalStateException("Linked list is empty.");
        }

        if ( (inPosition > getSize()) || (inPosition < 0) ){
            throw new IllegalArgumentException("Illegal position in Data Structure. Position must be positive and must be smaller than size of Data Structure. Maximal position is " + getSize() + ", received" + inPosition);
        }

        int curPosition = 0;
        Node<T> currNode = head;
        while (currNode != null){
            if (inPosition == curPosition){
                return currNode;
            }
            currNode = currNode.getNext();
            curPosition++;
        }
        return currNode;
    }

    /**
     * Removes the whole first node from the Linked List
     * This accesses the referenced node's reference, hence traversing exactly two nodes forward from the head node in order to connect the node before the dataFind node with the node that comes after dataFind.
     * This should not return a null value. Rather, the exception EmptyListException should be thrown. The user can recover from that by ensuring they are removing from a non-empty list
     * @return the removed node, or null if no node to be removed
     */

    @Override
    public T remove(){
        //Node traversedListByOne = head.getNext().getNext();
        if (isEmpty() == false){
            Node<T> currNode = head;
            head = head.getNext();

            if (head == null){
                tail = null;
            }

            // Returning the removed node's data
            return currNode.getData();
        }

        return null; // better to throw an exception instead
    }

    /**
     * Same as remove method, again duplication due to interface method requirement
     * @returns the data inside the removed Node
     */
    @Override
    public T removeFromFront(){
        return remove();
    }

    /**
     * Removes the tail node from the Linked List
     * @return returns the data inside the removed Node
     */
    @Override
    public T removeFromBack(){
        Node<T> currNode = traverseLinkedListPriorToReference(null);
        Node<T> prevNode = traverseLinkedListPriorToReference(currNode.getData());
        prevNode.setNext(null);

        if (getSize() <= 1) {
            // If the list has one or zero elements after removal, update the tail to null
            tail = null;
        } else {
            // If removing the tail, update the tail to the new last node
            tail = prevNode;
        }

        return currNode.getData();
    }



    /**
     * Removes the first instance of an object reference. Removes currentNode by substituting previousNode with the reference of the node after currentNode, where currentNode has the value we wish to remove.
     * @throws IllegalArgumentException if instance is not found
     */
    @Override
    public void removeFirstInstance(T value) throws IllegalArgumentException {
        Node<T> currentNode = head;
        Node<T> previousNode = null;

        // Search for the node with the specified value
        while (currentNode != null && !currentNode.getData().equals(value)) {
            previousNode = currentNode;
            currentNode = currentNode.getNext();
        }

        // If the node with the specified value is not found
        if (currentNode == null) {
            throw new IllegalArgumentException("Passed-in argument value does not exist in Linked List.");
        }

        // Perform the removal action
        if (previousNode == null) {
            // If the node to remove is the head
            head = currentNode.getNext();
        } else {
            // If the node to remove is not the head
            previousNode.setNext(currentNode.getNext());
        }

        // If removing the tail, update the tail to the new last node
        if (currentNode.getNext() == null) {
            tail = previousNode;
        }

    }

    /**
     * Removes all the instance of an object reference. Removes currentNode by substituting previousNode with the reference of the node after currentNode, where currentNode has the value we wish to remove.
     * @throws IllegalArgumentException if instance is not found
     */
    @Override
    public void removeAllInstances(T value) throws IllegalArgumentException {
        Node<T> currentNode = head;
        Node<T> previousNode = null;
        boolean removedAtLeastOnce = false;

        while (currentNode != null) {
            if (currentNode.getData().equals(value)) {
                // Remove the current node
                if (previousNode == null) {
                    // If the node to remove is the head
                    head = currentNode.getNext();
                } else {
                    // If the node to remove is not the head
                    previousNode.setNext(currentNode.getNext());
                }
                removedAtLeastOnce = true;
                // If removing the tail, update the tail to the new last node
                if (currentNode.getNext() == null) {
                    tail = previousNode;
                }
            }
            else {
                // Move to the next node
                previousNode = currentNode;
            }

            // Move to the next node, even if the current node is removed
            currentNode = currentNode.getNext();
        }

        if (!removedAtLeastOnce) {
            // If the value is not found in the list
            throw new IllegalArgumentException("Passed-in argument value does not exist in Linked List.");
        }
    }


    @Override
    /**
     * O(1) if we increment and decrement based on the other methods, otherwise O(n) if we traverse the LinkedList to get the size
     * @return the size of the Linked List (that is, number of nodes in the Linked List)
     */
    public int getSize(){
        if(isEmpty()){
            return 0;
        }

        int size = 0;
        Node<T> currentNode = head;
        while(currentNode !=null){
            currentNode = currentNode.getNext();
            size++;
        }
        return size;
    }

    @Override
    public boolean isEmpty(){
        if (head == null){
            return true;
        }

        return false;
    }

    /**
     * Simple toStirng method
     * @return the String representation of the SLL.
     */
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();

        sb.append("[");
        if(head != null){
            Node<T> curr = head;
            while(curr != null){

                if (curr.getNext() == null){
                    sb.append(curr.getData());
                }
                else{
                    sb.append(curr.getData() + ", ");
                }
                curr = curr.getNext();
            }
        }
        sb.append("]");

        return sb.toString();
    }


    /**
     * Accessor method for the Head Node
     * @return returns the Head Node
     */
    public Node<T> getHeadData(){
        return head;
    }


    /**
     * Mutator method to set the head of the Linked List.
     * @param inHead the new head
     */
    public void setHeadData(Node<T> inHead){
        head = inHead;
    }


    /**
     * Accessor method for the Tail Node
     * @return returns the Tail Node
     */
    public Node<T> getTailData(){
        return tail;
    }


    /**
     * Mutator method to set the Tail of the Linked List.
     * @param inTail the new Tail
     */
    public void setTailData(Node<T> inTail){
        tail = inTail;
    }


}