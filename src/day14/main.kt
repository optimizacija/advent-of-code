package day14

fun main() {
    val helper = ChemicalCreator(data_4, lookup_4)
    println("----")
    println(helper.resources)
    println(helper.spentResources)
    println("----")

    println("ores: ${helper.getOres()}")
    println(helper.getBaseChemicals())

    // helper.makeOresDistinct()

    println("----")
    println(helper.resources)
    println(helper.spentResources)
    println("----")

    println(helper.getOres())
    println(helper.getBaseChemicals())

    println("----")
    helper.createChemical("FUEL")
    println(helper.resources)
    println(helper.spentResources)
    println("----")

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