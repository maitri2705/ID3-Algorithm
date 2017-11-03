public class PruneTree {

    public void pruneTree(TreeNode treeNode, int noOfNodesToPrune, int totalNodes) {
         int[] randomArray = new int[noOfNodesToPrune];
        for (int i = 0; i < noOfNodesToPrune; i++) {
            randomArray[i] = (int) (Math.random() * (totalNodes - 1)) + 1; 
        }
        cuttingNodes(treeNode, randomArray);
    }

    private void cuttingNodes(TreeNode treeNode, int[] randomArray) {
        if (treeNode.leftChild == null && treeNode.rightChild == null) {
            return;
        } 
        else {
            for (int i = 0; i < randomArray.length; i++) {
                if (treeNode.number == randomArray[i]) {
                    treeNode.leftChild = null;
                    treeNode.rightChild = null;
                }
            }
            if (treeNode.leftChild != null) {
                cuttingNodes(treeNode.leftChild, randomArray);
            }
            if (treeNode.rightChild != null) {
                cuttingNodes(treeNode.rightChild, randomArray);
            }
        }
    }
}
