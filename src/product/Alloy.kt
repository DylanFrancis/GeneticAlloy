package product

class Alloy (private val price: Double,
             private val platinum: Double,
             private val iron: Double,
             private val copper: Double,
             private val electricity: Double) {

    private val PLATINUM = "platinum"
    private val IRON = "iron"
    private val COPPER = "copper"
    private val ELECTRICITY = "electricity"


    /**
     * Calculates the total resource quantities to make a number of alloys
     * @param alloy amounts
     * @return an array with the quantity of each resource required
     */
    fun calc(alloy: Double): Map<String, Double> {
        return mapOf(
                PLATINUM    to Math.abs(platinum * alloy),
                IRON        to Math.abs(iron * alloy),
                COPPER      to Math.abs(copper * alloy),
                ELECTRICITY to Math.abs(electricity * alloy))
    }

    fun price(quant: Double) : Double{
        if (quant <= 0.0) return 0.0
        return quant * price
    }
}