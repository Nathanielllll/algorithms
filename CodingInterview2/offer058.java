public class offer058 {

    public static class TreeNode {
        int start;
        int end;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    class MyCalendar {

        TreeNode root;

        public MyCalendar() {

        }

        public boolean book(int start, int end) {
            if (root == null) {
                root = new TreeNode(start, end);
                return true;
            }

            TreeNode node = root;
            while (node != null) {
                if (node.start >= end) {
                    if (node.left == null) {
                        node.left = new TreeNode(start, end);
                        return true;
                    } else {
                        node = node.left;
                    }
                } else if (node.end <= start) {
                    if (node.right == null) {
                        node.right = new TreeNode(start, end);
                        return true;
                    } else {
                        node = node.right;
                    }
                } else {
                    return false;
                }
            }
            return false;
        }
    }
}
