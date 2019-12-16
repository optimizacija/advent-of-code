package day14

class ChemicalResourceAllocator {
    private val data: ChemicalFormulaMap
    private val lookup: ChemicalAmountLookup
    var resources: MutableMap<Chemical, Int>
    var spentResources: MutableMap<Chemical, Int>

    constructor(data: ChemicalFormulaMap, lookup: ChemicalAmountLookup) {
        this.data = data
        this.lookup = lookup

        resources = lookup.map { Pair(it.key, 0) }.toMap().toMutableMap()
        spentResources = lookup.map { Pair(it.key, 0) }.toMap().toMutableMap()
        resources["ORE"] = 0
        spentResources["ORE"] = 0
    }

    fun allocateResources(chemical: String, amount: Int) {
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
                    allocateResources(dep.first, dep.second)
                    takeResource(dep.first, dep.second)
                }
            }
            addResource(chemical, ceiledAmount)
        }
    }

    fun modCeil(amount: Int, mod: Int): Int {
        var i = mod
        while (i < amount) {
            i += mod
        }
        return i
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
    val lookup = lookup_3
    val data = data_3

    val allocator = ChemicalResourceAllocator(data, lookup)
    allocator.allocateResources("FUEL", 1)
    /*
    println(allocator.modCeil(7, 12)) // 12
    println(allocator.modCeil(13, 12)) // 24
    println(allocator.modCeil(12, 12)) // 12
     */

    println(allocator.totalOreCount())
    println(allocator.resources)
    println(allocator.spentResources)
    println("ore count: ${allocator.totalOreCount()}")
}