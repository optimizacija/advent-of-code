package day14

class ChemicalCreator(var reactions: ReactionComponentLookup,
                      var chemicalResultLookup: ReactionResultLookup) {

    var resources = getEmptyChemicalCache()
    var spentResources = getEmptyChemicalCache()

    fun createChemical(chemical: Chemical) {
        consumeReactionComponents(getReactionComponents(chemical))
        addResource(chemical, getReactionResultAmount(chemical))
    }

    private fun consumeReactionComponents(reactionComponents: ReactionComponents) {
        reactionComponents.forEach(fun (it) {
            val (chemical, amount) = it

            if(!isChemicalStocked(chemical, amount)) {
                stockChemical(chemical, amount)
            }
            takeResource(chemical, amount)
        })
    }

    private fun stockChemical(chemical: Chemical, requiredAmount: Long) {
        if (isOre(chemical)) {
            addResource(chemical, requiredAmount)
        } else {
            val missingAmount = requiredAmount - resources[chemical]!!
            val reactionProducedAmount = getReactionResultAmount(chemical)
            val producedAmount = missingAmount.raisedToModBase(reactionProducedAmount)
            val reactionCount =  producedAmount / reactionProducedAmount
            for(i in 0L until reactionCount) {
                createChemical(chemical)
            }
        }
    }

    private fun addResource(chemical: Chemical, amount: Long) {
        resources[chemical] = (resources[chemical] ?: error("could not find chemical $chemical")) + amount
    }

    private fun takeResource(chemical: Chemical, amount: Long) {
        // take resources
        resources[chemical] = resources[chemical]!! - amount
        // write change to history
        spentResources[chemical] = spentResources[chemical]!! + amount
    }

    private fun isChemicalStocked(chemical: Chemical, requiredAmount: Long): Boolean {
        return resources[chemical]!! >= requiredAmount
    }

    fun getReactionComponents(chemical: Chemical): ReactionComponents {
        return reactions[chemicalResultLookup[chemical]]!!
    }

    fun getReactionResult(chemical: Chemical): ReactionResult {
        return chemicalResultLookup[chemical] ?: error("chemical output not found for $chemical")
    }

    fun getReactionResultAmount(chemical: Chemical): Long {
        return getReactionResult(chemical).second
    }

    fun getOres(): List<Chemical> {
        val allChemicals = reactions.flatMap { it.value }.map{it.first}
        return allChemicals.filter{isOre(it)}.distinct()
    }

    fun isOre(chemical: Chemical): Boolean {
        return !chemicalResultLookup.contains(chemical)
    }

    private fun getEmptyChemicalCache(): MutableMap<Chemical, Long> {
        val cache = reactions.flatMap{ it.value }.distinct().map{ Pair(it.first, 0L)}.toMap().toMutableMap()
        reactions.forEach{cache[it.key.first] = 0L} // basically only add FUEL
        return cache
    }

    fun totalResources(): MutableMap<Chemical, Long> {
        return resources.map{ Pair(it.key, it.value + spentResources[it.key]!!)}.toMap().toMutableMap()
    }
}