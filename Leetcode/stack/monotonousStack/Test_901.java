package stack.monotonousStack;
/*
编写一个 StockSpanner 类，它收集某些股票的每日报价，并返回该股票当日价格的跨度。

今天股票价格的跨度被定义为股票价格小于或等于今天价格的最大连续日数（从今天开始往回数，包括今天）。

例如，如果未来7天股票的价格是 [100, 80, 60, 70, 60, 75, 85]，那么股票跨度将是 [1, 1, 1, 2, 1, 4, 6]。

 */

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/*
求左边【最远】的比【它】小的数的位置 ==> 求左边离它【最近】的大于它的数字的位置 + 1
 */
//class StockSpanner {
//
//    private Stack<Integer> prices;
//    private Stack<Integer> indexes;
//
//    public StockSpanner() {
//        prices = new Stack<>();
//        indexes = new Stack<>();
//    }
//
//    public int next(int price) {
//        int ans = 1;
//        while(!prices.isEmpty() && prices.peek() <= price) {
//            prices.pop();
//            ans += indexes.pop();
//        }
//        indexes.push(ans);
//        prices.push(price);
//        return ans;
//    }
//
//}

/**
 * 相比上面的解法，更好理解但是速度更慢。
 */
class StockSpanner {
    private final List<Integer> priceList;
    private final Stack<Integer> indexStack;

    public StockSpanner() {
        priceList = new LinkedList<>();
        indexStack = new Stack<>();
    }

    public int next(int price) {
        // 注意是<=
        while(!indexStack.isEmpty() && priceList.get(indexStack.peek()) <= price){
            indexStack.pop();
        }

        int curIndex = priceList.size();
        int count = curIndex - (indexStack.isEmpty() ? -1 : indexStack.peek());

        indexStack.push(curIndex);
        priceList.add(price);
        return count;
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
