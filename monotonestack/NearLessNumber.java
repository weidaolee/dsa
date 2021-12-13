package monotonestack;
import java.util.Stack;

public class NearLessNumber {
    /**
     * Assignment:
     * Given an int arr [], return a int res [arr.length][2],
     * 0 表第 i 個左邊最靠近的最小的 index
     * 1 表第 i 個右邊最靠近的最小的 index
     * ========================================================
     * Prepare:
     * 準備一 stack, stack = Stack <Stack<Integer>>,
     * 如果要找最小，則 stack 要維持嚴格升序，如果找最大，維持降序
     * stack 中的 integer 是 index, inner stack 是用來存放相同大小的不同 indexes
     * ==========================================================
     * Implementation:
     * 來到 arr 的第 i 個數:
     * 1. if stack is not empty and if arr[i] 的加入無法使 stack 維持升序,
     *    則從 stack 中 pop 出一個 inner stack - popIndexes, 開始生成這些 indexes 的資訊:
     *
     *    對所有的 index,
     *    其左最近且比它小的，要麼是 -1 (已經到 stack 底部), 要麼是現在 stack 的 top of top
     *    其右最近且比它小的， i
     *
     * 2. 確定做完第一步後, push 動作：
     *    if: arr[top] = arr[i]
     *        把 i push 到 inner stack 中
     *    else:
     *        new 一個新的 inner stack, push i into inner stack
     *        then push inner stack into stack
     * 3. 做完第二步，如果 stack is not empty, imply:
     *    all rest indexes have no neareast right index,
     *
     *    對所有的 index,
     *    其左最近且比它小的，要麼為 -1 (抵達 stack 底部) 要麼為 stack.top.top
     *    其右最近且比它小的，不存在 -1
     */
    public static int [][] getNearLess(int [] arr) {
        int [][] res = new int [arr.length][2];
        Stack <Stack<Integer>> stack = new Stack<>();

        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[i] < arr[stack.peek().peek()]) {
                Stack<Integer> popIndexes = stack.pop();

                int leftLessIndex = stack.isEmpty() ? -1 : stack.peek().peek();

                while (!popIndexes.isEmpty()) {
                    int index = popIndexes.pop();
                    res[index][0] = leftLessIndex;
                    res[index][1] = i;
                }
            }

            if (!stack.isEmpty() && arr[stack.peek().peek()] == arr[i]) {
                stack.peek().push(i);
            } else {
                Stack<Integer> innerStack = new Stack<>();
                innerStack.push(i);
                stack.push(innerStack);
            }
        }

        while (!stack.isEmpty()) {
            Stack<Integer> popIndexes = stack.pop();
            int leftLessIndex = stack.isEmpty() ? -1 : stack.peek().peek();

            while (!popIndexes.isEmpty()) {
                int index = popIndexes.pop();
                res[index][0] = leftLessIndex;
                res[index][1] = -1;
            }
        }
        return res;
    }
    public static void main(String[] args) {
        int arr[] = {0,483,62,9,94,772,611,90,94,7,2,0,20171,35,48,405,82,62,0,30,1,40,5,86,3};
        int res[][] = getNearLess(arr);
        for (int i = 0; i < res.length; i++) {
            int l = res[i][0] == -1 ? Integer.MAX_VALUE : arr[res[i][0]];
            int r = res[i][1] == -1 ? Integer.MAX_VALUE : arr[res[i][1]];
            System.out.printf("arr[%d] = %d, left less is arr[%d] = %d, right is arr[%d] = %d%n",
                              i, arr[i], res[i][0], l, res[i][1], r);
        }
    }
}
