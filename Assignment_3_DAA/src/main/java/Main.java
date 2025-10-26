import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;

public class Main {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
        String inputPath = args.length > 0 ? args[0] : "src/main/resources/assign_3_input.json";
        String outputPath = args.length > 1 ? args[1] : "results/assign_3_output.json";

        Gson gson = new Gson();
        Map in = gson.fromJson(new FileReader(inputPath), Map.class);
        List<Map> graphs = (List<Map>) in.get("graphs");
        List<Map> results = new ArrayList<>();

        for (Map gmap : graphs) {
            int id = ((Number) gmap.get("id")).intValue();
            List<String> nodes = (List<String>) gmap.get("nodes");
            List<Map> edgesIn = (List<Map>) gmap.get("edges");
            List<Edge> edges = new ArrayList<>();
            for (Map e : edgesIn) {
                String from = (String) e.get("from");
                String to = (String) e.get("to");
                int w = ((Number) e.get("weight")).intValue();
                edges.add(new Edge(from, to, w));
            }

            Graph g = new Graph(nodes, edges);
            Result kr = KruskalMST.run(g);
            Result pr = PrimMST.run(g);

            Map<String, Object> res = new LinkedHashMap<>();
            res.put("graph_id", id);
            Map<String, Object> stats = new LinkedHashMap<>();
            stats.put("vertices", g.vertexCount());
            stats.put("edges", g.edgeCount());
            res.put("input_stats", stats);

            Map<String, Object> primMap = new LinkedHashMap<>();
            primMap.put("mst_edges", krToList(pr.mstEdges));
            primMap.put("total_cost", pr.totalCost);
            primMap.put("operations_count", pr.operations);
            primMap.put("execution_time_ms", pr.timeMs);
            res.put("prim", primMap);

            Map<String, Object> krMap = new LinkedHashMap<>();
            krMap.put("mst_edges", krToList(kr.mstEdges));
            krMap.put("total_cost", kr.totalCost);
            krMap.put("operations_count", kr.operations);
            krMap.put("execution_time_ms", kr.timeMs);
            res.put("kruskal", krMap);

            results.add(res);
        }

        Map<String, Object> out = new LinkedHashMap<>();
        out.put("results", results);

        Gson pretty = new GsonBuilder().setPrettyPrinting().create();
        java.io.File resultsDir = new java.io.File("results");
        if (!resultsDir.exists()) resultsDir.mkdirs();

        try (FileWriter fw = new FileWriter(outputPath)) {
            pretty.toJson(out, fw);
        }
        System.out.println("Wrote results to: " + outputPath);
    }

    private static List<Map<String, Object>> krToList(List<Edge> edges) {
        List<Map<String, Object>> list = new ArrayList<>();
        for (Edge e : edges) {
            Map<String, Object> m = new LinkedHashMap<>();
            m.put("from", e.from);
            m.put("to", e.to);
            m.put("weight", e.weight);
            list.add(m);
        }
        return list;
    }
}
