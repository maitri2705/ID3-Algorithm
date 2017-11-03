import java.io.FileReader;
import java.util.Scanner;
public class Accuracy {

    private int notCorrectlyClassified, correctlyClassified;
    private int trainingInstances=0,validationInstances=0,testingInstances=0;
    private int trainingAttributes=0,validationAttributes=0,testingAttributes=0;

    public void trainingDataTesting(TreeNode treeNode, String filePath) {
        notCorrectlyClassified = 0;
        correctlyClassified = 0;
        try {
            Scanner sc = new Scanner(new FileReader(filePath));
            String str = sc.nextLine();
            String[] inputData = new String[treeNode.attributeSet.length];
            while (sc.hasNextLine()) {
                String str1 = sc.nextLine();
                inputData = str1.split(",");
                /*......calculate accuracy of every line.........*/
                findAccuracy(treeNode, inputData);
                trainingInstances++;
            }
            trainingAttributes=treeNode.attributeSet.length;
            float percentage = (float) correctlyClassified / (notCorrectlyClassified + correctlyClassified) * 100;
            
            System.out.println("\nNumber of TrainingInstances = "+(trainingInstances));
            System.out.println("Number of TrainingAtrributes = "+(trainingAttributes-1));
            System.out.println("Accuracy of the model on training dataset = " + percentage + " %");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void validationDataTesting(TreeNode treeNode, String filePath) {
        notCorrectlyClassified = 0;
        correctlyClassified = 0;
        try {
            Scanner sc = new Scanner(new FileReader(filePath));
            String str = sc.nextLine();
            while (sc.hasNextLine()) {
                String str1 = sc.nextLine();
                String[] inputData = str1.split(",");
                /*......calculate accuracy of every line.........*/
                findAccuracy(treeNode, inputData);
                validationInstances++;
            }
            validationAttributes=treeNode.attributeSet.length;
            float percentage = (float) correctlyClassified / (notCorrectlyClassified + correctlyClassified) * 100;
            System.out.println("\nNumber of ValidationInstances = "+validationInstances);
            System.out.println("Number of ValidationAtrributes = "+(validationAttributes-1));
            System.out.println("Accuracy of the model on validation dataset = " + percentage + " %");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void testDataTesting(TreeNode treeNode, String filePath) {
        notCorrectlyClassified = 0;
        correctlyClassified = 0;
        try {
            Scanner sc = new Scanner(new FileReader(filePath));
            String str = sc.nextLine();
            while (sc.hasNextLine()) {
                String str1 = sc.nextLine();
                String[] inputData = str1.split(",");
                /*......calculate accuracy of every line.........*/
                findAccuracy(treeNode, inputData);
                testingInstances++;
            }
            testingAttributes++;
            float percentage = (float) correctlyClassified / (notCorrectlyClassified + correctlyClassified) * 100;
            
            System.out.println("\nNumber of TestingInstances = "+testingInstances);
            System.out.println("Number of TestingAtrributes = "+(testingAttributes-1));
            System.out.println("Accuracy of the model on testing dataset = " + percentage + " %");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void findAccuracy(TreeNode treeNode, String[] inputData) {
        if (!(treeNode.leftChild == null && treeNode.rightChild == null)) {
            if (Integer.parseInt(inputData[treeNode.leftChild.index]) == 0) {
                findAccuracy(treeNode.leftChild, inputData);
            } 
            else {
                findAccuracy(treeNode.rightChild, inputData);
            }

        } 
        else {
            if (treeNode.classLabel == Integer.parseInt(inputData[inputData.length - 1])) {
                correctlyClassified++;
            } else {
                notCorrectlyClassified++;
            }
        }
    }
}
