import java.util.List;

public class Result {
    public final List<Edge> mstEdges;
    public final int totalCost;
    public final long operations;
    public final double timeMs;

    public Result(List<Edge> mstEdges, int totalCost, long operations, double timeMs) {
        this.mstEdges = mstEdges;
        this.totalCost = totalCost;
        this.operations = operations;
        this.timeMs = timeMs;
    }
}
