package day6

fun main() {
    val mp = createMap(data)
    println(totalNumberOfOrbits(mp))
    println(totalNumberOfTransfers(mp))
}

fun createMap(data: Array<String>): Map<String, String> {
    val regex = """(\w+)\)(\w+)""".toRegex()

    return data.map {
        val matches = regex.find(it)!!
        val planet = matches.groups[1]!!.value
        val moon = matches.groups[2]!!.value

        moon to planet
    }.toMap()
}

fun totalNumberOfOrbits(mp: Map<String, String>): Int {
    return mp.keys.map { key ->
        var curr = key
        var acc = 0
        while (curr != "COM") {
            ++acc
            curr = mp[curr] ?: error("impossibru")
        }
        acc
    }.sum()
}

fun totalNumberOfTransfers(mp: Map<String, String>): Int {
    var youPath = mp.getParentPath("YOU", "COM")
    var sanPath = mp.getParentPath("SAN", "COM")

    for(i in 0..(sanPath.size-1)){
        val youPathPlanet = youPath[i]
        val sanPathPlanet = sanPath[i]

        if(youPathPlanet != sanPathPlanet) {
            youPath = youPath.drop(i)
            sanPath = sanPath.drop(i)
            break
        }
    }

    println(youPath)
    println(sanPath)
    return youPath.size + sanPath.size -2
}

private fun <V> Map<V, V>.getParentPath(value: V, stop: V): List<V> {

    val result = ArrayList<V>()
    var curr = value
    while(curr != stop) {
        result.add(curr)
        curr = this[curr] ?: error("element $curr not found")
    }
    result.add(stop)
    result.reverse()

    return result
}
