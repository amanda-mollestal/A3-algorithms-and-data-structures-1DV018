package uppgift;

import java.util.ArrayList;
import java.util.List;

public class UndirectedGraph extends Graph {

    @Override
    public void addEdge(int from, int to, double weight) {
        adjacencyList.putIfAbsent(from, new ArrayList<>());
        adjacencyList.putIfAbsent(to, new ArrayList<>());
        adjacencyList.get(from).add(new Edge(from, to, weight)); 
        adjacencyList.get(to).add(new Edge(to, from, weight));
    }

    @Override
    public void removeEdge(int from, int to) {
        List<Edge> fromEdges = adjacencyList.get(from);
        List<Edge> toEdges = adjacencyList.get(to);
        if (fromEdges != null) {
            fromEdges.removeIf(edge -> edge.to == to);
        }
        if (toEdges != null) {
            toEdges.removeIf(edge -> edge.to == from);
        }
    }

    @Override
    public int numberOfEdges() {
        int count = 0;
        for (List<Edge> edges : adjacencyList.values()) {
            count += edges.size();
        }
        return count / 2; // Each edge is counted twice, so divide by 2
    }
    
    public void addEdge(int from, int to) {
        addEdge(from, to, 1.0);
    }
}
