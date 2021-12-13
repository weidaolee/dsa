package hash;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HeigestFrequencyNumber {
    /**
     * 給定 40 億個數, 要求計算出 出現頻率最高的數
     */

    public int hashTable (List <Integer> nums) {
        /**
         * 如果全都不一樣, map 會爆掉
         *
         */
        Map <Integer, Integer> map = new HashMap<>();
        for (int i : nums) {
            if (!map.containsKey(i)) {
                map.put(i, 1);
            } else {
                map.put(i, map.get(i) + 1);
            }
        }

        return Collections.max(map.entrySet(), Map.Entry.comparingByValue()).getKey();
    }
    public int hashHash (List <Integer> nums) {
        /**
         * 準備 0 ~ 99 號 文件
         * 讀取 i in nums, i 取 hash mod 上 100: fd = hash (i) % 100
         * 將 i 寫入 fd 號 文件
         * 注意，相同的數，一定被寫入相同文件，因為 hash (n0) = hash (n1), for all n0 = n1.
         * ========================================================
         * 讀取 0 - 99 號文件：
         * 統計出現最高的
         * space 花費是 32 G / 100
         */


        return 0;
    }

    public int hash (int in) {
        return 0;
    }
}
