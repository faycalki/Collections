package ADTsandDataStructures.Graphs;

import java.util.ArrayList;

/**
 * This is an implementation of a Graph.Graph ADT using an ArrayList.
 * A graph is a freeform data structure made of vertices connected by edges.
 * A graph, G, can be defined as G = (V, E). Where V(G) is a finite, nonempty set of vertieces and E(G) is a set of edges
 * written as pairs of vertices.
 * @author Faycal Kilali, Dylan Kim
 * @implSpec This is implemented as a unweighted graph (hence the boolean matrix). However, it can be easily adapted to a weighted graph by changing the type of the matrix and appropriately assigning weights.
 * @implNote [row][column].
 * @param <T> the parameterized type
 */
public class Graph<T> implements GraphADT<T> {
    private boolean[][] matrix;
    private int size;
    private final int DEF_CAP = 60;
    private int capacity = DEF_CAP;
    private T[] referenceMatrix;
    private boolean[] arrayOfMarks;

    public Graph(){
        matrix = new boolean[DEF_CAP][DEF_CAP];
        referenceMatrix = (T[]) new Object[DEF_CAP];
        size = 0;
        arrayOfMarks = new boolean[DEF_CAP];
        
    }


    /**
     * Checks whether the graph is devoid of nodes or not.
     * Time Complexity: 1
     * @return true if the graph is devoid of nodes, false otherwise.
     */
    @Override
    public boolean isEmpty() {
        if (size == 0){
            return true;
        }
        return false;
    }

    /**
     * Checks whether the graph is at capacity
     * Time Complexity: O(1)
     * @return true if graph is at capacity, false otherwise
     */
    @Override
    public boolean isFull() {
        if (size == capacity){
            return true;
        }
        return false;
    }


    /**
     * Adds the Vertex to the matrix
     * Time Complexity: (n+1)
     * @implSpec the vertex must be
     * @param vertex
     */
    @Override
    public void addVertex(T vertex) {
        if (size != capacity){
        referenceMatrix[size] = vertex;
        size++;
        }

        else{
            enLarge();
            referenceMatrix[size] = vertex;
            size++;
        }



        // If we were to use a remove method
        //for (int i = 0; i < referenceMatrix.length; i++){
        //}


    }

    /**
     * Enlarges the underlying arrays
     * Time Complexity:
     */
    private void enLarge(){
        capacity = capacity * 2;
        boolean[][] largerMatrix = new boolean[capacity][capacity];
        T[] largerReferenceMatrix = (T[]) new Object[capacity];
        boolean[] largerArrayOfMarks = new boolean[capacity];
        System.arraycopy(matrix, 0, largerMatrix, 0, matrix.length);
        System.arraycopy(referenceMatrix, 0, largerReferenceMatrix, 0, size);
        System.arraycopy(arrayOfMarks, 0, largerArrayOfMarks, 0, arrayOfMarks.length);
        matrix = largerMatrix;
        referenceMatrix = largerReferenceMatrix;
        arrayOfMarks = largerArrayOfMarks;
    }

    @Override
    public boolean hasVertex(T vertex) {
        for (int i = 0; i < size; i++) {
            if (referenceMatrix[i] != null && referenceMatrix[i].equals(vertex)) {
                return true;
            }
        }
        return false;
    }


    /**
     * Creates an edge between two vertices.
     * @implSpec the weight of any two vertices is 1 if the graph is unweighted.
     * @implNote The weights are strictly integers, for now.
     * @param fromVertex the direction of the edge coming from this vertex
     * @param toVertex the edge coming towards this vertex
     */
    @Override
    public void addEdge(T fromVertex, T toVertex) {
        int fromVertexIndex = -1;
        int toVertexIndex = -1;

        // Find indices of the vertices in the referenceMatrix
        for (int i = 0; i < size; i++) {
            if (referenceMatrix[i] != null && referenceMatrix[i].equals(fromVertex)) {
                fromVertexIndex = i;
            } else if (referenceMatrix[i] != null && referenceMatrix[i].equals(toVertex)) {
                toVertexIndex = i;
            }
        }

        // If either vertex is not found, return
        if (fromVertexIndex == -1 || toVertexIndex == -1) {
            return;
        }

        // Set connections in both directions for an undirected graph
        matrix[fromVertexIndex][toVertexIndex] = true;
        matrix[toVertexIndex][fromVertexIndex] = true;
    }

