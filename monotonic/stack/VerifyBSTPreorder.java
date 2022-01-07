package tree.traverse;

import java.util.Stack;

public class VerifyBSTPreorder {
   /**
    * Link:
    * https://leetcode-cn.com/problems/verify-preorder-sequence-in-binary-search-tree/BST in-order 會滿足 L < D < R，整個序列遞增
    *
    * Main idea:
    * BST pre-order 會滿足 D > L < R，所以該序列：
    * 1. 在左樹上，會遞減
    * 2. 遇到一個非遞減的 node ，說明來到 D，但接下來的 node 必須比 D 大
    *
    * 將 pre-order 依序把遞減 node push 進 stack, 如果遇到非遞減的 node,
    * 從 stack 中 pop 一個作為最小值下屆，如果後續的值比最小值下屆還小，則代表
    * 該序列非 BST pre-order
    */

    public boolean verifyPreorder (int [] preorder) {
        Stack <Integer> stack = new Stack<>();
        int min = Integer.MIN_VALUE;
        for (int i : preorder) {
            if (i < min) {return false;}
            while (!stack.isEmpty() && i > stack.peek()) {
                min = stack.pop();
            }
            stack.push(i);
        }
        return true;
    }
    private boolean verifyPreorderArrayStack (int [] preorder) {
        int [] stack = new int [preorder.length];
        int min = Integer.MIN_VALUE;
        int top = -1;
        for (int i : preorder) {
            if (i < min) {return false;}
            while (top > -1 && i > stack[top]) {
                min = stack[top --];
            }
            stack[++ top] = i;
        }
        return true;
    }
}
