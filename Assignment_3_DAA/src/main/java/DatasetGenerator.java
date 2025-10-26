import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.util.*;

public class DatasetGenerator {

    private static final Random rnd = new Random(123456);

    public static Graph randomConnectedGraph(int n, double density) {
        List<String> nodes = new ArrayList<>();
        for (int i = 0; i < n; i++) nodes.add("V" + i);
        List<Edge> edges = new ArrayList<>();

        for (int i = 1; i < n; i++) {
            int j = rnd.nextInt(i);
            int w = 1 + rnd.nextInt(50);
            edges.add(new Edge(nodes.get(i), nodes.get(j), w));
        }

        int maxEdges = n * (n - 1) / 2;
        int target = Math.max(edges.size(), (int) Math.round(density * maxEdges));
        Set<String> used = new HashSet<>();
        for (Edge e : edges) {
            used.add(e.from + "-" + e.to);
            used.add(e.to + "-" + e.from);
        }

        while (edges.size() < target) {
            int a = rnd.nextInt(n);
            int b = rnd.nextInt(n);
            if (a == b) continue;
            String key = nodes.get(a) + "-" + nodes.get(b);
            if (used.contains(key)) continue;
            int w = 1 + rnd.nextInt(100);
            edges.add(new Edge(nodes.get(a), nodes.get(b), w));
            used.add(key);
            used.add(nodes.get(b) + "-" + nodes.get(a));
        }

        return new Graph(nodes, edges);
    }

    public static void writeDataset(String path, List<Graph> graphs) throws Exception {
        List<Map<String, Object>> gList = new ArrayList<>();
        int id = 1;
        for (Graph g : graphs) {
            Map<String, Object> gm = new LinkedHashMap<>();
            gm.put("id", id++);
            gm.put("nodes", g.nodes);

            List<Map<String, Object>> edList = new ArrayList<>();
            for (Edge e : g.edges) {
                Map<String, Object> em = new LinkedHashMap<>();
                em.put("from", e.from);
                em.put("to", e.to);
                em.put("weight", e.weight);
                edList.add(em);
            }
            gm.put("edges", edList);
            gList.add(gm);
        }

        Map<String, Object> root = new LinkedHashMap<>();
        root.put("graphs", gList);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter fw = new FileWriter(path)) {
            gson.toJson(root, fw);
        }
        System.out.println("Saved dataset to: " + path);
    }

    public static void main(String[] args) throws Exception {
        List<Graph> graphs = new ArrayList<>();
        graphs.add(randomConnectedGraph(4, 0.6));   // small
        graphs.add(randomConnectedGraph(10, 0.4));  // medium
        graphs.add(randomConnectedGraph(20, 0.3));  // large
        writeDataset("datasets/assign_3_input.json", graphs);
    }
}
