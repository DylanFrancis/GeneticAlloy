package product;

import utils.Codes;

public class Alloy implements Codes {
    protected double price;
    protected double platinum;
    protected double iron;
    protected double copper;
    protected double electricity;

    public Alloy(double price, double platinum, double iron, double copper, double electricity) {
        this.price = price;
        this.platinum = platinum;
        this.iron = iron;
        this.copper = copper;
        this.electricity = electricity;
    }

    /**
     * Calculates the total resource quantities to make a number of alloys
     * @param alloy amounts
     * @return an array with the quantity of each resource required
     */
    public double[] calc(int alloy) {
        double[] quant = new double[4];

        quant[PLATINUM] = platinum     * alloy;
        quant[IRON] = iron         * alloy;
        quant[COPPER] = copper       * alloy;
        quant[ELECTRICITY] = electricity  * alloy;

        return quant;
    }
}
