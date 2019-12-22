package day14

import kotlin.math.ceil

fun getReactions(src: String): List<Reaction> {
    val regex = """((\d+) (\w+))""".toRegex()
    val sources = src.split("\n")
    return sources.map{
        val matches = regex.findAll(it)
        val ingredients = matches.map{
                matchResult -> Ingredient(matchResult.groupValues[3], matchResult.groupValues[2].toLong())
        }.toList()
        Reaction(ingredients.last(), ingredients.dropLast(1))
    }
}

class Reactor{
    val reactions: List<Reaction>
    lateinit var surplus: MutableMap<String, Long>

    constructor(reactions: List<Reaction>) {
        this.reactions = reactions
        resetSurplus()
    }

    fun oreCountForChemical(chemical: String, amount: Long): Long {
        val result = oreCountForChemicalImpl(chemical, amount)
        resetSurplus()
        return result
    }

    private fun oreCountForChemicalImpl(chemical: String, amount: Long): Long {
        if(chemical == "ORE")
            return amount

        val chemicalSurplus = surplus[chemical]!!
        if(chemicalSurplus >= amount) {
            surplus[chemical] = chemicalSurplus - amount
            return 0L
        }

        val missingAmount = amount - chemicalSurplus
        val reaction = getReaction(chemical)
        val reactionCount = ceil(missingAmount.toDouble() / reaction.result.amount).toLong()
        val result = reaction.ingredients.map{ oreCountForChemicalImpl(it.chemical, it.amount * reactionCount) }.sum()
        val producedAmount = reactionCount* reaction.result.amount
        surplus[chemical] = chemicalSurplus + producedAmount - amount
        return result
    }

    fun getReaction(chemical: String): Reaction {
        return reactions.find{it.result.chemical == chemical} ?: error("chemical $chemical not found")
    }

    fun maxChemicalCount(chemical: String, availableOre: Long): Long {
        val singleChemicalOreCount = oreCountForChemical(chemical, 1)

        var min = availableOre / singleChemicalOreCount
        var max = 2* min
        while(true) {
            val mid = (max - min) / 2 + min
            val oreCount = oreCountForChemical(chemical, mid)
            println("[$min, $mid, $max]: $oreCount")

            if(oreCount > availableOre) {
                max = mid
            } else { // oreCount < availableOre
                min = mid
                if ((max - min) <= 1)
                    return mid
            }
        }

    }

    fun resetSurplus() {
        surplus = reactions.map{Pair(it.result.chemical, 0L)}.toMap().toMutableMap()
    }

}


fun main() {
    val reactor = Reactor(getReactions(src2))

    val result1 = reactor.oreCountForChemical("FUEL", 1)
    val result2 = reactor.maxChemicalCount("FUEL", 1000000000000L)

    println("1 FUEL consumed $result1 ORE")
    println("Trillion ORE spent for $result2 FUEL")
}

