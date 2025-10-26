import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MSTPerformanceTest {

    @Test
    public void testPerformanceMedium() {
        Graph g = DatasetGenerator.randomConnectedGraph(20, 0.4);
        Result kr = KruskalMST.run(g);
        Result pr = PrimMST.run(g);
        assertTrue(kr.timeMs >= 0);
        assertTrue(pr.timeMs >= 0);
        assertTrue(kr.operations >= 0);
        assertTrue(pr.operations >= 0);
        // costs should match
        assertEquals(kr.totalCost, pr.totalCost);
    }

    @Test
    public void testPerformanceLarge() {
        Graph g = DatasetGenerator.randomConnectedGraph(50, 0.25);
        Result kr = KruskalMST.run(g);
        Result pr = PrimMST.run(g);
        assertNotNull(kr.mstEdges);
        assertNotNull(pr.mstEdges);
        assertEquals(kr.totalCost, pr.totalCost);
    }
}
