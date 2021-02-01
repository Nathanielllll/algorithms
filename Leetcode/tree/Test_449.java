package tree;

public class Test_449 {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);

        Codec codec = new Codec();
        String data = codec.serialize(root);
        codec.deserialize(data);
    }

    public static class Codec {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            if(root == null) return "";

            String result = root.val + ",";
            result += serialize(root.left);
            result += serialize(root.right);
            return result;
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            String[] strings = data.split(",");
            return generateTree(strings, 0, strings.length - 1);
        }

        private TreeNode generateTree(String[] strings, int start, int end){
            if (start > end) {
                return null;
            }

            TreeNode root = new TreeNode(Integer.parseInt(strings[start]));
            int index = start;
            while(index <= end && Integer.parseInt(strings[index]) <= Integer.parseInt(strings[start])){
                index++;
            }
            int leftEndIndex = index - 1;
            int rightStartIndex = index;

            root.left = generateTree(strings, start + 1, leftEndIndex);
            root.right = generateTree(strings, rightStartIndex, end);
            return root;
        }
    }
}
