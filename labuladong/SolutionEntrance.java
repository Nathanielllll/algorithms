import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author luzhi
 * @date 2021/8/11
 */
public class SolutionEntrance {

//    public static void main(String[] args) {
//        int[] list1 = new int[]{1,5,4,6,7,3};
//        int[] list2 = new int[]{7,1,5,4,3,7,0};
//        int[] list3 = new int[]{4,2,7,3,1,6};
//
//        int size = 4;
//
//        int length1 = list1.length;
//        int length2 = list2.length;
//        int length3 = list3.length;
//
//        int index1 = 0;
//        int index2 = 0;
//        int index3 = 0;
//
//
//
//
//
//    }

    private static List<Integer> list1;
    private static List<Integer> list2;
    private static List<Integer> list3;
    private static int size = 19;


    public static void main(String[] args) {
        list1 = new LinkedList<>();
        list2 = new LinkedList<>();
        list3 = new LinkedList<>();
        for (int i = 0; i <= 19; i++) {
            list1.add(i);
        }
        for (int i = 0; i <= 18; i++) {
            list2.add(i);
        }
        for (int i = 0; i <= 20; i++) {
            list3.add(i);
        }
        List<List<Integer>> list = new ArrayList<>();
        list.add(list1);
        list.add(list2);
        list.add(list3);


        int index = 0; // list数组的索引
//        int pos = 0; //
        int calculateCount = 0; // 累计的size


        int length = 4;
        int fromIndex = 0;
        int endIndex = fromIndex + length;

        while (true) {
            List<Integer> list0 = list.get(index);

            calculateCount = list0.size();

            if (endIndex > calculateCount) {
                int realFromIndex = calculateCount;
            }else {

            }




            fromIndex = endIndex;
            endIndex = fromIndex + length;



        }






//        int pos = 0;
//
//        int[] indexes = new int[3];
//        for (int i = 0; i < list.size(); i++) {
//            List<Integer> list0 = list.get(i);
//            int index = indexes[i];
//
//            while (index < list0.size()) {
//                if (pos == size) {
//                    pos = 0;
//                    System.out.println();
//                }
//                System.out.print(list0.get(index) + " ");
//                ++index;
//                ++pos;
//            }
//        }

    }

}
