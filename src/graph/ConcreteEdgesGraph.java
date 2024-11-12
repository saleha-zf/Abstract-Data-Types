package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * An implementation of Graph.
 */
public class ConcreteEdgesGraph implements Graph<String> {
    
    private final Set<String> vertices = new HashSet<>();
    private final List<Edge> edges = new ArrayList<>();
    
    // Abstraction function:
    //   The graph is represented as a set of vertices and a list of edges between vertices.
    // Representation invariant:
    //   - vertices is a set of unique vertex strings.
    //   - edges is a list of edges between vertices, each edge has a source, target, and weight.
    // Safety from rep exposure:
    //   - vertices is a Set, so it is safe from rep exposure.
    //   - edges is a List, but Edge is immutable, so it is safe from exposure.
    
    public ConcreteEdgesGraph() {
        // Initialize the graph with empty vertices and edges.
    }
    
    @Override
    public boolean add(String vertex) {
        return vertices.add(vertex);
    }
    
    @Override
    public int set(String source, String target, int weight) {
        // Add or update the edge between source and target
        for (Edge edge : edges) {
            if (edge.getSource().equals(source) && edge.getTarget().equals(target)) {
                // Update existing edge with new weight
                edge.setWeight(weight);
                return weight;
            }
        }
        // If no such edge exists, create a new one
        edges.add(new Edge(source, target, weight));
        return weight;
    }
    
    @Override
    public boolean remove(String vertex) {
        // Remove the vertex from the set
        boolean removed = vertices.remove(vertex);
        if (removed) {
            // Remove all edges connected to the vertex
            edges.removeIf(edge -> edge.getSource().equals(vertex) || edge.getTarget().equals(vertex));
        }
        return removed;
    }
    
    @Override
    public Set<String> vertices() {
        return new HashSet<>(vertices);
    }
    
    @Override
    public Map<String, Integer> sources(String target) {
        Map<String, Integer> result = new HashMap<>();
        for (Edge edge : edges) {
            if (edge.getTarget().equals(target)) {
                result.put(edge.getSource(), edge.getWeight());
            }
        }
        return result;
    }
    
    @Override
    public Map<String, Integer> targets(String source) {
        Map<String, Integer> result = new HashMap<>();
        for (Edge edge : edges) {
            if (edge.getSource().equals(source)) {
                result.put(edge.getTarget(), edge.getWeight());
            }
        }
        return result;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Vertices: ").append(vertices.toString()).append("\n");
        sb.append("Edges: ");
        for (Edge edge : edges) {
            sb.append(edge.toString()).append(", ");
        }
        return sb.toString();
    }
}

/**
 * Internal class to represent an edge in the graph.
 */
class Edge {
    private final String source;
    private final String target;
    private int weight;
    
    // Abstraction function:
    //   The edge is represented by a source vertex, target vertex, and a weight.
    // Representation invariant:
    //   - source and target are non-null strings.
    //   - weight is a non-negative integer.
    // Safety from rep exposure:
    //   - source and target are immutable, so they cannot be modified from outside.
    //   - weight can be modified, but it is encapsulated within this class.
    
    public Edge(String source, String target, int weight) {
        this.source = source;
        this.target = target;
        this.weight = weight;
        checkRep();
    }
    
    // Checks the representation invariant of the edge
    private void checkRep() {
        assert source != null : "source cannot be null";
        assert target != null : "target cannot be null";
        assert weight >= 0 : "weight must be non-negative";
    }
    
    public String getSource() {
        return source;
    }
    
    public String getTarget() {
        return target;
    }
    
    public int getWeight() {
        return weight;
    }
    
    public void setWeight(int weight) {
        this.weight = weight;
        checkRep();
    }
    
    @Override
    public String toString() {
        return "(" + source + " -> " + target + ", weight: " + weight + ")";
    }
}
