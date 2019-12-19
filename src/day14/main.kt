package day14

fun main() {
    val data = data_0
    val lookup = lookup_0
    val helper = ChemicalCreator(data, lookup)
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
    println(helper.totalResources())
    println(helper.totalResources()["ORE"])
    println("----")
}