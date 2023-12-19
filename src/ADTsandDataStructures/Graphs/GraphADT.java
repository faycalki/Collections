package ADTsandDataStructures.Graphs;

import java.util.ArrayList;

/**
 * Simple interface for a generic Graph.Graph object that
 * provides the basic range of behaviors for a Graph.Graph
 * to function. This class follows the textbook in
 * having a system of 'marking' Vertices for traversals.
 * 
 * We assume this Graph.Graph is directed for the purposes of
 * this lab.
 * 
 * Recall that the two options seen in class for the
 * underlying data structure are the Adjacency Matrix
 * and the Adjacency List. You are free to use either
 * one for this implementation.
 * 
 * These methods are largely borrowed from the description
 * of the Graph.GraphADT from the course textbook.
 * 
 * @author Ada Clevinger
 * @version Nov 26, 2023
 * 
 */

public interface GraphADT<T>
{
    
    public abstract boolean isEmpty();
    
    public abstract boolean isFull();
    
    public abstract void addVertex(T vertex);
    
    public abstract boolean hasVertex(T vertex);
    
    public abstract void addEdge(T fromVertex, T toVertex);
    
    public abstract ArrayList<T> getToVertices(T vertex);
    
    public abstract void markVertex(T vertex);
    
    public abstract void clearMarks();
    
    public abstract boolean isMarked(T vertex);
    
    public abstract T getUnmarked();

    public abstract String toString();
    
}
