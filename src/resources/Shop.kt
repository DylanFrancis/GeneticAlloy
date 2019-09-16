package resources

import product.Alloy
import utils.Codes
import java.util.function.BiConsumer
import java.util.function.ToDoubleBiFunction


fun main() {
    val shop = Shop()
    println(shop.buy(mapOf(shop.ADAMANTIUM to 9.5, shop.UNOBTAINIUM to 10.12, shop.DILITHIUM to 20.45, shop.PANDEMONIUM to 100.23)))

}

class Shop {
    val PLATINUM = "platinum"
    val IRON = "iron"
    val COPPER = "copper"
    val ELECTRICITY = "electricity"
    val ADAMANTIUM = "adamantium"
    val UNOBTAINIUM = "unobtainium"
    val DILITHIUM = "dilithium"
    val PANDEMONIUM = "pandemonium"


    private val PLATINUM_COST = 1200.0
    private val IRON_COST = 300.0
    private val COPPER_COST = 800.0

    private val alloys = mapOf(
            ADAMANTIUM  to Alloy(3000.0, 0.2, 0.7, 0.1, 25.0),
            UNOBTAINIUM to Alloy(3100.0, 0.3, 0.2, 0.5, 23.0),
            DILITHIUM   to Alloy(5200.0, 0.8, 0.1, 0.1, 35.0),
            PANDEMONIUM to Alloy(2500.0, 0.1, 0.5, 0.4, 20.0))



    /**
     * returns total cost of given alloys
     */
    fun buy(alloys: Map<String, Double>): Double {
        var totalCosts = 0.0
        var electricity = 0.0
        var plat = 0.0
        var copper = 0.0
        var iron = 0.0
        var revenue = 0.0

        // Total material amounts
        for ((key, value) in alloys){
            // Map of quantity of each material to make alloy
            val quants = this.alloys[key]?.calc(value)!!

            revenue += this.alloys[key]?.price(value)!!

            plat        += quants[PLATINUM]!!
            copper      += quants[COPPER]!!
            electricity += quants[ELECTRICITY]!!
            iron        += quants[IRON]!!
        }

        // Calc and total costs
        totalCosts += calcCopper(copper)
        totalCosts += calcIron(plat, iron)
        totalCosts += calcPlat(plat)
        totalCosts += calcElectricity(electricity)

        return revenue - totalCosts
    }

    fun calcCopper(copper : Double) : Double{
        if (copper <= 0.0) return 0.0

        return if (copper > 8.0)
            (copper * COPPER_COST) * 0.9
        else
            copper * COPPER_COST

    }

    fun calcIron(platinum : Double, iron : Double) : Double{
        if (iron <= 0.0) return iron

        val p = platinum.toInt()
        val i = iron.toInt()

        if (p == 0)
            return iron * IRON_COST
        if (p >= i)
            return 0.0

        return (iron - p) * IRON_COST

    }

    fun calcPlat(platinum: Double) : Double{
        if (platinum <= 0.0) return 0.0
        val p = platinum.toInt()
        return (platinum * PLATINUM_COST) + (p * 10)
    }

    fun calcElectricity(electricity : Double) : Double{
        return Math.exp(0.005 * electricity)
    }

}