package hash;

import java.util.HashMap;

public class DesignRandomPool {
    /**
     * 設計一結構，該結構 insert, delete 都是 O(1),
     * 且可以 retrun 一個 random value
     */

    public static class RandomPool <K>{
        /**
         * 準備兩個 map, 第一個 map 紀錄 key -> 第 i 個 insert 的 i
         * 第二個 map 紀錄 第 i 個 insert i -> key
         * 準備一個 size, initial = 0
         *
         * insert: if key 不存在，分別 put 後 size ++
         * delete:
         * 拿最後一個 key 去填補要刪掉的 key。
         * 這句話等價於: 最後一個 key index 改成要刪掉的 index
         * 最後再把 key remove 掉, size --
         */

        private HashMap <K, Integer> keyIndexMap;
        private HashMap <Integer, K> indexKeyMap;
        private int size;

        public RandomPool() {
            this.keyIndexMap = new HashMap<K, Integer>();
            this.indexKeyMap = new HashMap<Integer, K>();
            size = 0;
        }

        public void insert (K key) {
            if (!keyIndexMap.containsKey(key)) {
                keyIndexMap.put(key, size);
                indexKeyMap.put(size, key);
                size ++;
            }
        }

        public void delete (K key) {
            if (keyIndexMap.containsKey(key)) {
                int deleteIndex = keyIndexMap.get(key);
                K lastKey = indexKeyMap.get(size);

                // 最後一個 key 去紀錄要刪掉的 index
                keyIndexMap.put(lastKey, deleteIndex);

                // 要刪掉的 index 紀錄最後一個 key
                indexKeyMap.put(deleteIndex, lastKey);

                // 刪掉 key
                keyIndexMap.remove(key);

                // 刪掉 index
                indexKeyMap.remove(size);
                size --;
            }
        }

        public K getRandom() {
            if (size == 0) {
                return null;
            }

            int randomIndex = (int) (Math.random() * size);
            return indexKeyMap.get(randomIndex);
        }
    }
}
