
/**
 * Ordering based on Real Numbers
 * @author Faycal Kilali
 * @version Oct 20, 2023
 */
public class PriorityDouble implements Priority<PriorityDouble>
{

    private Double value;

    public PriorityDouble(Double inValue){
        value = inValue;
    }

    /**
     * Comparison of real numbers
     * @param other
     * @return true if our Double has a higher value, false otherwise.
     */

    public boolean isMoreImportant(PriorityDouble other){
        if (getValue() > other.getValue()){
            return true;
        }
        else{
            return false;
        }
    }


    /**
     * Obtain value of Double
     * @return
     */
    public Double getValue(){
        return value;
    }


    @Override
    public String toString() {
        return String.valueOf(value);
    }



}
