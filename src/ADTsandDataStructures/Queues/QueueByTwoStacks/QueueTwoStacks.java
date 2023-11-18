/**
 * A Queue supporting two stacks operations. Underlying Data Structure is a Singly Linked List.
 * @author Faycal Kilali, Dylan Kim
 * @param <T> the type of Object held in the Queue
 */
public class QueueTwoStacks<T> implements QueueTwoStacksADT <T> {
    private int size;
    private Stack<T> mainStack;
    private Stack<T> helperStack;
    private boolean found;
    private int location;

    public QueueTwoStacks(){
        size = 0;
        mainStack = new Stack();
        helperStack = new Stack();
        found = false;
        location = -1;
    }

    /**
     * Helper method that moves all element in mainStack to helperStack
     * @TimeComplexity: O(n): since we are moving all elements to another, it is linearly proportional to the number of elements
     */
    private void moveAllQueToHelper(){
        while(!mainStack.isEmpty()){
            try{
                T element = mainStack.top();
                helperStack.push(element);
                mainStack.pop();
            }
            catch(StackOverflowException soe){
                System.out.println("StackOverflowException Occurred. Please check your Stack code");
            }
            catch(StackUnderflowException sue){
                System.out.println("StackUnderflowException Occurred. Please check your Stack code");
            }
        }
    }

    /**
     * Helper method that moves all elements in helperStack to mainStack
     * * @TimeComplexity: O(n): since we are moving all elements to another, it is linearly proportional to the number of elements
     */
    private void moveAllHelperToQue(){
        while(!helperStack.isEmpty()){
            try{
                T element = helperStack.top();
                mainStack.push(element);
                helperStack.pop();
            }
            catch(StackOverflowException soe){
                System.out.println("StackOverflowException Occurred. Please check your Stack code");
            }
            catch(StackUnderflowException sue){
                System.out.println("StackUnderflowException Occurred. Please check your Stack code");
            }
        }
    }

    /**
     * Method that adds a new element at the rear(top) of the mainStack
     * @param elements: the element that is going to be added to the mainStack
     * @throws QueueOverflowException
     * @TimeComplexity O(1): It is not related to the number of elements, it is constant
     * Algorithm
     *      1. Push the element to the mainStack
     *      2. increase the 'size' variable by 1
     */
    public void enqueue(T elements) throws QueueOverflowException{
        if(mainStack.isFull()){
            throw new QueueOverflowException("Failed to add: your queue is full");
        }
        try{
            mainStack.push(elements);
        }
        catch(StackOverflowException soe){
            System.out.println("StackOverFlowException Occurred, please check your Stack code");
        }
        size++;
    }

    /**
     * Method removes one element from the front of the mainStack
     * @return the element that is removed
     * @throws QueueUnderflowException
     * @TimeComplexity O(n): since we have to move all elements back and forth, the time complexity will increase
     *                       linearly proportional to the number of elements
     * Algorithm
     *      1. Moves all elements from mainStack to helperStack
     *      2. Since the element that was originally at front of mainStack is now located at the top of the helperStack
     *         we can remove it. Pop the helperStack
     *      3. Moves all other elements back to mainStack
     *      4. Decrease 'size' variable by 1
     */
    public T dequeue() throws QueueUnderflowException{
        if(mainStack.isEmpty()){
            throw new QueueUnderflowException("Failed to remove: your queue is empty");
        }
        T element = null;
        try{
            moveAllQueToHelper();
            element = helperStack.top();
            helperStack.pop();
            moveAllHelperToQue();
        }
        catch(StackUnderflowException sue){
            System.out.println("StackUnderflowException Occurred. Please check your Stack code");
        }
        size--;
        return element;
    }

    /**
     * Method checks whether the mainStack is full or not. Since we implemented Singly Linked List
     * there is no bound of size, thus it always returns false.
     * @return false
     * @TimeComplexity O(1): It is not related to the number of elements, it is constant
     */
    public boolean isFull(){
        return false;
    }

