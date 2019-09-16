package gen

import resources.Shop
import kotlin.random.Random
import kotlin.random.asJavaRandom

fun main(){
    val evo = Evo(10000)
    evo.initialisePopulation()

    for (i in 1..100000){
        evo.run()
        evo.selection()
        evo.mutate()

        println("${evo.best.result}")
        for (v in evo.best.vectors){
            print("${v.key} ${v.value} ")
        }
        println("\n")
    }
}

class Evo (val population : Int){
    var curGeneration   = mutableListOf<Solution>()
    var newGeneration   = mutableListOf<Solution>()
    var best = Solution()


    val mutation_rate       = 0.5
    val mutation_magnitude  = 0.01

    val random = java.util.Random()
    val tournament_count = population / 20
    val alloys = listOf("adamantium", "unobtainium", "dilithium", "pandemonium")
    val shop = Shop()



    fun initialisePopulation(){
        println(tournament_count)
        for (x in 1..population){
            val newSolution = Solution()
            for (a in alloys) newSolution.vectors[a] = Random.nextDouble(0.0, 150.0)
            curGeneration.add(newSolution)
        }
    }

    fun run(){
        for (g in curGeneration) g.result = shop.buy(g.vectors)
    }

    fun selection(){
        if (curGeneration.max()!! > best) best = curGeneration.max()!!

        for (x in 1..tournament_count) newGeneration.add(tournament())
    }

    fun tournament() : Solution{
        var tourn = mutableListOf<Solution>()
        for (x in 1..tournament_count) tourn.add(curGeneration[Random.nextInt(0, population)])
        return tourn.max()!!
    }

    fun mutate(){
        curGeneration = mutableListOf()
        for (cur in newGeneration){
            for (x in 1..tournament_count){
                val next = Solution()
                for (vector in cur.vectors)
                    if (Random.nextDouble(0.0, 1.1) < mutation_rate)
                        next.vectors[vector.key] = random.nextGaussian() + vector.value
                else next.vectors[vector.key] = vector.value

                curGeneration.add(next)
            }
        }
        newGeneration = mutableListOf()
    }
}