    // Pre-resolution
//    /**
//     * Creates an edge between two vertices.
//     * @implSpec the weight of any two vertices is 1 if the graph is unweighted.
//     * @implNote The weights are strictly integers, for now.
//     * @param fromVertex the direction of the edge coming from this vertex
//     * @param toVertex the edge coming towards this vertex
//     */
//    @Override
//    public void addEdge(T fromVertex, T toVertex) {
//        int fromVertexIndex = 0;
//        int toVertexIndex = 0;
//        boolean foundFromVertex = false;
//        boolean foundToVertex = false;
//
//        for (int i = 0; i < size && !(foundFromVertex || foundToVertex); i++) {
//            if (referenceMatrix[i] != null && referenceMatrix[i].equals(fromVertex)) {
//                fromVertexIndex = i;
//                foundFromVertex = true;
//            } else if (referenceMatrix[i] != null && referenceMatrix[i].equals(toVertex)) {
//                toVertexIndex = i;
//                foundToVertex = true;
//            }
//        }
//
//        matrix[fromVertexIndex][toVertexIndex] = true;
//    }


    /**
     * Retrieves the connected vertices to parameterized vertex
     * @param vertex the root vertex to consider
     * @return the connected vertices to the root vertex
     */
    @Override
    public ArrayList<T> getToVertices(T vertex) {
        ArrayList<T> linkedVertices = new ArrayList<T>();
        int vertexIndex = -1;

        // Find the index of the vertex in the referenceMatrix
        for (int i = 0; i < size; i++) {
            if (referenceMatrix[i] != null && referenceMatrix[i].equals(vertex)) {
                vertexIndex = i;
                break;
            }
        }

        // If the vertex is not found, return an empty list
        if (vertexIndex == -1) {
            return linkedVertices;
        }

        // Check for connected vertices based on the matrix
        for (int i = 0; i < size; i++) {
            // Add the connected vertex if the matrix indicates a connection
            if (matrix[i][vertexIndex] && referenceMatrix[i] != null) {
                linkedVertices.add(referenceMatrix[i]);
            }
        }

        return linkedVertices;
    }



    /**
     * Marks a vertex as previously traversed through
     * @param vertex the vertex to mark
     */
    @Override
    public void markVertex(T vertex) {
        for(int i=0;i<size;i++){
            if(referenceMatrix[i].equals(vertex)){
                arrayOfMarks[i] = true;
                return;
            }
        }
    }


    /**
     * Clears all the marks on the nodes
     */
    @Override
    public void clearMarks() {
        for(int i=0; i<size;i++){
            arrayOfMarks[i] = false;
        }
    }


    /**
     * Retrieves the state of whether a vertex has been traversed through or not.
     * @param vertex the vertex to check if it has been traversed through
     * @return true if previously traversed through, false otherwise
     */
    @Override
    public boolean isMarked(T vertex) {
        for (int i = 0; i < size; i++){
            if (referenceMatrix[i].equals(vertex)){
                return arrayOfMarks[i];
            }
        }
        return false;
    }

    /**
     * Retrieves a single unmarked vertex.
     * Can be used to check if a particular graph is disjoint, among other things.
     * @return an unmarked vertex
     */
    @Override
    public T getUnmarked() {
        for(int i=0;i<size;i++){
            if(!arrayOfMarks[i]){
                return referenceMatrix[i];
            }
        }
        return null;
    }

    /**
     * Gets the edges in the Graph.Graph E(G).
     */
    public ArrayList<Edge<T>> getEdges() {
        ArrayList<Edge<T>> edges = new ArrayList<Edge<T>>();

        // FromVertex will be the reference matrix index at that position
        // ToVertex will be the reference matrix index at the end of the edge
        // Which, for our boolean matrix, will be [fromVertex][toVertex]

        for (int row = 0; row < size; row++){
            for (int column = 0; column < size; column++){
                if (matrix[row][column] == true){
                    edges.add(new Edge<T>(referenceMatrix[row], referenceMatrix[column]));
                }
            }
        }
        return edges;
    }







    /**
     * @implSpec To be implemented
     * @return
     */
    @Override
    public String toString() {
        return super.toString();
    }

