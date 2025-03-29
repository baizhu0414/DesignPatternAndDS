package DS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class WeightObj implements Comparable<WeightObj> {
    int weight;
    String name;

    public WeightObj(int weight, String name) {
        this.weight = weight;
        this.name = name;
    }

    @Override
    public int compareTo(WeightObj o2) {
        String s2 = o2.name;
        for (int i = 0; i < Math.min(name.length(), s2.length()); i++) {
            if (name.charAt(i) == s2.charAt(i)) {
                continue;
            }
            return name.charAt(i) - s2.charAt(i);
        }
        return name.length() - s2.length();
    }
}

public class SortListAndArray {
    public static void main(String[] args) {
        sortListStrExamples();
        sortListObjExamples();
        sortArrayExamples();
    }

    static void sortListObjExamples() {
        List<String> names = Arrays.asList("Charlie", "Alice", "Ali");
        List<WeightObj> list = new ArrayList<>();
        for (int i = 0; i < names.size(); i++) {
            list.add(new WeightObj(0, names.get(i)));
        }
        Collections.sort(list);
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i).name + " ");
        }
        System.out.println();
    }

    static void sortListStrExamples() {
        List<String> names = Arrays.asList("Charlie", "Alice", "Ali");
        names.sort(Comparator.naturalOrder());
        printStrList(names);
        names.sort(Comparator.reverseOrder());
        printStrList(names);
    }

    static void printStrList(List<String> names) {
        for (int i = 0; i < names.size(); i++) {
            System.out.print(names.get(i) + " ");
        }
        System.out.println();
    }

    static void sortArrayExamples() {
        int[] numbers = { 5, 2, 8, 10, 1, 4 };
        /** 默认数据类型<数组 int[]类型> */
        Arrays.sort(numbers); // 升序
        System.out.println(Arrays.toString(numbers));

        for (int i = 0; i < (numbers.length >> 1); i++) { // 降序：通过将升序数组逆序
            int x = numbers[i];
            numbers[i] = numbers[numbers.length - 1 - i];
            numbers[numbers.length - 1 - i] = x;
        }
        System.out.println(Arrays.toString(numbers));

        int[] weights = { 5, 2, 8, 10, 1, 4 };
        WeightObj[] objs = new WeightObj[weights.length];
        for (int i = 0; i < weights.length; i++) {
            objs[i] = new WeightObj(weights[i], "");
        }
        /** 对象数据类型<数组 Integer[]、Object[]> */
        Arrays.sort(objs, (a, b) -> a.weight - b.weight); // 升序
        printObjsArrays(objs);

        Arrays.sort(objs, (a, b) -> b.weight - a.weight); // 降序
        printObjsArrays(objs);
    }

    static void printObjsArrays(WeightObj[] objs) {
        for (int i = 0; i < objs.length; i++) {
            System.out.print(objs[i].weight + " ");
        }
        System.out.println();
    }
}
