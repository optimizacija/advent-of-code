package day14

fun Int.raisedToModBase(mod: Int): Int {
    var i = mod
    while (i < this) {
        i += mod
    }
    return i
}

