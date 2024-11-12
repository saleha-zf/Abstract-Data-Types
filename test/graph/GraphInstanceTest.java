package graph;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

public abstract class GraphInstanceTest {

    public abstract Graph<String> emptyInstance();

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
        graph.set("B", "A", 10);

        // Testing sources for B
        Map<String, Integer> sourcesB = graph.sources("B");
        assertTrue("B should have A as a source", sourcesB.containsKey("A"));
        assertEquals("Edge from A to B should have weight 5", (int) sourcesB.get("A"), 5);

        // Testing targets for A
        Map<String, Integer> targetsA = graph.targets("A");
        assertTrue("A should have B as a target", targetsA.containsKey("B"));
        assertEquals("Edge from A to B should have weight 5", (int) targetsA.get("B"), 5);
    }
}
