package ADTsandDataStructures.Trees;

import java.util.Iterator;

public interface BinaryTreeIteratorInterface<T> extends Iterator<T> {
    BSTNode<T> nextNode(); // better to find a work-around for using iterators for adding nodes to the tree
}