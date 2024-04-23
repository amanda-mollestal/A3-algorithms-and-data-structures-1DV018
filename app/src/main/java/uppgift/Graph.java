package uppgift;

import java.util.*;

public abstract class Graph {
  protected Map<Integer, List<Edge>> adjacencyList;

  protected static class Edge {
    int from; 
    int to;
    double weight;

    public Edge(int from, int to, double weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Edge from: " + from + " to: " + to + ", Weight: " + weight;
    }
}

  public Graph() {
    adjacencyList = new HashMap<>();
  }

  public abstract void addEdge(int from, int to, double weight);

  public abstract void addEdge(int from, int to);

  public abstract void removeEdge(int from, int to);

  public int numberOfNodes() {
    return adjacencyList.size();
  }

  public int numberOfEdges() {
    int count = 0;
    for (int node : adjacencyList.keySet()) {
      count += adjacencyList.get(node).size();
    }
    return count;
  }

  public Iterable<Integer> nodes() {
    return adjacencyList.keySet();
  }

  public Iterable<Edge> edge(int node) {
    return adjacencyList.getOrDefault(node, Collections.emptyList());
  }

  public Iterable<Edge> edges() {
    List<Edge> edges = new ArrayList<>();
    for (int node : adjacencyList.keySet()) {
      edges.addAll(adjacencyList.get(node));
    }
    return edges;
  }

  public int degree(int node) {
    return adjacencyList.getOrDefault(node, Collections.emptyList()).size();
  }

  public Iterable<Integer> neighbors(int node) {
    List<Integer> neighbors = new ArrayList<>();
    for (Edge edge : adjacencyList.getOrDefault(node, Collections.emptyList())) {
      neighbors.add(edge.to);
    }
    return neighbors;
  }

  public boolean hasEdge(int from, int to) {
    if (!adjacencyList.containsKey(from)) {
      return false; // 'from' node does not exist
    }

    for (Edge edge : adjacencyList.get(from)) {
      if (edge.to == to) {
        return true; // found
      }
    }

    return false; // does not exist
  }

  public DFS depthFirstPaths(int source) {
    return new DFS(this, source);
  }

  public BFS breadthFirstPaths(int source) {
    return new BFS(this, source);
  }

  public double getWeight(int from, int to) {
    if (!adjacencyList.containsKey(from)) {
      return Double.NaN; // the 'from' node does not exist
    }

    for (Edge edge : adjacencyList.get(from)) {
      if (edge.to == to) {
        return edge.weight; // the weight of the found edge
      }
    }

    return Double.NaN; // no edge exists from 'from' to 'to'
  }
}
