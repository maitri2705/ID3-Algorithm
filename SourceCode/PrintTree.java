public class PrintTree {

    int noOfTotalNodes = 0, noOfLeafNodes = 0;
    
    public int getTotalNumberOfLeafNodes(){
        return noOfLeafNodes;
    }
    
    public int getTotalNumberOfNodes(){
        return noOfTotalNodes;
    }

    public void printDecisionTree(TreeNode treeNode) {
        printChild(treeNode.leftChild, "");
        printChild(treeNode.rightChild, "");
    }

    private void printChild(TreeNode treeNode, String s) {
        int attributeValue = 0;
        
        if (treeNode == null) {
            return;
        }
        if (treeNode == treeNode.parentNode.leftChild) {
            attributeValue = 0;
        } else {
            attributeValue = 1;
        }
    
        System.out.println();

        System.out.print(s + treeNode.nameOfNode + " = " + attributeValue);

        noOfTotalNodes++; /*... COunting total number of nodes...*/

        /*============Check for leaf node==========*/     
        if (!(treeNode.leftChild == null && treeNode.rightChild == null)) {
            printChild(treeNode.leftChild, s + "| ");
            printChild(treeNode.rightChild, s + "| ");
        } else {
            if (treeNode.count1 > treeNode.count0) {
                System.out.print(" : 1");
            } else {
                System.out.print(" : 0");
            }
            noOfLeafNodes++; /*.... Counting number of leaf nodes...*/
        }
    }
}
