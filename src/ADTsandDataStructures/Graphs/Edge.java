package ADTsandDataStructures.Graphs;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an edge between two vertices.
 * @implSpec implemented the tuple as follows: LHS of the tuple as fromVertex, and the RHS of the tuple as toVertex
 * @param <T> the type of vertices
 */
public class Edge<T> {
    private final T fromVertex;
    private final T toVertex;

    public Edge(T fromVertex, T toVertex) {
        this.fromVertex = fromVertex;
        this.toVertex = toVertex;
    }

    /**
     * Retrieves the LHS of the tuple
     * @return retrieves a from (a,b).
     */
    public T getFromVertex() {
        return fromVertex;
    }

    /**
     * Retrieves the RHS of the tuple
     * @return retrieves b from (a,b).
     */
    public T getToVertex() {
        return toVertex;
    }
}