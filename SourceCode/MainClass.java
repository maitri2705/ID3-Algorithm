import java.io.FileReader;
import java.util.Scanner;

public class MainClass {

    static int featureCount = 0;
    static int instanceCount = 0;
    static String[][] inputData;

    public static void main(String[] args) {
        try {
            String trainingFile = args[0];
            String validationFile = args[1];
            String testingFile = args[2];
            double pruningFactor = Double.parseDouble(args[3]);
//            
            Scanner sc = new Scanner(new FileReader(trainingFile));

            String lineOfAttributes = sc.nextLine();
            /*......Attribute Set like x1,x2,x3,and so on.....*/
            String[] attributeSet = lineOfAttributes.split(",");
          /*........ Input Data like 1,0,0,1......... */
            String[] inputData = new String[attributeSet.length];

            TreeNode rootNode = new TreeNode();
            rootNode.attributeSet = attributeSet;
            rootNode.depthOfTree = 0;
            rootNode.nameOfNode = "root";
            rootNode.number = 0;

            String line;

            while (sc.hasNextLine()) {
                line = sc.nextLine();
                inputData = line.split(",");
                rootNode.inputData.add(inputData); /*....Add input Data to arrayList with string array....*/
            }

            DecisionTree decisionTree = new DecisionTree();
            decisionTree.mainDecisionTree(rootNode);

            
            System.out.println("\n*****************PRE PRUNED TREE **********************\n"
                    + "=====================================================");
            PrintTree p = new PrintTree();
            p.printDecisionTree(rootNode);

            System.out.print("\n\n*************Pre-pruned summary***********\n"
                    + "------------------------------------------");
            int totalNoOfNodes = p.getTotalNumberOfNodes();
            int totalNoOfLeafNodes = p.getTotalNumberOfLeafNodes();
            
            System.out.println("\nTotal number of nodes in the tree = " + totalNoOfNodes);
            System.out.println("Number of leaf nodes in the tree = " + totalNoOfLeafNodes);

            Accuracy accuracy = new Accuracy();
            accuracy.trainingDataTesting(rootNode, trainingFile);
            accuracy.validationDataTesting(rootNode, validationFile);
            accuracy.testDataTesting(rootNode, testingFile);

            int noOfNodesToPrune = (int) (pruningFactor * (decisionTree.no));
            PruneTree pruneTree = new PruneTree();
            pruneTree.pruneTree(rootNode, noOfNodesToPrune, totalNoOfNodes);

            System.out.print("\n\n*****************POST PRUNED TREE **********************\n"
                    + "=====================================================");

            PrintTree p1 = new PrintTree();
            p1.printDecisionTree(rootNode);

            System.out.println("\n\n*************Post-pruned summary***********\n"
                    + "------------------------------------------");
            int totalNoOfNodes1 = p1.getTotalNumberOfNodes();
            int totalNoOfLeafNodes1 = p1.getTotalNumberOfLeafNodes();
            System.out.println("\nTotal number of nodes in the tree = " + totalNoOfNodes1);
            System.out.println("Number of leaf nodes in the tree = " + totalNoOfLeafNodes1);

            Accuracy accuracy1 = new Accuracy();
            accuracy1.trainingDataTesting(rootNode, trainingFile);
            accuracy1.validationDataTesting(rootNode, validationFile);
            accuracy1.testDataTesting(rootNode, testingFile);

        } catch (Exception e) {
            System.out.println(e+"\n Please give input in the following order:PathOfTrainingDataset PathOfValidationDataset PathOfTestDataset pruningFactor");
        }
    }

}
