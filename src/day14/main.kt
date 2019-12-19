package day14

fun main() {
    part1()
}

fun part1() {
    val data = data_4
    val lookup = lookup_4
    val helper = ChemicalCreator(data, lookup)

    helper.createChemical("FUEL")

    println(helper.resources)
    println(helper.spentResources)
    println(helper.totalResources()["ORE"])
}

