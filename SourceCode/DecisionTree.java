
public class DecisionTree {

    public int depthOfTree = 0, noOfLeafs = 0, no = 1;

    public void mainDecisionTree(TreeNode treeNode) {
        boolean flag = false;
        for (int i = 1; i < treeNode.inputData.size(); i++) {            
            if (!(treeNode.inputData.get(i)[treeNode.attributeSet.length - 1]).equals(treeNode.inputData.get(0)[treeNode.attributeSet.length - 1])) {
                flag = true;
                break;
            }
        }

        if (flag == true && treeNode.attributeSet.length > 1) {
            Calculation calculation = new Calculation();
            int splitIndex = calculation.splitAttribute(treeNode);
            String nameOfAttribute = treeNode.attributeSet[splitIndex];
            depthOfTree++;
            ChildNode child = new ChildNode();
            child.createChild(treeNode, splitIndex, depthOfTree, no);
            no = no + 2;
            TreeNode leftChild = new TreeNode();
            leftChild = child.leftChild;
            leftChild.nameOfNode = nameOfAttribute;
            leftChild.index = splitIndex;

            TreeNode rightChild = new TreeNode();
            rightChild = child.rightChild;
            rightChild.nameOfNode = nameOfAttribute;
            rightChild.index = splitIndex;

            treeNode.setLeftChild(leftChild);
            treeNode.setRightChild(rightChild);

            mainDecisionTree(leftChild);

            mainDecisionTree(rightChild);

        } else {
            noOfLeafs++;
        }
    }
}
