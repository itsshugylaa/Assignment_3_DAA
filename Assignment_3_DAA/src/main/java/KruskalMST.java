import java.util.*;

public class KruskalMST {

    public static Result run(Graph g) {
        long start = System.nanoTime();

        List<Edge> edges = new ArrayList<>(g.edges);

        edges.sort(Comparator.comparingInt(e -> e.weight));

        DSU dsu = new DSU(g.nodes);
        List<Edge> mst = new ArrayList<>();
        long ops = 0;
        int cost = 0;

        for (Edge e : edges) {
            ops++;
            if (dsu.union(e.from, e.to)) {
                mst.add(e);
                cost += e.weight;
                if (mst.size() == g.vertexCount() - 1) break;
            }
        }

        long end = System.nanoTime();
        double ms = (end - start) / 1_000_000.0;

        return new Result(mst, cost, ops + dsu.finds + dsu.unions, ms);
    }
}
