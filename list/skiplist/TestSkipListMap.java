package list.skiplist;


public class TestSkipListMap {


    public static void main(String[] args) {
        SkipListMap <String, String> map = new SkipListMap<>();
		// map.printAll();
		// System.out.println("======================");
		// map.put("A", "10");
		// map.printAll();
		// System.out.println("======================");
		// map.remove("A");
		// map.printAll();
		System.out.println("======================");
		map.put("D", "D");
		map.put("F", "F");
		map.put("E", "E");
		map.put("B", "B");
		map.put("A", "A");
		map.put("C", "C");
		map.printAll();
		System.out.println("======================");
		System.out.println(map.containsKey("B"));
		System.out.println(map.containsKey("Z"));
		// System.out.println(map.firstKey());
		// System.out.println(map.lastKey());
		// System.out.println(map.floorKey("D"));
		// System.out.println(map.ceillingKey("D"));
		System.out.println("======================");
        map.remove("B");
		map.printAll();
		System.out.println("======================");
		// System.out.println(map.floorKey("D"));
		// System.out.println(map.ceillingKey("D"));
    }
}
