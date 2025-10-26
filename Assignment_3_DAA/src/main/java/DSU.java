import java.util.*;

public class DSU {
    private final Map<String, String> parent;
    private final Map<String, Integer> rank;
    public long unions = 0;
    public long finds = 0;

    public DSU(Collection<String> nodes) {
        parent = new HashMap<>();
        rank = new HashMap<>();
        for (String n : nodes) {
            parent.put(n, n);
            rank.put(n, 0);
        }
    }

    public String find(String x) {
        finds++;
        String p = parent.get(x);
        if (p == null) {
            parent.put(x, x);
            rank.put(x, 0);
            return x;
        }
        if (!p.equals(x)) {
            String r = find(p);
            parent.put(x, r);
            return r;
        }
        return p;
    }

    public boolean union(String a, String b) {
        unions++;
        String ra = find(a);
        String rb = find(b);
        if (ra.equals(rb)) return false;
        int raRank = rank.getOrDefault(ra, 0);
        int rbRank = rank.getOrDefault(rb, 0);
        if (raRank < rbRank) parent.put(ra, rb);
        else if (raRank > rbRank) parent.put(rb, ra);
        else {
            parent.put(rb, ra);
            rank.put(ra, raRank + 1);
        }
        return true;
    }
}
