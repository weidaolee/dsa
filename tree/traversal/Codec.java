package tree.traversal;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import tree.TreeNode;

public class Codec {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return "N,";
        }
        StringBuilder data = new StringBuilder();
        data = serialize(root, data);
        return data.toString();
    }

    public StringBuilder serialize(TreeNode root, StringBuilder data) {
        if (root == null) {
            data.append("N,");
            return data;
        }

        data.append(root.val + ",");
        data = serialize(root.left, data);
        data = serialize(root.right, data);
        return data;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        List <String> dataList = new LinkedList<>(Arrays.asList(data.split(",")));
        return deserialize(dataList);
    }

    public TreeNode deserialize(List <String> dataList) {
        if (dataList.get(0).equals("N")) {
            dataList.remove(0);
            return null;
        }

        String val = dataList.get(0);
        dataList.remove(0);
        TreeNode root = new TreeNode(Integer.valueOf(val));
        root.left = deserialize(dataList);
        root.right = deserialize(dataList);

        return root;
    }
}
