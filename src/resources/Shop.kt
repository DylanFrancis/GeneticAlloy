package resources

import product.Alloy
import utils.Codes
import java.util.function.BiConsumer
import java.util.function.ToDoubleBiFunction

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

    private val alloys = mapOf(ADAMANTIUM to Alloy(3000.0, 0.2, 0.7, 0.1, 25.0),
            UNOBTAINIUM to Alloy(3100.0, 0.3, 0.2, 0.5, 23.0),
            DILITHIUM to Alloy(5200.0, 0.8, 0.1, 0.1, 35.0),
            PANDEMONIUM to Alloy(2500.0, 0.1, 0.5, 0.4, 20.0))

    fun buy(alloys: Map<String, Int>): Map<String, Double> {
        val totalCosts: MutableMap<String, Double>
        var electricity = 0
        var plat = 0.0
        var copper = 0.0

        for ((key, value) in alloys){
            // Map of cost of each material to make alloy
            val costMap = this.alloys[key]?.calc(value)!!
            plat += costMap[PLATINUM]!!
            copper += costMap[COPPER]!!
            totalCosts.put(key, costMap.values.stream().mapToDouble { ToDoubleBiFunction })
        }

        return totalCosts
    }

}