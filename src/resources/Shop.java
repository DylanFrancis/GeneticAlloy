package resources;

import product.Alloy;
import utils.Codes;

public class Shop implements Codes {

    private final double PLATINUM_COST  = 1200;
    private final double IRON_COST      = 300;
    private final double COPPER_COST    = 800;

    private Alloy adamantium;
    private Alloy unobtainium;
    private Alloy dilithium;
    private Alloy pandemonium;

    public Shop() {
        adamantium  = new Alloy(3000, 0.2, 0.7, 0.1, 25);
        unobtainium = new Alloy(3100, 0.3, 0.2, 0.5, 23);
        dilithium   = new Alloy(5200, 0.8, 0.1, 0.1, 35);
        pandemonium = new Alloy(2500, 0.1, 0.5, 0.4, 20);
    }

    @SuppressWarnings("Duplicates")
    /**
     * Calculates the cost of producing the given quantity of each individual alloy
     * @param alloys array of quantity of each alloy to be calculated
     * @return cost for each alloy
     */
    public double[] buy(int[] alloys){
        double[] cost = new double[4];

        double electricity = 0;
        double copper = 0;
        double plat = 0;

        double[] p = price(adamantium.calc(alloys[ADAMANTIUM]));
        copper += p[COPPER];
        plat += p[PLATINUM];
        cost[ADAMANTIUM] = p[0];
        electricity += p[1];

        p = price(unobtainium.calc(alloys[UNOBTAINIUM]));
        copper += p[COPPER];
        plat += p[PLATINUM];
        cost[UNOBTAINIUM] = p[0];
        electricity += p[1];

        p = price(dilithium.calc(alloys[DILITHIUM]));
        copper += p[COPPER];
        plat += p[PLATINUM];
        cost[DILITHIUM] = p[0];
        electricity += p[1];

        p = price(pandemonium.calc(alloys[PANDEMONIUM]));
        copper += p[COPPER];
        plat += p[PLATINUM];
        cost[PANDEMONIUM] = p[0];
        electricity += p[1];

        cost[ELECTRICITY]   = calcElectricity(electricity);

        cost[COPPER]    = copperDiscount(copper, cost);
        cost[IRON]      = freeIron(plat, cost);
        cost[PLATINUM]  = extraPlat(plat, cost);

        return cost;
    }

    private double copperDiscount(double copper, double[] cost){
        return copper > 8 ? cost[COPPER] * 0.9 : cost[COPPER];
    }

    private double freeIron(double plat, double[] cost){
        if (plat < 1)
            return cost[IRON];

        int iron = (int) plat;
        double freeIron = (double) iron * IRON_COST;

        if (cost[IRON] < freeIron)
            return 0;
        else
            return cost[IRON] -= freeIron;
    }

    private double extraPlat(double plat, double[] cost){
        if (plat < 1)
            return cost[PLATINUM];

        int p = (int) plat;
        return cost[PLATINUM] + (p * 10.0);
    }

    /**
     * Calculates the cost/price for the given quantities of resources
     * Return the cost
     * @param quant array of each resource
     * @return total price, 0 = resources, 1 = electricity
     */
    private double[] price(double[] quant){
        double[] p = new double[2];

        p[0] += quant[PLATINUM]    * PLATINUM_COST;
        p[0] += quant[IRON]        * IRON_COST;
        p[0] += quant[COPPER]      * COPPER_COST;

        p[1] += quant[ELECTRICITY];

        return p;
    }

    private double calcElectricity(double electricity){
        return Math.exp(0.005 * electricity);
    }
}
