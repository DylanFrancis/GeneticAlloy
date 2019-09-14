package resources;

public abstract class AMaterial {
    private double cost;
    private double quantity = 0.0;

    public AMaterial(double cost) {
        this.cost = cost;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getQuantity() {
        return quantity;
    }

    public void buy(double quantity){
        incQuantity(quantity);

    }

    public void incQuantity(double quantity) {
        this.quantity += quantity;
    }
}
