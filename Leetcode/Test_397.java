import java.util.HashMap;
import java.util.Map;

public class Test_397 {
    Map<Long, Integer> cache = new HashMap<>();

    public int integerReplacement(int n) {
        return dfs(n * 1L);
    }

    private int dfs(long n) {
        if (n == 1) return 0;
        if (cache.containsKey(n)) {
            return cache.get(n);
        }
        int ans;
        if (n % 2 == 0) {
            ans = dfs(n / 2) + 1;
        } else {
            ans = Math.min(dfs(n + 1), dfs(n - 1)) + 1;
        }
        cache.put(n, ans);
        return ans;
    }
}
