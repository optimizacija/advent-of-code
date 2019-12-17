package day14

fun main() {
    val lookup = lookup_3
    val data = data_3

    var requirements = mutableListOf<ReactionOutput>()
    requirements.addAll(data[lookup["FUEL"]]!!)

    while (requirements.any { it.first != "ORE" }) {
        println(requirements)
        val newReqs = mutableListOf<ReactionOutput>()
        for (requirement in requirements) {
            if(requirement.first == "ORE") continue
            val (requiredChemical, requiredAmount) = requirement
            val (_, reactionAmount) = lookup[requiredChemical]!!

            val producedAmount = modCeil(requiredAmount, reactionAmount)
            val reactionCount = producedAmount / reactionAmount

            if (producedAmount != requiredAmount) {
                println("fix")
                // fix reaction element
                for (i in 0 until reactionCount) {
                    newReqs.add(Pair(requiredChemical, reactionAmount))
                }
            } else {
                println("expand")
                // expand reaction element
                newReqs.addAll(data[lookup[requiredChemical]]!!)
            }
        }
        newReqs.addAll(requirements.filter { it.first == "ORE" })
        println(newReqs)
        requirements = newReqs
    }


    /*
    val allocator = ChemicalResourceAllocator(data, lookup)
    // allocator.allocateOptimally("FUEL", 1)
    println(allocator.modCeil(7, 12)) // 12
    println(allocator.modCeil(13, 12)) // 24
    println(allocator.modCeil(12, 12)) // 12

    println(allocator.totalOreCount())
    println(allocator.resources)
    println(allocator.spentResources)
    println("ore count: ${allocator.totalOreCount()}")
     */
}
