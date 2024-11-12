package graph;

import static org.junit.Assert.*;

import java.util.Collections;

import org.junit.Test;

/**
 * Tests for ConcreteEdgesGraph.
 * 
 * This class runs the GraphInstanceTest tests against ConcreteEdgesGraph, as
 * well as tests for that particular implementation.
 * 
 * Tests against the Graph spec should be in GraphInstanceTest.
 */
public class ConcreteEdgesGraphTest extends GraphInstanceTest {
    
    /*
     * Provide a ConcreteEdgesGraph for tests in GraphInstanceTest.
     */
    @Override public Graph<String> emptyInstance() {
        return new ConcreteEdgesGraph();
    }
    
    /*
     * Testing ConcreteEdgesGraph...
     */
    
    // Testing strategy for ConcreteEdgesGraph.toString()
    //   Test that the string representation of the graph is correct (i.e., lists vertices and edges)
    @Test
    public void testToString() {
        Graph<String> graph = emptyInstance();
        graph.add("A");
        graph.add("B");
        graph.set("A", "B", 5);
        
        String expected = "Vertices: [A, B], Edges: [A -> B (5)]";
        
    }
    
    /*
     * Testing Edge...
     */
    
    // Testing strategy for Edge
    // Test adding and removing edges between vertices.
    
    @Test
    public void testSetEdge() {
        Graph<String> graph = emptyInstance();
        graph.add("A");
        graph.add("B");
        graph.set("A", "B", 5);
        
        // Check if edge exists from A to B with correct weight
        assertTrue("Graph should contain edge from A to B", graph.sources("B").containsKey("A"));
        assertEquals("Graph should contain edge from A to B with weight 5", 5, (int) graph.sources("B").get("A"));
    }

    @Test
    public void testRemoveEdge() {
        Graph<String> graph = emptyInstance();
        graph.add("A");
        graph.add("B");
        graph.set("A", "B", 5);
        
        // Remove edge from A to B
        graph.sources("B").remove("A");
       
    }

    @Test
    public void testInitialVerticesEmpty() {
        assertEquals("expected new graph to have no vertices",
                Collections.emptySet(), emptyInstance().vertices());
    }

    @Test
    public void testAddVertex() {
        Graph<String> graph = emptyInstance();
        graph.add("A");
        assertTrue("Graph should contain vertex A", graph.vertices().contains("A"));
    }

    @Test
    public void testAddDuplicateVertex() {
        Graph<String> graph = emptyInstance();
        graph.add("A");
        graph.add("A");
        assertEquals("Graph should contain only one instance of vertex A", 1, graph.vertices().size());
    }

    @Test
    public void testRemoveVertex() {
        Graph<String> graph = emptyInstance();
        graph.add("A");
        graph.add("B");
        graph.set("A", "B", 5);
        
        graph.remove("B");
        
        assertFalse("Graph should no longer contain vertex B", graph.vertices().contains("B"));
    }

    @Test
    public void testSourcesAndTargets() {
        Graph<String> graph = emptyInstance();
        graph.add("A");
        graph.add("B");
        graph.set("A", "B", 5);
        
        // Check sources and targets
        assertTrue("A should be a source of B", graph.sources("B").containsKey("A"));
        assertTrue("B should be a target of A", graph.targets("A").containsKey("B"));
    }
}



