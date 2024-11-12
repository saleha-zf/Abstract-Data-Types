package graph;

import static org.junit.Assert.*;
import java.util.Collections;
import java.util.Set;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;

public class GraphStaticTest {
    
    private Graph<String> graph;
    
    // This setup method will run before each test to create a new empty graph.
    @Before
    public void setUp() {
        graph = new ConcreteEdgesGraph(); // Assuming ConcreteEdgesGraph is your graph implementation.
    }

    // Testing strategy:
    // - testInitialVerticesEmpty(): test that a new graph has no vertices
    // - testAddVertex(): add vertices and check if they exist in the graph
    // - testAddDuplicateVertex(): add a vertex that already exists and check no duplication
    // - testSetEdge(): add edges and check if they exist with correct weights
    // - testRemoveVertex(): remove a vertex and verify it no longer exists
    // - testSourcesAndTargets(): check sources and targets for directed edges

    @Test
    public void testInitialVerticesEmpty() {
        // Verifying that an initially created graph has no vertices
        assertTrue("Graph should be empty initially", graph.vertices().isEmpty());
    }

    @Test
    public void testAddVertex() {
        // Adding a vertex to the graph and checking if it exists
        graph.add("A");
        Set<String> vertices = graph.vertices();
        assertTrue("Graph should contain vertex A", vertices.contains("A"));
    }

    @Test
    public void testAddDuplicateVertex() {
        // Adding a duplicate vertex and ensuring that it is not added again
        graph.add("A");
        int initialSize = graph.vertices().size();
        graph.add("A"); // Adding duplicate
        assertEquals("Graph should not have duplicate vertices", initialSize, graph.vertices().size());
    }

    @Test
    public void testSetEdge() {
        // Adding an edge and checking if it exists with the correct weight
        graph.add("A");
        graph.add("B");
        graph.set("A", "B", 10); // Setting an edge with a weight of 10

        // Check that the edge exists and the weight is correct
        Map<String, Integer> targets = graph.targets("A");
        assertEquals("Target for vertex A should have weight 10", Integer.valueOf(10), targets.get("B"));
    }

    @Test
    public void testRemoveVertex() {
        // Adding a vertex, removing it, and verifying it no longer exists
        graph.add("A");
        graph.remove("A");
        assertFalse("Graph should not contain vertex A after removal", graph.vertices().contains("A"));
    }

    @Test
    public void testSourcesAndTargets() {
        // Testing the sources and targets for a directed edge
        graph.add("A");
        graph.add("B");
        graph.set("A", "B", 10); // Directed edge from A to B

        // Verifying the sources and targets
        Map<String, Integer> sources = graph.sources("B");
        assertTrue("B should have A as source", sources.containsKey("A"));
        assertEquals("Weight of edge from A to B should be 10", Integer.valueOf(10), sources.get("A"));

        Map<String, Integer> targets = graph.targets("A");
        assertTrue("A should have B as target", targets.containsKey("B"));
        assertEquals("Weight of edge from A to B should be 10", Integer.valueOf(10), targets.get("B"));
    }

    // Testing strategy for empty()
    @Test
    public void testEmptyVerticesEmpty() {
        equals("Expected empty() graph to have no vertices");
             
    }

    
    // TODO test other vertex label types in Problem 3.2
}
