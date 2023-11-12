
/**
 * Ordering based on Integers
 * @author Faycal Kilali
 * @version Oct 20, 2023
 */
public class PriorityInt implements Priority<PriorityInt>
{

    private Integer value;

    public PriorityInt(Integer inValue){
        value = value.valueOf(inValue);
    }

    /**
     * Comparison of Integers
     * @param other
     * @return true if our int has a higher value, false otherwise.
     */

    public boolean isMoreImportant(PriorityInt other){
        if (getValue() > other.getValue()){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Obtain value of int
     * @return
     */
    public int getValue(){
        return value;
    }


    @Override
    public String toString() {
        return String.valueOf(value);
    }



}
