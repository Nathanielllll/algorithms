package pdd.October_23;

import java.util.*;

public class Test_2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String string = scanner.nextLine();
        int[] res = process(string);
        System.out.print(res[0] + " ");
        System.out.print(res[1]);
    }

    private static int[] process(String string){
        HashMap<String, List<Integer>> hashMap = new HashMap<>();

        HashSet<Character> hashSet = new HashSet<>();
        for (int i = 0; i < string.length(); i++) {
            hashSet.add(string.charAt(i));
        }
        List<Character> list2 = new ArrayList<>();
        for(char c : hashSet){
            list2.add(c);
        }

        for (int i = 0; i < string.length(); i++) {
            for (int j = i + 1; j <= string.length(); j++) {
                String string1 = string.substring(i, j);
                if (contains(string1, list2)) {
                    List<Integer> list;
                    if (hashMap.containsKey(string1)) {
                        list = hashMap.get(string1);
                    }else {
                        list = new ArrayList<>();
                    }
                    list.add(i);
                    hashMap.put(string1, list);
                }
            }
        }

        List<String> list1 = new ArrayList<>(hashMap.keySet());
        Collections.sort(list1);
        List<Integer> res1 = hashMap.get(list1.get(0));
        Collections.sort(res1);
        int start = res1.get(res1.size() - 1);
        int length = list1.get(0).length();
        return new int[]{start, length};
    }

    private static boolean contains(String str1, List<Character> list2){
        List<Character> list1 = new ArrayList<>();
        for (int i = 0; i < str1.length(); i++) {
            list1.add(str1.charAt(i));
        }
        return list1.containsAll(list2) && list2.containsAll(list1);
    }

    private static int[] slidingWindows(String string) {
        HashMap<String, List<Integer>> hashMap = new HashMap<>();

        HashSet<Character> hashSet = new HashSet<>();
        for (int i = 0; i < string.length(); i++) {
            hashSet.add(string.charAt(i));
        }

        HashMap<Character, Integer> needs = new HashMap<>();
        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);
            needs.put(c, 1);
        }

        HashMap<Character, Integer> window = new HashMap<>();
        int left = 0;
        int right = 0;
        while (right < string.length()) {
            Character char_right = string.charAt(right);
            window.put(char_right, window.getOrDefault(char_right, 0) + 1);
            right++;

            while (window.get(char_right) > 1) {
                Character char_left = string.charAt(left);
                window.put(char_left, window.getOrDefault(char_left, 0) - 1);
                left++;
            }

            String str = string.substring(left, right);
            List<Integer> list;
            if (hashMap.containsKey(str)) {
                list = hashMap.get(str);
            }else {
                list = new ArrayList<>();
            }
            list.add(left);
            hashMap.put(str, list);
        }
        List<String> list1 = new ArrayList<>(hashMap.keySet());
        Collections.sort(list1);
        List<Integer> res1 = hashMap.get(list1.get(0));
        Collections.sort(res1);
        int start = res1.get(0);
        int length = list1.get(0).length();
        return new int[]{start, length};
    }
}
