/**
 * Node class for Binary Trees
 * @author Faycal Kilali
 * @version 1.1
 * @param <T> parameterized type
 */
public class BTNode<T> {
    private T data;

    private int duplicates;
    private BTNode<T> leftChild;
    private BTNode<T> rightChild;

    public BTNode(){
        this.data = null;
        this.leftChild = null;
        this.rightChild = null;
        duplicates = 0;
    }

    public BTNode(T inData){
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

    public BTNode<T> getLeftChild(){
        return leftChild;
    }

    public void setLeftChild(BTNode<T> inLeftChild){
        leftChild = inLeftChild;
    }

    public BTNode<T> getRightChild(){
        return rightChild;
    }

    public void setRightChild(BTNode<T> inRightChild){
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
