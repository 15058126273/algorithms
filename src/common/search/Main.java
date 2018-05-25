package search;

/**
 * desc
 *
 * @author yjy
 * 2018-05-25 23:18
 */
public class Main {

    public static void main(String[] args) {
        // just do...
        SequentialSearchST<String, String> ss = new SequentialSearchST<>();
        ss.put("1", "val1");
        ss.put("2", "val2");
        ss.put("3", "val3");
        ss.put("3", "val33");
        ss.put("4", "val4");
        ss.put("5", "val5");
        print(ss);

        System.out.println(ss.get("3"));

        ss.delete("2");
        print(ss);

        System.out.println(ss.size());

        System.out.println(ss.contains("4") + ":" + ss.contains("7"));
    }


    private static void print(SearchST searchST) {
        System.out.println("print start................");
        for (Object k : searchST.keys()) {
            System.out.println(k.toString() + ":" + searchST.get(k).toString());
        }
        System.out.println("print end................");
    }
}
