package heap.app;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class TopKFrequent {
    public int[] topKFrequent(int[] nums, int k) {
        int[] ans = new int[k];

        Map <Integer, Integer> times = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int n = nums[i];
            if (times.containsKey(n)) {
                times.put(n, times.get(n) + 1);
            } else {
                times.put(n, 1);
            }
        }

        PriorityQueue <Number> minHeap = new PriorityQueue<>(new NumberComparator());
        for (Map.Entry <Integer, Integer> e : times.entrySet()) {

            if (minHeap.size() < k) {
                minHeap.add(new Number(e.getKey(), e.getValue()));
                continue;
            }

            if(e.getValue() > minHeap.peek().times){
                minHeap.poll();
                minHeap.add(new Number(e.getKey(), e.getValue()));
                continue;
            }

        }

        for (int i = 0; i < k; i++) {
            ans[i] = minHeap.poll().val;
        }

        return ans;
    }

    private static class Number {
        int val;
        int times;
		public Number(int val, int times) {
			this.val = val;
			this.times = times;
		}

    }

    private static class NumberComparator implements Comparator<Number> {
		@Override
		public int compare(Number o1, Number o2) {
			return o1.times - o2.times;
		}
    }
}
