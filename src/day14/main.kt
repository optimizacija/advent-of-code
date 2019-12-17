package day14

class Temp {
    private val data: ReactionInputsLookup
    private val lookup: ReactionOutputLookup
    var resources: MutableMap<Chemical, Int>
    var spentResources: MutableMap<Chemical, Int>

    constructor(data: ReactionInputsLookup, lookup: ReactionOutputLookup) {
        this.data = data
        this.lookup = lookup

        resources = lookup.map { Pair(it.key, 0) }.toMap().toMutableMap()
        spentResources = lookup.map { Pair(it.key, 0) }.toMap().toMutableMap()
        resources["ORE"] = 0
        spentResources["ORE"] = 0
    }

    fun allocateOptimally(chemical: String, amount: Int) {
        // resources are shared by every chemical
        // TODO: WIP
        if (canTakeResource(chemical, amount)) {
            takeResource(chemical, amount)
        } else if (chemical == "ORE") {
            addResource(chemical, amount)
        } else {
            val chemicalAmount = lookup[chemical]!!
            val prodAmount = chemicalAmount.second
            val ceiledAmount = amount.raisedToModBase(prodAmount)
            val prodCount = ceiledAmount / prodAmount
            for (i in 0 until prodCount) {
                val deps = data[chemicalAmount]!!
                for (dep in deps) {
                    allocateOptimally(dep.first, dep.second)
                    takeResource(dep.first, dep.second)
                }
            }
            addResource(chemical, ceiledAmount)
        }
    }

    private fun canTakeResource(chemical: String, amount: Int): Boolean {
        return resources[chemical]!! >= amount
    }

    private fun takeResource(chemical: String, amount: Int) {
        println("removed $amount $chemical")
        resources[chemical] = resources[chemical]!! - amount
        spentResources[chemical] = spentResources[chemical]!! + amount
    }

    private fun addResource(chemical: String, amount: Int) {
        println("added $amount $chemical")
        resources[chemical] = resources[chemical]!! + amount
    }

    fun totalOreCount(): Int {
        return resources["ORE"]!! + spentResources["ORE"]!!
    }

}

fun main() {
    val helper = ChemicalResourceAllocator(data_1, lookup_1)
    println(data_1)
    println(helper.getOres())
    println(helper.getBaseChemicals())

    println(helper.makeOresDistinct())

    println(data_1)
    println(helper.getOres())
    println(helper.getBaseChemicals())
    /*
    val subBaseChemicals = helper.getBaseChemicals()
    val chemicalDist = subBaseChemicals.map{ Pair(it, helper.getTotalRequiredChemicals(it, "FUEL").raisedToModBase(helper.getReactionResult(it).second))}
    val reactionCounts = chemicalDist.map{ Pair(it.first, it.second / helper.getReactionResult(it.first).second )}
    val oreDist = reactionCounts.map{helper.getReactionComponents(it.first)!!.first().second * it.second}
    println(chemicalDist)
    println(oreDist)
    println(oreDist.sum())
     */
}