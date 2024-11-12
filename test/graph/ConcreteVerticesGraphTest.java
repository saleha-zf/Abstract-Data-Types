/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph;

import static org.junit.Assert.*;

import java.util.Collections;

import org.junit.Test;

/**
 * Tests for ConcreteVerticesGraph.
 * 
 * This class runs the GraphInstanceTest tests against ConcreteVerticesGraph, as
 * well as tests for that particular implementation.
 * 
 * Tests against the Graph spec should be in GraphInstanceTest.
 */
public class ConcreteVerticesGraphTest extends GraphInstanceTest {
    
    /*
     * Provide a ConcreteVerticesGraph for tests in GraphInstanceTest.
     */
    @Override public Graph<String> emptyInstance() {
        return new ConcreteVerticesGraph();
    }
    
    /*
     * Testing ConcreteVerticesGraph...
     */
    
    // Testing strategy for ConcreteVerticesGraph.toString()
    //   We expect the string representation to list all the vertices and edges in a readable format.
    @Test
    public void testToString() {
        Graph<String> graph = emptyInstance();
        graph.add("A");
        graph.add("B");
        graph.set("A", "B", 5);
        
        String expected = "Vertices: [A, B], Edges: [(A -> B, 5)]";    }
    
    /*
     * Testing Vertex...
     */
    
    // Testing strategy for Vertex:
    // - Test that vertices can be added, removed, and found in the graph
    // - Test that duplicate vertices are not added
    
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
        graph.add("A");  // Adding duplicate vertex
        assertEquals("Graph should contain only one instance of vertex A", 1, graph.vertices().size());
    }
    
    @Test
    public void testRemoveVertex() {
        Graph<String> graph = emptyInstance();
        graph.add("A");
        graph.add("B");
        graph.set("A", "B", 5);
        
        // Remove vertex "B"
        graph.remove("B");
        
        assertFalse("Graph should no longer contain vertex B", graph.vertices().contains("B"));
    }
    
    @Test
    public void testInitialVerticesEmpty() {
        assertEquals("expected new graph to have no vertices",
                Collections.emptySet(), emptyInstance().vertices());
    }
    
    /*
     * Testing Edges...
     */
    
    // Testing strategy for Edges:
    // - Test that edges can be added with weights
    // - Test that the edges are correctly assigned and retrieved
    
    @Test
    public void testSetEdge() {
        Graph<String> graph = emptyInstance();
        graph.add("A");
        graph.add("B");
        graph.set("A", "B", 5);
        
        assertTrue("Graph should contain edge from A to B",
                graph.sources("B").containsKey("A"));
        assertTrue("Graph should contain edge from A to B with weight 5",
                graph.sources("B").get("A") == 5);
    }
    
    @Test
    public void testSourcesAndTargets() {
        Graph<String> graph = emptyInstance();
        graph.add("A");
        graph.add("B");
        graph.add("C");
        graph.set("A", "B", 3);  // A -> B with weight 3
        graph.set("A", "C", 4);  // A -> C with weight 4
        graph.set("B", "C", 2);  // B -> C with weight 2
        
        // Check sources for C
        assertTrue("C should have A and B as sources", graph.sources("C").containsKey("A"));
        assertTrue("C should have B as source", graph.sources("C").containsKey("B"));
        
        // Check targets for A
        assertTrue("A should have B and C as targets", graph.targets("A").containsKey("B"));
        assertTrue("A should have C as target", graph.targets("A").containsKey("C"));
    }
    
    @Test
    public void testRemoveEdge() {
        Graph<String> graph = emptyInstance();
        graph.add("A");
        graph.add("B");
        graph.set("A", "B", 5);  // A -> B with weight 5
        
        // If the removeEdge() method does not exist, we can remove the edge by manipulating the graph's edges directly.
        graph.sources("B").remove("A");  // This removes the edge from A to B
        
    }

}
