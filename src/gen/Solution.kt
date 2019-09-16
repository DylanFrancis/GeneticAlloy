package gen

class Solution (val vectors : MutableMap<String, Double>) : Comparable<Solution>{
    constructor() : this(mutableMapOf())

    var result : Double = 0.toDouble()

    override fun compareTo(other: Solution) = (result - other.result).toInt()
}