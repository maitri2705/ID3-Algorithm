import java.util.ArrayList;

public class ChildNode {

    public TreeNode leftChild;
    public TreeNode rightChild;

    ChildNode() {
        leftChild = new TreeNode();
        rightChild = new TreeNode();
    }

    public void createChild(TreeNode treeNode, int splitIndex, int depth, int number) {
        leftChild.depthOfTree = depth;
        rightChild.depthOfTree = depth;

        String[] tempAttributes = treeNode.attributeSet;
        for (int i = splitIndex; i < tempAttributes.length - 1; i++) {
            tempAttributes[i] = tempAttributes[i + 1];
        }

        leftChild.attributeSet = new String[tempAttributes.length - 1];
        rightChild.attributeSet = new String[tempAttributes.length - 1];

        for (int i = 0; i < leftChild.attributeSet.length; i++) {
            leftChild.attributeSet[i] = tempAttributes[i];
            rightChild.attributeSet[i] = tempAttributes[i];
        }        
        for (int i = 0; i < treeNode.inputData.size(); i++) {
            if (treeNode.inputData.get(i)[splitIndex].equals("0")) {
                String[] str1 = treeNode.inputData.get(i);
                
                for (int j = splitIndex; j < str1.length - 1; j++) {
                    str1[j] = str1[j + 1];
                }
                leftChild.inputData.add(str1);
            } 
            else {
                String[] str1 = treeNode.inputData.get(i);
                for (int j = splitIndex; j < str1.length - 1; j++) {
                    str1[j] = str1[j + 1];
                }
                rightChild.inputData.add(str1);
            }
        }
        leftChild.parentNode = treeNode;
        rightChild.parentNode = treeNode;

        leftChild.number = number;
        rightChild.number = number + 1;

        for (int i = 0; i < leftChild.inputData.size(); i++) {
            if (leftChild.inputData.get(i)[leftChild.attributeSet.length - 1].equals("0")) {
                leftChild.count0++;
            } else {
                leftChild.count1++;
            }
        }
      
        if(leftChild.count0 > leftChild.count1){
            leftChild.classLabel = 0;
        } else{
            leftChild.classLabel = 1;
        }
        
        for (int i = 0; i < rightChild.inputData.size(); i++) {
            if (rightChild.inputData.get(i)[rightChild.attributeSet.length - 1].equals("0")) {
                rightChild.count0++;
            } else {
                rightChild.count1++;
            }
        }
        if(rightChild.count0 > rightChild.count1){
            rightChild.classLabel = 0;
        } else{
            rightChild.classLabel = 1;
        }
        depth++;
    }
}
