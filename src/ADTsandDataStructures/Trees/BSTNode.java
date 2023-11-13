package ADTsandDataStructures.Trees;

/**
 * Node class for Binary Trees
 * @author Faycal Kilali
 * @version 1.1
 * @param <T> parameterized type
 */
public class BSTNode<T> {
    private T data;

    private int duplicates;
    private BSTNode<T> leftChild;
    private BSTNode<T> rightChild;

    public BSTNode(){
        this.data = null;
        this.leftChild = null;
        this.rightChild = null;
        duplicates = 0;
    }

    public BSTNode(T inData){
        this.data = inData;
        this.leftChild = null;
        this.rightChild = null;
    }
    public T getData(){
        return data;
    }

    public void setData(T inData){
        this.data = inData;
    }

    public BSTNode<T> getLeftChild(){
        return leftChild;
    }

    public void setLeftChild(BSTNode<T> inLeftChild){
        leftChild = inLeftChild;
    }

    public BSTNode<T> getRightChild(){
        return rightChild;
    }

    public void setRightChild(BSTNode<T> inRightChild){
        rightChild = inRightChild;
    }


    public int getDuplicate(){
        return duplicates;
    }

    public void setDuplicate(int inDuplicates){
        duplicates = inDuplicates;
    }

    public int getNumChildren(){
        int numOfChildren = 0;
        if (this.leftChild != null){
            numOfChildren++;
        }
        if (this.rightChild != null){
            numOfChildren++;
        }
        return numOfChildren;
    }


}
