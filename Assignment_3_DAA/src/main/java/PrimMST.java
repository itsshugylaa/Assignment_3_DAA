import java.util.*;

public class PrimMST {

    public static Result run(Graph g) {
        long start = System.nanoTime();

        if (g.vertexCount() == 0) {
            return new Result(Collections.emptyList(), 0, 0, 0.0);
        }

        String startNode = g.nodes.iterator().next();
        Set<String> visited = new HashSet<>();
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.weight));

        visited.add(startNode);
        List<Edge> adjList = g.adj.getOrDefault(startNode, Collections.emptyList());
        pq.addAll(adjList);
        long pushes = pq.size();
        long comparisons = 0;
        List<Edge> mst = new ArrayList<>();

        while (!pq.isEmpty() && mst.size() < g.vertexCount() - 1) {
            Edge e = pq.poll();
            comparisons++;
            if (visited.contains(e.to)) continue;
            visited.add(e.to);
            mst.add(new Edge(e.from, e.to, e.weight));
            for (Edge ne : g.adj.getOrDefault(e.to, Collections.emptyList())) {
                if (!visited.contains(ne.to)) {
                    pq.add(ne);
                    pushes++;
                }
            }
        }

        long ops = comparisons + pushes;
        long end = System.nanoTime();
        double timeMs = (end - start) / 1_000_000.0;
        int cost = mst.stream().mapToInt(x -> x.weight).sum();
        return new Result(mst, cost, ops, timeMs);
    }
}
