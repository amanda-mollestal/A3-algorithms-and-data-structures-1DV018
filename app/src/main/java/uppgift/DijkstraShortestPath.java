package uppgift;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

public class DijkstraShortestPath {
  private Graph.Edge[] shortestPathTo;
  private double[] shortestDistanceTo;
  private PriorityQueue<QueueNode> priorityQueue;

  private static class QueueNode {
    private final int nodeIndex;
    private final double distance;

    QueueNode(int nodeIndex, double distance) {
      this.nodeIndex = nodeIndex;
      this.distance = distance;
    }

    public int getNodeIndex() {
      return nodeIndex;
    }

    public double getDistance() {
      return distance;
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj)
        return true;
      if (obj == null || getClass() != obj.getClass())
        return false;
      QueueNode that = (QueueNode) obj;
      return nodeIndex == that.nodeIndex;
    }

    @Override
    public int hashCode() {
      return Objects.hash(nodeIndex);
    }
  }

  public DijkstraShortestPath(Graph graph, int sourceNode) {
    shortestPathTo = new Graph.Edge[graph.numberOfNodes()];
    shortestDistanceTo = new double[graph.numberOfNodes()];
    Arrays.fill(shortestDistanceTo, Double.POSITIVE_INFINITY);
    shortestDistanceTo[sourceNode] = 0.0;

    priorityQueue = new PriorityQueue<>(Comparator.comparingDouble(QueueNode::getDistance));
    priorityQueue.add(new QueueNode(sourceNode, 0.0));

    while (!priorityQueue.isEmpty()) {
      QueueNode currentNode = priorityQueue.poll();
      int currentNodeIndex = currentNode.getNodeIndex();
      for (Graph.Edge edge : graph.edge(currentNodeIndex)) {
        relaxEdge(edge);
      }
    }
  }

  private void relaxEdge(Graph.Edge edge) {
    int fromNode = edge.from, toNode = edge.to;
    if (shortestDistanceTo[toNode] > shortestDistanceTo[fromNode] + edge.weight) {
      shortestDistanceTo[toNode] = shortestDistanceTo[fromNode] + edge.weight;
      shortestPathTo[toNode] = edge;

      priorityQueue.remove(new QueueNode(toNode, shortestDistanceTo[toNode]));
      priorityQueue.add(new QueueNode(toNode, shortestDistanceTo[toNode]));
    }
  }

  public double distanceTo(int destinationNode) {
    return shortestDistanceTo[destinationNode];
  }

  public boolean hasPathTo(int destinationNode) {
    return shortestDistanceTo[destinationNode] < Double.POSITIVE_INFINITY;
  }

  public Iterable<Graph.Edge> pathTo(int destinationNode) {
    if (!hasPathTo(destinationNode))
      return null;
    List<Graph.Edge> path = new ArrayList<>();
    for (Graph.Edge edge = shortestPathTo[destinationNode]; edge != null; edge = shortestPathTo[edge.from]) {
      path.add(edge);
    }
    Collections.reverse(path);
    return path;
  }

} 
