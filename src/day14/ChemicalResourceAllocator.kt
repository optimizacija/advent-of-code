package day14

class ChemicalResourceAllocator(var reactions: ReactionInputsLookup,
                                var chemicalResultLookup: ReactionOutputLookup) {

    fun getTotalRequiredChemicals(chemical: Chemical, root: Chemical): Int {
        val requiredAmounts = getReactionComponents(root).map(fun (it: ReactionInput) : Int {
            val (inputChemical, inputAmount) = it

            if (inputChemical == chemical) {
                return inputAmount
            } else if (!isOre(inputChemical)) {
                val outputAmount = getReactionResult(inputChemical).second
                val multi = inputAmount.raisedToModBase(outputAmount)
                val multiplier = multi / outputAmount
                val requiredChemicals = getTotalRequiredChemicals(chemical, inputChemical)
                val result = multiplier*requiredChemicals
                return result
            }
            return 0
        })
        return requiredAmounts.sum()
    }

    fun makeOresDistinct() {
        // A, B
        val chemicals = getBaseChemicals()
        // (A, A_ORE), (B, B_ORE)
        val chemicalOres = chemicals.map{Pair(it, it + "_ORE")}

        chemicalOres.forEach{
            // (A, 9)
            val reactionResult = getReactionResult(it.first)
            // ((A, 9), [(ORE, 10)]) = ((A, 9), [(A_ORE, 10)]
            reactions[reactionResult] = listOf(Pair(it.second, reactions[reactionResult]!![0].second))
        }
    }

    fun getReactionComponents(chemical: Chemical): ReactionComponents {
        return reactions[chemicalResultLookup[chemical]]!!
    }

    fun getReactionResult(chemical: Chemical): ReactionResult {
        return chemicalResultLookup[chemical] ?: error("chemical output not found for $chemical")
    }

    fun getOres(): List<Chemical> {
        val allChemicals = reactions.flatMap { it.value }.map{it.first}
        return allChemicals.filter{isOre(it)}.distinct()
    }

    fun isOre(chemical: Chemical): Boolean {
        return !chemicalResultLookup.contains(chemical)
    }

    fun getBaseChemicals(): List<Chemical> {
        return reactions.filter { isBaseChemical(it.key.first) }.map { it.key.first }
    }

    fun isBaseChemical(chemical: Chemical) : Boolean {
        val components = getReactionComponents(chemical)
        return components.size == 1 && components.any{isOre(it.first)}
    }
}