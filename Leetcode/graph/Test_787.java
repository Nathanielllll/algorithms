package graph;

import java.util.Arrays;

public class Test_787 {

  // Bellman Ford 有线节点数
  public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
    int[] prices = new int[n];
    Arrays.fill(prices, Integer.MAX_VALUE);
    prices[src] = 0;

    for (int i = 0; i <= k; i++) {
      int[] tmpPrices = Arrays.copyOf(prices, n);

      for (int[] flight : flights) {
        int s = flight[0];
        int d = flight[1];
        int p = flight[2];

        if (prices[s] == Integer.MAX_VALUE) {
          continue;
        }

        if (prices[s] + p < tmpPrices[d]) {
          tmpPrices[d] = prices[s] + p;
        }
      }

      prices = tmpPrices;
    }

    return prices[dst] == Integer.MAX_VALUE ? -1 : prices[dst];
  }

}
