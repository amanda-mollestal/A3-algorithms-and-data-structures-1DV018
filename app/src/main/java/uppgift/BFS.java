package uppgift;

import java.util.*;

public class BFS {
    private Map<Integer, Integer> edgeTo;
    private Set<Integer> marked;
    private final int source;

    public BFS(Graph G, int s) {
        edgeTo = new HashMap<>();
        marked = new HashSet<>();
        this.source = s;
        bfs(G, s);
    }

    private void bfs(Graph G, int s) {
        Queue<Integer> q = new LinkedList<>();
        marked.add(s);
        q.add(s);
        while (!q.isEmpty()) {
            int v = q.remove();
            for (Graph.Edge e : G.adjacencyList.getOrDefault(v, Collections.emptyList())) {
                int w = e.to;
                if (!marked.contains(w)) {
                    edgeTo.put(w, v);
                    marked.add(w);
                    q.add(w);
                }
            }
        }
    }

    public boolean hasPathTo(int v) {
        return marked.contains(v);
    }

    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        List<Integer> path = new ArrayList<>();
        for (int x = v; x != source; x = edgeTo.get(x)) {
            path.add(x);
        }
        path.add(source);
        Collections.reverse(path);
        return path;
    }
}

