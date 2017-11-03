public class Calculation {

   
    
    public double log2(double prob) {
        double ans = Math.log10(prob) / Math.log10(2);
        return ans;
    }

    public Counter getCountOf0and1(TreeNode treeNode, int index) {
        Counter counter = new Counter();
        for (int i = 0; i < treeNode.inputData.size(); i++) {
            if (treeNode.inputData.get(i)[index].equals("0")) {
                counter.count0++;
            } else {
                counter.count1++;
            }
        }
        return counter;
    }

    /*=================  START MY OWN FUnction for Entropy ==================*/
    public double getMyEntropy(int m, int n) {
        if(m == 0 || n == 0){ return 0.0;}
        double mPlusn = (double) m + n;
        double entropy = -(m / mPlusn) * log2(m / mPlusn) - (n / mPlusn) * log2(n / mPlusn);
        return entropy;
    }
    /*=================  END MY OWN FUnction for Entropy ==================*/

    public int splitAttribute(TreeNode treeNode) {
        int maxInfoGainAtrributeIndex = calculateInfoGain(treeNode);
        return maxInfoGainAtrributeIndex;
    }

    public int calculateInfoGain(TreeNode treeNode) {
         /*....... All attribute excluding class label goes to infoGain array.........*/
        double[] infoGain = new double[treeNode.attributeSet.length - 1];
         /*...... Calculate class info gain.......*/
        Counter parentCounter = getCountOf0and1(treeNode, treeNode.attributeSet.length - 1);
        double parentEntropy = getMyEntropy(parentCounter.count0, parentCounter.count1);
        for (int i = 0; i < infoGain.length; i++) {
            Counter counter = getCountOf0and1(treeNode, i);
            int totalNoOfOand1 = counter.count0 + counter.count1;
            double probOf0 = (double) counter.count0 / totalNoOfOand1;
            double probOf1 = 1 - probOf0;

            counter = getConditionalCount(treeNode, i, "0");
            double st0 = (double) (counter.count0+counter.count1) / totalNoOfOand1;
            double entropyOf0 = getMyEntropy(counter.count0,counter.count1);
            counter = getConditionalCount(treeNode, i, "1");
            double st1 = (double)(counter.count0 + counter.count1)/ totalNoOfOand1;
            double entropyOf1 = getMyEntropy(counter.count0,counter.count1);
            double childENtropy = st0*entropyOf0 + st1* entropyOf1;
            infoGain[i] = parentEntropy - childENtropy;
        }
        double maxInfoGain = infoGain[0];
        int maxInfoGainAttributeIndex = 0;
        for (int i = 0; i < infoGain.length; i++) {
            if (infoGain[i] > maxInfoGain) {
                maxInfoGain = infoGain[i];
                maxInfoGainAttributeIndex = i;
            }
        }
        return maxInfoGainAttributeIndex;
    }

    public Counter getConditionalCount(TreeNode treeNode, int index, String str) {
        Counter counter = new Counter();
        for (int i = 0; i < treeNode.inputData.size(); i++) {

            String attributeValue = treeNode.inputData.get(i)[index];
            String classLabel = treeNode.inputData.get(i)[treeNode.attributeSet.length - 1];
            if (attributeValue.equals("0")&& str.equals("0")) {
                if (classLabel.equals("0")) {
                    counter.count0++;
                } else {
                    counter.count1++;
                }
            }
            else if(attributeValue.equals("1")&& str.equals("1")){
                if (classLabel.equals("1")) {
                    counter.count1++;
                } else {
                    counter.count0++;
                }
            }
        }
        return counter;
    }
}
