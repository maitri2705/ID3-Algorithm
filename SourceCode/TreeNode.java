import java.util.ArrayList;

public class TreeNode {
    public TreeNode parentNode;
    public TreeNode leftChild;
    public TreeNode rightChild;
    public int classLabel = 0;
    public int depthOfTree = 0;
    public int number = 0; // ********************     ????????????????  ****************//
    public String[] attributeSet;
    
    public int count0 = 0,count1 = 0;
    
    public String nameOfNode;
    public int index;
            
    public ArrayList<String[]> inputData = new ArrayList<String[]>();
    
    public void setLeftChild(TreeNode treeNode){
        this.leftChild = treeNode;
    }
    
    public void setRightChild(TreeNode treeNode){
        this.rightChild = treeNode;
    }
}
