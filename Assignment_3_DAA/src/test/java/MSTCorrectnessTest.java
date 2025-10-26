import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

public class MSTCorrectnessTest {

    @Test
    public void testSmallGraphEquality() {
        List<String> nodes = List.of("A","B","C","D");
        List<Edge> edges = List.of(
                new Edge("A","B",3),
                new Edge("A","C",1),
                new Edge("B","C",7),
                new Edge("B","D",5),
                new Edge("C","D",2)
        );
        Graph g = new Graph(nodes, edges);
        Result k = KruskalMST.run(g);
        Result p = PrimMST.run(g);
        assertEquals(k.totalCost, p.totalCost, "Costs must match");
        assertEquals(g.vertexCount() - 1, k.mstEdges.size(), "Kruskal should have V-1 edges");
        assertEquals(g.vertexCount() - 1, p.mstEdges.size(), "Prim should have V-1 edges");
    }

    @Test
    public void testDisconnectedGraph() {
        List<String> nodes = List.of("A","B","C","D");
        List<Edge> edges = List.of(
                new Edge("A","B",1),
                new Edge("C","D",2)
        );
        Graph g = new Graph(nodes, edges);
        Result k = KruskalMST.run(g);
        Result p = PrimMST.run(g);
        assertTrue(k.mstEdges.size() < g.vertexCount() - 1 || p.mstEdges.size() < g.vertexCount() - 1);
    }
}