    /**
     * method checks whether the mainStack is empty or not. Since every single enququing and dequeuiung
     * updates the value of instance variable, size, it just compares the value of size and zero
     * @return true if the mainStack is empty, otherwise, false
     * @TimeComplexity O(1): It is not related to the number of elements, it is constant
     */
    public boolean isEmpty(){
        return (size==0)? true:false;
    }

    /**
     * Method that informs number of elements included in the mainStack.
     * @return size
     * @TimeComplexity O(1): It is not related to the size of the mainStack, it is constant
     */
    public int size(){
        return size;
    }

    /**
     * Method that find the location of the parameter in the mainStack.
     * @param data: data that the method is going to search
     * @return If data exists in the mainStack, returns the index number of data in mainStack. If not, return -1
     * @TimeComplexity O(n): Since it search every single element in the mainStack, the worst time complexity would be linear
     * Algorithm
     *      1. Create crrElement and Re-initialize found and location.
     *      2. Store top value of mainStack and check whether it is equal to the data
     *          - If it is, change 'found' to true, and terminate the loop
     *      3. Move top element to the helperStack
     *      4. Repeat this until it finds same value with data in mainStack, or it reaches the end of the mainStack
     *      5. Check found value
     *          - if it's false, which means that it couldn't find 'data', return -1;
     *          - if it's true, return location
     */
    public int find(T data){
        T crrElement = null;
        found = false;
        location = -1;
        while(!mainStack.isEmpty()) {
            location++;
            try {
                crrElement = mainStack.top();
                if (crrElement == data) {
                    found = true;
                    break;
                }
                helperStack.push(crrElement);
                mainStack.pop();
            } catch (StackUnderflowException e) {
                System.out.println("StackUnderflowException Occurred. Please check your Stack code");
            }
            catch(StackOverflowException e){
                System.out.println("StackOverFlowException Occurred");
            }
        }
        moveAllHelperToQue();
        if(!found){
            return -1;
        }
        return location;
    }

    /**
     * Method that return boolean value whether the mainStack contains the parameter, or not
     * @param data: the value that is going to be searched in the mainStack
     * @return: "true" if mainStack contains data, "false" if not
     * @TimeComplexity O(n): since we are calling find, it is also linear
     */
    public boolean contains(T data){
        find(data);
        return found;
    }

    /**
     * Method that reverse the entire order of elements in mainStack
     * @TimeComplexity O(n): Since we have to move all element from mainStack to helperStack, it is linearly proportional
     *                       to the number of elements
     * Algorithm
     *      1. Move all elements from mainStack to helperStack (At this moment element's order flipped)
     *      2. Let mainStack become equal to helperStack
     *      3. Reassign helperStack to new Stack
     */
    public void reverse(){
        moveAllQueToHelper();
        mainStack = helperStack;
        helperStack = new Stack<T>();
    }

    /**
     * Method that print all element in mainStack from rear to front
     * @return "[elements in mainStack from rear to front]"
     * @TimeComplexity O(n): To print all elements in mainStack, we have to check every single elements
     *                       Thus, it is linearly proportioanl to the number of elements
     * Algorithm
     *      1. Top value of mainStack is stored in 'element' variable.
     *      2. StringBuilder object receive the value of 'element'
     *      3. Add 'element' to the helperStack and pop the mainStack
     *      4. Repeat this until mainStack become empty
     *      5. Put all elements in helperStack back to mainStack and return string value stored in the StringBuilder
     */
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Queue: [");
        while(!mainStack.isEmpty()){
            try {
                T element = mainStack.top();
                sb.append(""+element);
                helperStack.push(element);
                mainStack.pop();
            }
            catch(StackOverflowException soe){
                System.out.println("StackOverflowException Occurred. Please check your Stack code");
            }
            catch(StackUnderflowException sue){
                System.out.println("StackUnderflowException Occurred. Please check your Stack code");
            }

            if(!mainStack.isEmpty()){
                sb.append(", ");
            }
        }
        moveAllHelperToQue();
        sb.append("]");
        return sb.toString();
    }


}
