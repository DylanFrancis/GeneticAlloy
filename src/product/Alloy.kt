package product

class Alloy (price: Double,
             private val platinum: Double,
             private val iron: Double,
             private val copper: Double,
             private val electricity: Double) {

    val price: Double = price
        get() = field

    private val PLATINUM = "platinum"
    private val IRON = "iron"
    private val COPPER = "copper"
    private val ELECTRICITY = "electricity"


    /**
     * Calculates the total resource quantities to make a number of alloys
     * @param alloy amounts
     * @return an array with the quantity of each resource required
     */
    fun calc(alloy: Int): Map<String, Double> {
        return mapOf(
                PLATINUM to platinum * alloy,
                IRON to iron * alloy,
                COPPER to copper * alloy,
                ELECTRICITY to electricity * alloy)
    }
}