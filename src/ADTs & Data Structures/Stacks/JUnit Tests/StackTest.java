import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

class StackTest {
    Stack<Integer> stack;
    @BeforeEach
    void setUp() {
        stack = new Stack<Integer>();
    }

    @AfterEach
    void tearDown() {
        stack = null;
    }

    @Test
    void push() {
        // We are aware that the src.StackOverflowException makes no sense in our implementation so we assert it won't be thrown
        assertDoesNotThrow(() -> stack.push(1));
    }

    @Test
    void pop() {

        try{
            stack.push(1);
            stack.push(2);
            stack.push(3);
            stack.pop();
            assert (stack.top() == 2);
        }

        catch(StackUnderflowException | StackOverflowException e){
            e.printStackTrace();
            fail();
        }

    }

    @Test
    void top() {
        try {
            stack.push(1);
            assert(stack.top() == 1);
        }
        catch(StackOverflowException | StackUnderflowException e) {
            fail();
        }
        }


    @Test
    void isFull() {
        assert (stack.isFull() == false);
    }

    @Test
    void isEmpty() {
        try {
            while (stack.top() != null){
                stack.pop();
            }
        }
        catch(StackUnderflowException e){
            assert(stack.isEmpty() == true);
        }

    }
}