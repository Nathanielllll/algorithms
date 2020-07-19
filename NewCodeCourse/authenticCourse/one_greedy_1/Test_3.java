package authenticCourse.one_greedy_1;
/*
有字符串string1，string2
string2最少【删除】几个字符，可以变成string1的某个子字符串

第一种方法：生成s2的所有子序列，等同于由s2删除字符得到的结果。所有子序列要2^M的复杂度
长度从大到小排列，使用kmp算法O(N)复杂度，看看其是不是s1的子字符串，是就返回。
一共是O(N * 2^M)复杂度
一般用于s2的长度M比较小的时候

第二种方法：
 */
public class Test_3 {

}
