import java.util.*;

public class Test {
    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        list.add("A");
        list.add("B");
        list.add("B");
        list.add("C");
        list.add("D");

        System.out.println(list.indexOf("F"));
        System.out.println(list.toString());
    }
}
