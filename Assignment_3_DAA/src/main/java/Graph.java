import java.util.*;

public class Graph {
    public final Set<String> nodes;
    public final List<Edge> edges;
    public final Map<String, List<Edge>> adj;

    public Graph(Collection<String> nodes, Collection<Edge> edges) {
        this.nodes = new TreeSet<>(nodes);
        this.edges = new ArrayList<>(edges);
        this.adj = new HashMap<>();

        for (String n : this.nodes)
            adj.put(n, new ArrayList<>());

        for (Edge e : this.edges) {
            adj.computeIfAbsent(e.from, k -> new ArrayList<>());
            adj.computeIfAbsent(e.to, k -> new ArrayList<>());

            adj.get(e.from).add(e);
            adj.get(e.to).add(new Edge(e.to, e.from, e.weight)); // undirected
        }
    }

    public int vertexCount() {
        return nodes.size();
    }

    public int edgeCount() {
        return edges.size();
    }
}
