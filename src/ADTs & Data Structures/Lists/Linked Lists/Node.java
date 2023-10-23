/**
 * This Node class is made for double-linked lists. However, it can also be used for single-linked lists.
 * @implNote We've opted not to extend the Node class, as a lot of the code in LinkedList uses type Node<T>.
If we extend to say, DLLNode, we'll have to rewrite almost the entire code-base of LinkedList rather than merely extend it.
 * @param <T>, any generic object is acceptable.
 */

public class Node<T>{
        private T data; // Node's reference to its inner data
        private Node<T> reference; // Reference to the next node

        private Node<T> prevReference; // pointer to the preceding node in the chain


        /**
         * Constructor for objects of class Node with null reference
         * @param inData the reference of the object that the Node's data will hold as a pointer.
         */
        public Node(T inData)
        {
                data = inData;
                reference = null;
                prevReference = null;
        }

        /**
         * Constructor for objects of class Node, for singly-linked lists
         * @implNote to preserve extensibility, this class will exist for any number of linked-lists.
         * @param inData the object to direct the pointer to
         * @param inReference the next node to direct the pointer to in the chain of nodes
         */
        public Node(T inData, Node<T> inReference)
        {
                data = inData;
                reference = inReference;
        }


        /**
         * Constructor for objects of class Node, for Doubly-Linked Lists
         * @param inData the object to direct the pointer to
         * @param inReference the next node to direct the pointer to in the chain of nodes
         * @param inPrevReference the previous node to direct the pointer to in the chain of nodes
         */
        public Node(T inData, Node<T> inReference, Node<T> inPrevReference)
        {
                data = inData;
                reference = inReference;
                prevReference = inPrevReference;
        }

        public Node<T> getNext(){
                return reference;
        }

        public void setNext(Node<T> node){
                reference = node;
        }

        public T getData(){
                return data;
        }

        public void setData(T inData){
                data = inData;
        }


        /*
                Belwo are additions to form Doubly-Linked Lists to the Node class
         */
        /**
         * Accessor method for previous node in the chain
         * @return the previous node reference
         */
        public Node<T> getPrevReference(){

                return prevReference;
        }

        /**
         * Mutator method for the previous node in the chain
         * @param previous what to set the previous node reference to
         */
        public void setPrevReference(Node<T> previous){
                prevReference = previous;
        }

}