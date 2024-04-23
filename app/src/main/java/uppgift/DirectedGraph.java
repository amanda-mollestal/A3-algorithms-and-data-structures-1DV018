package uppgift;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class DirectedGraph extends Graph {

    private Map<Integer, Integer> inDegreeMap;

    public DirectedGraph() {
        super();
        inDegreeMap = new HashMap<>();
    }

    @Override
    public void addEdge(int from, int to, double weight) {
        adjacencyList.putIfAbsent(from, new ArrayList<>());
        adjacencyList.putIfAbsent(to, new ArrayList<>());
    
        adjacencyList.get(from).add(new Edge(from, to, weight)); 
    
        inDegreeMap.put(to, inDegreeMap.getOrDefault(to, 0) + 1);

        inDegreeMap.putIfAbsent(from, 0);
    }

    @Override
    public void removeEdge(int from, int to) {
        List<Edge> edges = adjacencyList.get(from);
        if (edges != null) {
            boolean removed = edges.removeIf(edge -> edge.to == to);
            if (removed) {
                inDegreeMap.put(to, inDegreeMap.getOrDefault(to, 0) - 1);
            }
        }
    }

    public void addEdge(int from, int to) {
        addEdge(from, to, 1.0);
    }


    public int outDegree(int node) {
        return adjacencyList.getOrDefault(node, Collections.emptyList()).size();
    }

    public int inDegree(int node) {
        return inDegreeMap.getOrDefault(node, 0);
    }

    public String degreeToString(int node) {
        return "In-Degree: " + inDegree(node) + ", Out-Degree: " + outDegree(node);
    }
}
