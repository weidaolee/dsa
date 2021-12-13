package unionfindset;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class UnionFindSet <V>{
    /**
     * 1. 要求用戶在一開始必須給出所有的資料
     * 2. 對每筆資料 v 進行初始化: 將 v 套一層 element 類，並把 parent 指向自己
     * 3. 查尋兩比資料是否屬於相同 set: 看是否 the first ancestor 是否相同
     * 4. union: set 數量小的 parent 指向 數量大的 the first ancestor
     * 5. 查詢完後，執行搜索路徑壓縮: 將沿路的 parent 全部指向 the first ancestor
     * ===================================================================
     * Implementation:
     * 1. 準備一個 V -> Element 的 map elementMap 服務 client 的查詢
     * 2. 準備一個 Element -> Element (parent) 的 map parentMap
     * 3. 準備一個 Element (earliest ancestor) -> int 的 map rankMap, 紀錄 set 的 rank
     * 4. 注意，只有 the first ancestor 可以在rankMap 中有 entry
     */

    public Map <V, Element <V>> elementMap;
    public Map <Element <V>, Element <V>> parentMap;
    public Map <Element <V>, Integer> rankMap;

    public UnionFindSet (Collection <V> collection) {
        elementMap = new HashMap <>();
        parentMap = new HashMap <>();
        rankMap = new HashMap <>();

        for (V v : collection) {
            Element <V> e = new Element <V> (v);
            elementMap.put(v, e);
            parentMap.put(e, e);
            rankMap.put(e, 1);
        }
    }

    Stack <Element <V>> stack = new Stack<>();
    public Element <V> findEarliestAncentor (Element <V> element) {
        if (element == null || !stack.isEmpty()) {
            return null;
        }

        while (element != parentMap.get(element)) {
            stack.push(element);
            element = parentMap.get(element);
        }

        while (!stack.isEmpty()) {
            parentMap.put(stack.pop(), element);
        }

        return element;
    }

    public boolean areInSameSet (V v1, V v2) {
        if (elementMap.containsKey(v1) && elementMap.containsKey(v2)) {
            Element <V> e1 = elementMap.get(v1);
            Element <V> e2 = elementMap.get(v2);

            return findEarliestAncentor(e1) == findEarliestAncentor(e2);
        }

        return false;
    }

    public void union (V v1, V v2) {
        if (!elementMap.containsKey(v1) || !elementMap.containsKey(v2)) {
            return;
        }
        Element <V> e1 = findEarliestAncentor(elementMap.get(v1));
        Element <V> e2 = findEarliestAncentor(elementMap.get(v2));

        if (e1 == e2) {
            return;
            // same set
        }

        int r1 = rankMap.get(e1);
        int r2 = rankMap.get(e2);

        Element <V> big = r1 >= r2 ? e1 : e2;
        Element <V> small = e1 == big ? e2 : e1;

        parentMap.put(small, big);
        rankMap.put(big, r1 + r2);
        rankMap.remove(small);
    }

    public static class Element <V> {
        public V value;

		public Element(V value) {
			this.value = value;
		}
    }
}
