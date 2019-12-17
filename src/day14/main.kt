package day14

fun modCeil(amount: Int, mod: Int): Int {
    var i = mod
    while (i < amount) {
        i += mod
    }
    return i
}

class ChemicalResourceAllocator {
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
            val ceiledAmount = modCeil(amount, prodAmount)
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
    val helper = ChemicalHelper(data_1, lookup_1)
    println(helper.getReactionInputs("FUEL"))
    println(helper.getReactionOutput("FUEL"))
    // println(helper.getTotalChemicalContribution("A", "AB"))
    // println(helper.getTotalChemicalContribution("A", "FUEL"))
    // println(helper.getTotalChemicalContribution("B", "FUEL"))
    println(helper.getTotalChemicalContribution("C", "FUEL"))
    println(helper.getSubBaseChemicals())
}