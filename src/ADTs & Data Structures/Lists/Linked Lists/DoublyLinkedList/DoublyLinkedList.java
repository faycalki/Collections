/**
 *
 * @implSpec Doubly Linked List Implementation by extending a Linked List
 * @implNote This Implementation attempts to minimize overriding the methods of Linked List.
 * @author Faycal Kilali, Dylan Kim
 * @version 1.2
 * @param <T>, a generic parameter type.
 */

public class DoublyLinkedList<T> extends SinglyLinkedList<T> implements ListADT<T>, LinkedListADT<T>, DoublyLinkedListADT<T> {

    public DoublyLinkedList() {
    }

    /**
     * Retrieves the nth position node's data (position ranges from 0 to size of Linked List)
     * @param position the position to retrieve the node's data from
     * @return the data of the position node
     */
    public T checkDataAtLocation(int position) {
        return traverseLinkedListToPosition(position).getData();
    }

    /**
     * Adds the Node that contains the data parameter to the front of the Linked List.
     * @param data the memory pointer of the object held within the added Node
     */
    @Override
    public void addToFront(T data){
        super.addToFront(data);
        if (super.getHeadData().getNext() != null){
            super.getHeadData().getNext().setPrevReference(getHeadData());
        }
    }

    /**
     * Adds a new node with the specified data to the back of the linked list.
     * @implSpec this can be lowered to O(1) if the tail is tracked appropriately in LinkedList
     * @param data The data to add to the back of the linked list.
     */
    @Override
    public void addToBack(T data) {
        Node<T> prevNode = super.getTailData();
        super.addToBack(data);
        super.getTailData().setPrevReference(prevNode); // Set new tail previous reference to the previous tail
    }

    /**
     * Adds a Node after dataFind Node. Does so by comparing the object value inside dataFind.
     * @param inData the data we'll use to create a new Node
     * @param dataFind the node that we'll add after that already exists in the Linked List
     * @throws NullPointerException
     */
    @Override
    public void addAfter(T inData, T dataFind) throws NullPointerException{

        if (inData == null){
            throw new NullPointerException("Parameterized object is null");
        }

        // Case where the Linked List is empty
        if(super.getHeadData() == null){
            addToFront(inData);
            return;
        }

        Node<T> insertNode = new Node<T>(inData);
        Node<T> currNode = traverseLinkedList(dataFind); // Acquire Node at dataFind (or the Node right before it if dataFind can't be found)
        // There are two cases. One where we are bounded by dataFind, in which case the following applies. One where we are bounded by Null
        // but not by dataFind, in which case, we add to the end of the list.
        //System.out.println(currNode.getData());

        // Case where we are bounded by dataFind
        if (currNode.getNext() != null){
            insertNode.setNext(currNode.getNext());
        }
        else{
            super.setTailData(insertNode);
        }

        currNode.setNext(insertNode); // Updates the previous node's pointer to the newly added node

        // Additions
        insertNode.setPrevReference(currNode);
        if ((insertNode.getNext()) != null){
            insertNode.getNext().setPrevReference(insertNode);
        }
    }



    /**
     * Removes and returns the data from the node at the front of the linked list.
     *
     * @return T The data from the front of the linked list.
     */
    @Override
    public T removeFromFront() {
        if (super.getHeadData().getNext() != null){
            super.getHeadData().getNext().setPrevReference(null); // Gets the second Node then sets its previous reference to null
        }
        return super.removeFromFront(); // Removes the first Node (the head Node) and sets the second Node as the new head Node. Returns the removed Node.
    }



    /**
     * Removes and returns the data from the node at the back of the linked list.
     *
     * @return T The data from the back of the linked list.
     */
    public T removeFromBack() {
        return super.removeFromBack();
    }


    /**
     * Removes the first instance of an object reference. Removes currentNode by substituting previousNode with the reference of the node after currentNode, where currentNode has the value we wish to remove.
     * Linking-correction should only be performed by the Nodes after the removed Node. All previous nodes to that should have proper linking performed by their appropriate methods.
     * @param data the first Node of this data is to be removed in the chain
     * @throws IllegalArgumentException if instance is not found
     */
    @Override
    public void removeFirstInstance(T data) throws IllegalArgumentException {
        Node<T> currentNode = getHeadData();
        Node<T> previousNode = null;

        // Search for the node with the specified value
        while (currentNode != null && !currentNode.getData().equals(data)) {
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
            setHeadData(currentNode.getNext());
            getHeadData().setPrevReference(null);

            // Double-Linking
            if (getHeadData().getNext() != null){
                getHeadData().getNext().setPrevReference(getHeadData());
            }

        }

        else {
            // If the node to remove is not the head
            previousNode.setNext(currentNode.getNext());

            // Double-Linking
            currentNode.getNext().setPrevReference(previousNode);
        }

        // If removing the tail, update the tail to the new last node
        if (currentNode.getNext() == null) {
            super.setTailData(previousNode);
        }
    }

    /**
     * Removes all the instance of an object reference. Removes currentNode by substituting previousNode with the reference of the node after currentNode, where currentNode has the value we wish to remove.
     * Linking-correction should only be performed by the Nodes after the removed Node(s). All previous nodes to that should have proper linking performed by their appropriate methods.
     * @param data the Nodes that contain this data are to be removed
     * @throws IllegalArgumentException if instance is not found
     */
    @Override
    public void removeAllInstances(T data) throws IllegalArgumentException {
        Node<T> currentNode = getHeadData();
        Node<T> previousNode = null;
        boolean removedAtLeastOnce = false;

        while (currentNode != null) {
            if (currentNode.getData().equals(data)) {
                // Remove the current node
                if (previousNode == null) {
                    // If the node to remove is the head
                    setHeadData(currentNode.getNext());
                    getHeadData().setPrevReference(null);

                    // Double-Linking
                    if(getHeadData().getNext() != null){
                        getHeadData().getNext().setPrevReference(getHeadData());
                    }
                }
                else {
                    // If the node to remove is not the head
                    previousNode.setNext(currentNode.getNext());
                    // Double-Linking
                    currentNode.getNext().setPrevReference(previousNode);
                }
                removedAtLeastOnce = true;
                // If removing the tail, update the tail to the new last node
                if (currentNode.getNext() == null) {
                    super.setTailData(previousNode);
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



    /**
     * Returns a string representation of the linked list.
     *
     * @return String A string representation of the linked list.
     */
    @Override
    public String toString() {
        return super.toString();
    }
}


