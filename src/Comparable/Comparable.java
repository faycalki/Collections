
/**
 General Interface for creating order in a Collection by comparing objects relative "size".
 @author Faycal Kilali
 @version 1.0
 **/

public interface Comparable<T>

{

    /**
     Compares the current object with the object o.
     @implNote should help order the objects of a collection within the internal representation structure, facilitating faster search.
     @param o the object we wish to compare the current object with
     @return negative integer if the current object is less than o, zero if they are the same, and positive integer if the current object is greater than the specified object o.
     The returned integer should be the relative "size" relationship between the object upon which the method is invoked and the object passed to the method as an argument.
     **/
    public int compareTo (T o);

}
