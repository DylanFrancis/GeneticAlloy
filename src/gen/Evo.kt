package gen

import resources.Shop
import java.io.File
import java.io.FileOutputStream
import kotlin.random.Random

fun main(){
    val evo = Evo(10000)
    evo.initialisePopulation()

    for (i in 1..50){
        evo.run()
        evo.selection()
        evo.mutate()
        writeBest(evo.best)
        println("${evo.best.result}")
        for (v in evo.best.vectors){
            print("${v.key} ${v.value} ")
        }
        println("\n")
    }
}

fun writeBest(best : Solution){
    FileOutputStream("best.csv", true).bufferedWriter().use { out ->
        best.vectors.forEach { t, u ->  out.write("$u;")}
        out.write("${best.result}\n")
        out.flush()
    }
}

fun write(gen : List<Solution>){
    File("results3.csv").bufferedWriter().use { out ->
        gen.forEach {
            out.write("${it.vectors["adamantium"]};${it.vectors["unobtainium"]};${it.vectors["dilithium"]};${it.vectors["pandemonium"]};${it.result}\n")
        }
        out.flush()
    }
}

class Evo (val population : Int){
    var curGeneration   = mutableListOf<Solution>()
    var newGeneration   = mutableListOf<Solution>()
    var best = Solution()


    val mutation_rate       = 0.004

    val random = java.util.Random()
    val tournament_count = population / 10
    val alloys = listOf("adamantium", "unobtainium", "dilithium", "pandemonium")
    val shop = Shop()



    fun initialisePopulation(){
        for (x in 1..population){
            val newSolution = Solution()
            for (a in alloys) newSolution.vectors[a] = Random.nextDouble(0.0, 50.0)
            curGeneration.add(newSolution)
        }
    }

    fun run() : List<Solution>{
        for (g in curGeneration) g.result = shop.buy(g.vectors)
        return curGeneration
    }

    fun selection(){
        if (curGeneration.max()!! > best) best = curGeneration.max()!!

        for (x in 1..tournament_count) newGeneration.add(tournament())
    }

    fun tournament() : Solution{
        val tourn = mutableListOf<Solution>()
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
                        next.vectors[vector.key] = (random.nextGaussian() + vector.value)
                else next.vectors[vector.key] = vector.value

                curGeneration.add(next)
            }
        }
        newGeneration = mutableListOf()

    }
}