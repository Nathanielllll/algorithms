package xiecheng.October_13;

import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Test_2 {
//    public static void main(String[] args) {
//        Scanner cin = new Scanner(System.in);
//        int[][] nums = new int[Integer.MAX_VALUE][2];
//        while (cin.hasNextInt()) {
//            int incomeOfA = cin.nextInt();
//            int incomeOfB = cin.nextInt();
//
//
//            //Start coding -- Input Data
//        }
//
//
////        10 30
////        100 200
////        150 50
////        60 20
//        int[][] nums = {{10,30},{100,200},{150,50},{60,20}};
//
//        boolean[] visited = new boolean[nums.length];
//        System.out.println(process(nums, visited));
//
//    }


    static class Pair{
        int value;
        int index;

        public Pair(int value, int index) {
            this.value = value;
            this.index = index;
        }
    }

    private static int process(int[][] nums, boolean[] visited){
        int N = nums.length;
        int M = N / 2;

        PriorityQueue<Pair> priorityQueue_A = new PriorityQueue<>(new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                return o2.value - o1.value;
            }
        });
        PriorityQueue<Pair> priorityQueue_B = new PriorityQueue<>(new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                return o2.value - o1.value;
            }
        });

        for (int i = 0; i < nums.length; i++) {
            priorityQueue_A.add(new Pair(nums[i][0], i));
            priorityQueue_B.add(new Pair(nums[i][1], i));
        }

        int count_A = 0;
        int count_B = 0;

        int sum = 0;
        while (count_A < M && count_B < M) {
            int value = 0;
            if (priorityQueue_A.peek().value > priorityQueue_B.peek().value ) {
                if(!visited[priorityQueue_A.peek().index]){
                    visited[priorityQueue_A.peek().index] = true;
                    value = priorityQueue_A.poll().value;
                    count_A++;
                }else {
                    priorityQueue_A.poll();
                }

            }
            if(priorityQueue_A.peek().value <= priorityQueue_B.peek().value){
                if(!visited[priorityQueue_B.peek().index]){
                    visited[priorityQueue_B.peek().index] = true;
                    value = priorityQueue_B.poll().value;
                    count_B++;
                }else {
                    priorityQueue_B.poll();
                }
            }
            sum += value;
        }

        if (count_A == M) {
            while(count_B < M){
                sum += priorityQueue_B.poll().value;
                count_B++;
            }
        }

        if(count_B == M){
            while(count_A < M){
                sum += priorityQueue_A.poll().value;
                count_A++;
            }
        }

        return sum;
    }
}
