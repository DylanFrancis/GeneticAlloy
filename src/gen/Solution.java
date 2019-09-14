package gen;

import utils.Codes;

public class Solution implements Codes, Comparable<Solution> {
    private int[] vectors;
    private double result;

    public Solution() {
        vectors = new int[4];
    }

    public Solution(int[] v) {
        vectors = v;
    }

    public void setVectors(int[] v){
        vectors = v;
    }

    public int[] getVectors(){
        return vectors;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public void setResult(double[] r) {
        result = 0;
        for (double v : r) result += v;
    }

    @Override
    public int compareTo(Solution solution) {
        return Double.compare(solution.getResult(), result);
    }
}
