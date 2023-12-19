package ADTsandDataStructures.Graphs;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
class GraphTest {

    @Test
    void isEmpty() {
        Graph<String> graph = new Graph<String>();
        assertTrue(graph.isEmpty());

        graph.addVertex("A");
        assertFalse(graph.isEmpty());
    }

    @Test
    void isFull() {
        Graph<String> graph = new Graph<String>();
        assertFalse(graph.isFull());

        // Fill the graph to its capacity
        for (int i = 0; i < 30; i++) {
            graph.addVertex("V" + i);
        }

        assertTrue(graph.isFull());
    }

    @Test
    void addVertex() {
        Graph<String> graph = new Graph<String>();
        graph.addVertex("A");
        assertTrue(graph.hasVertex("A"));
    }

    @Test
    void hasVertex() {
        Graph<String> graph = new Graph<String>();
        graph.addVertex("A");
        assertTrue(graph.hasVertex("A"));
        assertFalse(graph.hasVertex("B"));
    }

    @Test
    void addEdge() {
        Graph<String> graph = new Graph<String>();
        graph.addVertex("A");
        graph.addVertex("B");

        graph.addEdge("A", "B");

        assertTrue(graph.getEdges().contains("A") && graph.getEdges().contains( "B"));
    }

    @Test
    void getToVertices() {
        Graph<String> graph = new Graph<String>();
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addEdge("A", "B");

        assertEquals(1, graph.getToVertices("A").size());
        assertTrue(graph.getToVertices("A").contains("B"));
    }

    @Test
    void markVertex() {
        Graph<String> graph = new Graph<String>();
        graph.addVertex("A");
        graph.markVertex("A");

        assertTrue(graph.isMarked("A"));
    }

    @Test
    void clearMarks() {
        Graph<String> graph = new Graph<String>();
        graph.addVertex("A");
        graph.markVertex("A");

        graph.clearMarks();

        assertFalse(graph.isMarked("A"));
    }

    @Test
    void isMarked() {
        Graph<String> graph = new Graph<String>();
        graph.addVertex("A");
        graph.markVertex("A");

        assertTrue(graph.isMarked("A"));
        assertFalse(graph.isMarked("B"));
    }

    @Test
    void getUnmarked() {
        Graph<String> graph = new Graph<String>();
        graph.addVertex("A");
        graph.markVertex("A");

        assertNull(graph.getUnmarked());
    }

    @Test
    void getEdges() {
        Graph<String> graph = new Graph<String>();
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addEdge("A", "B");

        assertEquals(1, graph.getEdges().size());
        for (int i = 0; i < graph.getEdges().size(); i++)
        {
        System.out.println(graph.getEdges().get(i));
        }

    }

    // Additional test cases for other methods...

}