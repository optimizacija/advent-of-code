package day14

class ChemicalHelper(var inputLookup: ReactionInputsLookup, var outputLookup: ReactionOutputLookup) {

    fun isBaseChemical(chemical: Chemical): Boolean {
        return !outputLookup.contains(chemical)
    }

    fun getReactionInputs(chemical: Chemical): ReactionInputs {
        return inputLookup[outputLookup[chemical]]!!
    }

    fun getReactionOutput(chemical: Chemical): ReactionOutput {
        return outputLookup[chemical] ?: error("chemical output not found for $chemical")
    }

    fun getTotalChemicalContribution(chemical: Chemical, root: Chemical, carried: Int = 0): Int {
        val contributions = getReactionInputs(root).map {
            val (inputChemical, inputAmount) = it
            var result = 0
            if (inputChemical == chemical) {
                result = modCeil(inputAmount* carried, getReactionOutput(chemical).second )
            } else if (!isBaseChemical(inputChemical)) {
                val inputChemReactionAmount = getReactionOutput(inputChemical).second
                val multiplier = modCeil(inputAmount, inputChemReactionAmount) / inputChemReactionAmount
                result = getTotalChemicalContribution(chemical, inputChemical, multiplier)
            }
            result
        }
        return contributions.sum()
    }

    fun getChemicalContribution(chemical: Chemical, inputs: ReactionInputs): Int {
        val reactionInput = inputs.find { it.first == chemical } ?: return 0
        return reactionInput.second
    }

    fun getSubBaseChemicals(): List<Chemical> {
        return inputLookup.filter { it.value.any { isBaseChemical(it.first) } }.map { it.key.first }.toList()
    }
}