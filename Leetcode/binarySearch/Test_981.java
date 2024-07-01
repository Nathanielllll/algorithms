package binarySearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 设计一个基于时间的键值数据结构，该结构可以在不同时间戳存储对应同一个键的多个值，并针对特定时间戳检索键对应的值。
 * <p>
 * 实现 TimeMap 类：
 * <p>
 * TimeMap() 初始化数据结构对象 void set(String key, String value, int timestamp) 存储给定时间戳 timestamp 时的键 key
 * 和值 value。 String get(String key, int timestamp) 返回一个值，该值在之前调用了 set，其中 timestamp_prev <= timestamp
 * 。如果有多个这样的值，它将返回与最大  timestamp_prev 关联的值。如果没有值，则返回空字符串（""）。
 * <p>
 * 示例 1：
 * <p>
 * 输入： ["TimeMap", "set", "get", "get", "set", "get", "get"] [[], ["foo", "bar", 1], ["foo", 1],
 * ["foo", 3], ["foo", "bar2", 4], ["foo", 4], ["foo", 5]] 输出： [null, null, "bar", "bar", null,
 * "bar2", "bar2"]
 * <p>
 * 解释： TimeMap timeMap = new TimeMap(); timeMap.set("foo", "bar", 1);  // 存储键 "foo" 和值 "bar" ，时间戳
 * timestamp = 1 timeMap.get("foo", 1);         // 返回 "bar" timeMap.get("foo", 3);         // 返回
 * "bar", 因为在时间戳 3 和时间戳 2 处没有对应 "foo" 的值，所以唯一的值位于时间戳 1 处（即 "bar"） 。 timeMap.set("foo", "bar2", 4);
 * // 存储键 "foo" 和值 "bar2" ，时间戳 timestamp = 4 timeMap.get("foo", 4);         // 返回 "bar2"
 * timeMap.get("foo", 5);         // 返回 "bar2"x
 */
public class Test_981 {


  class TimeMap {

    private final Map<String, List<Pair<Integer, String>>> keyStore;

    public TimeMap() {
      keyStore = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
      List<Pair<Integer, String>> list = keyStore.getOrDefault(key, new ArrayList<>());
      list.add(new Pair<>(timestamp, value));
      keyStore.put(key, list);
    }

    public String get(String key, int timestamp) {
      List<Pair<Integer, String>> list = keyStore.getOrDefault(key, new ArrayList<>());
      int l = 0;
      int r = list.size() - 1;
      while (l <= r) {
        int c = l + (r - l) / 2;
        int curTs = list.get(c).key;
        if (curTs <= timestamp) {
          l = c + 1; // l的左侧，必然有时间戳<=timestamp
        } else {
          r = c - 1;
        }
      }
      if (r < 0) {
        return "";
      }
      return list.get(r).val;
    }
  }

  class Pair<K, V> {

    K key;
    V val;

    public Pair(K key, V val) {
      this.key = key;
      this.val = val;
    }
  }
}
