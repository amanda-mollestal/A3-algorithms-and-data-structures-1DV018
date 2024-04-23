package uppgift;

import java.util.*;

public class DFS {
    private Map<Integer, Integer> edgeTo;
    private Set<Integer> marked;
    private final int source;

    public DFS(Graph G, int s) {
        edgeTo = new HashMap<>();
        marked = new HashSet<>();
        this.source = s;
        dfs(G, s);
    }

    private void dfs(Graph G, int v) {
        marked.add(v);
        for (Graph.Edge e : G.adjacencyList.getOrDefault(v, Collections.emptyList())) {
            int w = e.to;
            if (!marked.contains(w)) {
                edgeTo.put(w, v);
                dfs(G, w);
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
