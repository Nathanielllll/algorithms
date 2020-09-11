package stack.monotonousStack;
/*
编写一个 StockSpanner 类，它收集某些股票的每日报价，并返回该股票当日价格的跨度。

今天股票价格的跨度被定义为股票价格小于或等于今天价格的最大连续日数（从今天开始往回数，包括今天）。

例如，如果未来7天股票的价格是 [100, 80, 60, 70, 60, 75, 85]，那么股票跨度将是 [1, 1, 1, 2, 1, 4, 6]。

 */

import java.util.Stack;

/*
即求左边离它最近的大于它的数字！
 */
class StockSpanner {

    private Stack<Integer> prices;
    private Stack<Integer> indexes;

    public StockSpanner() {
        prices = new Stack<>();
        indexes = new Stack<>();
    }

    public int next(int price) {
        int ans = 1;
        while(!prices.isEmpty() && prices.peek() <= price) {
            prices.pop();
            ans += indexes.pop();
        }
        indexes.push(ans);
        prices.push(price);
        return ans;
    }

}

public class Test_901 {
    public static void main(String[] args) {
        int[] nums = {100, 80, 60, 70, 60, 75, 85};
        StockSpanner test = new StockSpanner();
        for(int num : nums){
            System.out.println(test.next(num));
        }

    }
}