    public T getVertex(int i) {
        if (i < 0 || i >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + i);
        }

        return referenceMatrix[i];
    }


    /**
     * Method that takes the underlying Graph.Graph implementation
     * and derives an object of the type 'People' that is a
     * wrapper around the set of Vertices stored by the
     * underlying Graph.Graph.
     * <p>
     * Basically just 'getVertices' but into a class rather than
     * an ArrayList<T>.
     */
//   @Override
//   public People<T> getPeople() {
//       People<T> people = new People<T>();
//       for (int referenceIndex =0; referenceIndex < size; referenceIndex++){
//           people.addNewPerson(referenceMatrix[referenceIndex]);
//       }
//       return people;
//   }

    /**
     * Method that takes the underlying Graph.Graph implementation
     * and derives an object of the type 'Bonds' that is a
     * wrapper around the set of Edges stored by the
     * underlying Graph.Graph.
     * <p>
     * Basically just 'getEdges' but into a class rather than
     * an ArrayList<ArrayList<T>>. A trivial Graph.Edge class that
     * just holds two objects of type T may be helpful, but
     * that's up to you.
     */
 //   @Override
//    public Bonds<T> getBonds() {
//        Bonds<T> bonds = new Bonds<T>();
//
//        // FromVertex will be the reference matrix index at that position
//        // ToVertex will be the reference matrix index at the end of the edge
//        // Which, for our boolean matrix, will be [fromVertex][toVertex]
//
//        for (int row = 0; row < size; row++){
//            for (int column = 0; column < size; column++){
//                if (matrix[row][column] == true){
//                    bonds.addBond(referenceMatrix[row], referenceMatrix[column]);
//                }
//            }
//        }
//        return bonds;
//    }

    /**
     * Method that takes a People object and uses it to overwrite
     * the information represented by the underlying Graph.Graph object.
     * <p>
     * This should represent a wholesale replacement of the Vertices
     * in the underlying Graph.Graph; do not merge the existing set of
     * Vertices with the new People object.
     * <p>
     * This provides a convenient means of initializing our Graph.Graph
     * all at once instead of adding one Vertice at a time.
     * <p>
     * You should perform some amount of validating the Vertices
     * described in People to ensure that the existing Edges in
     * the underlying Graph.Graph are not invalidated by the change in
     * the set of Vertices.
     *
     * @param in
     */
//    @Override
//    public void updatePeople(People<T> in) {
//        // Replace all our vertices with the parameterized people object
//        // We must also check that the parameterized type does not invalidate the change in the set of vertices.
//
//        // TRIVIAL CASE VALIDATION:
//        if (in.getPeople().size() != size){
//            throw new UnsupportedOperationException("Trivially incompatible with current edges, given that each node has an edge to itself");
//        }
//
//        // TODO: validate the case where sizes differ.
//        //T[] tempReferenceMatrix = (T[])new Object[capacity];
//        //for (int i = 0; i < in.getPeople().size(); i++){
//        //    tempReferenceMatrix[i] = in.getPeople().get(i);
//        //}
//
//
//    }




    /**
     * Method that takes a Bonds object and uses it to overwrite
     * the information represented by the underlying Graph.Graph object.
     * <p>
     * This should represent a wholesale replacement of the Edges
     * in the underlying Graph.Graph; do not merge the existing set of
     * Edges with the new People object.
     * <p>
     * This provides a convenient means of initializing our Graph.Graph
     * all at once instead of adding one Graph.Edge at a time.
     * <p>
     * You should perform some amount of validating the Edges
     * described in Bonds to ensure that the appropriate Vertices
     * exists in the underlying Graph.Graph for these to work.
     *
     * @param in
     */
//    @Override
//    public void updateBonds(Bonds<T> in) {
//
//
//        // TRIVIAL CASE VALIDATION:
//        if (in.getFromVertex().size() != size || in.getToVertex().size() != size){
//            throw new UnsupportedOperationException("Trivially incompatible with current edges, given that each node has an edge to itself");
//        }
//
//        // TODO: validate the case where sizes differ.
//        //T[] tempReferenceMatrix = (T[])new Object[capacity];
//        //for (int i = 0; i < in.getPeople().size(); i++){
//        //    tempReferenceMatrix[i] = in.getPeople().get(i);
//        //}
//
//    }
}
