package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

public class ConcreteVerticesGraph implements Graph<String> {
    
    private final List<Vertex> vertices = new ArrayList<>();
    
    // Abstraction function:
    //   The graph is represented by a list of vertices, each of which has a list of outgoing edges.
    // Representation invariant:
    //   - vertices is a list of unique Vertex objects.
    // Safety from rep exposure:
    //   - vertices is a list, and we do not expose this list directly. We return copies of it when needed.
    
    public ConcreteVerticesGraph() {
        // Initialize the graph with empty vertices.
    }
    
    private void checkRep() {
        // Make sure the vertices list does not contain any nulls or duplicates
        assert vertices != null : "vertices list cannot be null";
        Set<String> uniqueLabels = new HashSet<>();
        for (Vertex v : vertices) {
            assert v != null : "vertex cannot be null";
            uniqueLabels.add(v.label);
        }
        assert uniqueLabels.size() == vertices.size() : "duplicate vertices found";
    }
    
    @Override
    public boolean add(String vertex) {
        checkRep();
        for (Vertex v : vertices) {
            if (v.label.equals(vertex)) {
                return false;  // Vertex already exists
            }
        }
        vertices.add(new Vertex(vertex));
        return true;
    }
    
    @Override
    public int set(String source, String target, int weight) {
        checkRep();
        Vertex src = findVertex(source);
        Vertex tgt = findVertex(target);
        
        if (src == null || tgt == null) {
            throw new IllegalArgumentException("Source or target vertex not found");
        }
        
        // Add or update the edge
        for (Edge edge : src.edges) {
            if (edge.target.equals(target)) {
                int prevWeight = edge.weight;
                edge.weight = weight;
                return prevWeight;
            }
        }
        
        // If no edge found, create a new one
        src.edges.add(new Edge(source, target, weight));
        return 0;
    }
    
    @Override
    public boolean remove(String vertex) {
        checkRep();
        Vertex v = findVertex(vertex);
        if (v == null) {
            return false;
        }
        
        vertices.remove(v);
        // Remove all edges connected to this vertex
        for (Vertex vertexObj : vertices) {
            vertexObj.edges.removeIf(edge -> edge.target.equals(vertex) || edge.source.equals(vertex));
        }
        return true;
    }
    
    @Override
    public Set<String> vertices() {
        checkRep();
        Set<String> vertexLabels = new HashSet<>();
        for (Vertex v : vertices) {
            vertexLabels.add(v.label);
        }
        return vertexLabels;
    }
    
    @Override
    public Map<String, Integer> sources(String target) {
        checkRep();
        Map<String, Integer> result = new HashMap<>();
        for (Vertex v : vertices) {
            for (Edge edge : v.edges) {
                if (edge.target.equals(target)) {
                    result.put(edge.source, edge.weight);
                }
            }
        }
        return result;
    }
    
    @Override
    public Map<String, Integer> targets(String source) {
        checkRep();
        Map<String, Integer> result = new HashMap<>();
        for (Vertex v : vertices) {
            for (Edge edge : v.edges) {
                if (edge.source.equals(source)) {
                    result.put(edge.target, edge.weight);
                }
            }
        }
        return result;
    }
    
    @Override
    public String toString() {
        checkRep();
        StringBuilder sb = new StringBuilder();
        sb.append("Vertices: ").append(vertices.toString()).append("\n");
        for (Vertex v : vertices) {
            sb.append(v.toString()).append("\n");
        }
        return sb.toString();
    }
    
    private Vertex findVertex(String label) {
        for (Vertex v : vertices) {
            if (v.label.equals(label)) {
                return v;
            }
        }
        return null;
    }
    
    /**
     * Internal class to represent a vertex in the graph.
     */
    private static class Vertex {
        private final String label;
        private final List<Edge> edges;
        
        public Vertex(String label) {
            this.label = label;
            this.edges = new ArrayList<>();
        }
        
        @Override
        public String toString() {
            return label + " -> " + edges;
        }
    }
    
    /**
     * Internal class to represent an edge in the graph.
     */
    private static class Edge {
        private final String source;
        private final String target;
        private int weight;
        
        public Edge(String source, String target, int weight) {
            this.source = source;
            this.target = target;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "(" + source + " -> " + target + ", weight: " + weight + ")";
        }
    }
}
