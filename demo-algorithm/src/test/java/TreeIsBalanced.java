/**
 * @description:
 * @author: haofeiL
 * @createDate: 2018/9/17 16:28
 * @version: v1.0
 */
public class TreeIsBalanced {
    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);

        node1.left = node2;
        node1.right = node3;

        System.out.println(isBalanced(node1));
    }

    public static boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }

        int left = getHeight(root.left);
        int right = getHeight(root.right);
        if (Math.abs(left - right) <= 1 && isBalanced(root.left) && isBalanced(root.right))
            return true;

        return false;
    }

    public static int getHeight(TreeNode node) {
        if (node == null)
            return 0;
        int left = getHeight(node.left);
        int right = getHeight(node.right);

        return Math.max(left, right) + 1;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}