package day14

val lookup_1 = mutableMapOf(
    Pair("A", Pair("A", 10)),
    Pair("B", Pair("B", 1)),
    Pair("C", Pair("C", 1)),
    Pair("D", Pair("D", 1)),
    Pair("E", Pair("E", 1)),
    Pair("FUEL", Pair("FUEL", 1))
)

val data_1 = mutableMapOf(
    Pair(Pair("A", 10), listOf(Pair("ORE", 10))),
    Pair(Pair("B", 1), listOf(Pair("ORE", 1))),
    Pair(Pair("C", 1), listOf(Pair("A", 7), Pair("B", 1))),
    Pair(Pair("D", 1), listOf(Pair("A", 7), Pair("C", 1))),
    Pair(Pair("E", 1), listOf(Pair("A", 7), Pair("D", 1))),
    Pair(Pair("FUEL", 1), listOf(Pair("A", 7), Pair("E", 1)))
)


val lookup_2 = mutableMapOf(
    Pair("A", Pair("A", 2)),
    Pair("B", Pair("B", 3)),
    Pair("C", Pair("C", 5)),
    Pair("AB", Pair("AB", 1)),
    Pair("BC", Pair("BC", 1)),
    Pair("CA", Pair("CA", 1)),
    Pair("FUEL", Pair("FUEL", 1))
)

val data_2 = mutableMapOf(
    Pair(Pair("A", 2), listOf(Pair("ORE", 9))),
    Pair(Pair("B", 3), listOf(Pair("ORE", 8))),
    Pair(Pair("C", 5), listOf(Pair("ORE", 7))),
    Pair(Pair("AB", 1), listOf(Pair("A", 3), Pair("B", 4))),
    Pair(Pair("BC", 1), listOf(Pair("B", 5), Pair("C", 7))),
    Pair(Pair("CA", 1), listOf(Pair("C", 4), Pair("A", 1))),
    Pair(Pair("FUEL", 1), listOf(Pair("AB", 2), Pair("BC", 3), Pair("CA", 4)))
)



val lookup_5 = mutableMapOf(
    Pair("CNZTR", Pair("CNZTR", 8)),
    Pair("PLWSL", Pair("PLWSL", 4)),
    Pair("BHXH", Pair("BHXH", 4)),
    Pair("BMBT", Pair("BMBT", 6)),
    Pair("FUEL", Pair("FUEL", 1)),
    Pair("FHTLT", Pair("FHTLT", 6)),
    Pair("ZLQW", Pair("ZLQW", 6)),
    Pair("ZDVW", Pair("ZDVW", 1)),
    Pair("WPTQ", Pair("WPTQ", 4)),
    Pair("KTJDG", Pair("KTJDG", 9)),
    Pair("XMNCP", Pair("XMNCP", 2)),
    Pair("XDBXC", Pair("XDBXC", 2)),
    Pair("XCVML", Pair("XCVML", 5)),
    Pair("MZWV", Pair("MZWV", 7)),
    Pair("VRPVC", Pair("VRPVC", 7)),
    Pair("RJRHP", Pair("RJRHP", 6)),
    Pair("LTCX", Pair("LTCX", 5))
)

val data_5 = mutableMapOf(
    Pair(Pair("CNZTR", 8), listOf(Pair("ORE", 171))),
    Pair(
        Pair("PLWSL", 4),
        listOf(
            Pair("ZLQW", 7),
            Pair("BMBT", 3),
            Pair("XCVML", 9),
            Pair("XMNCP", 26),
            Pair("WPTQ", 1),
            Pair("MZWV", 2),
            Pair("RJRHP", 1)
        )
    ),
    Pair(Pair("BHXH", 4), listOf(Pair("ORE", 114))),
    Pair(Pair("BMBT", 6), listOf(Pair("VRPVC", 14))),
    Pair(
        Pair("FUEL", 1),
        listOf(
            Pair("BHXH", 6),
            Pair("KTJDG", 18),
            Pair("WPTQ", 12),
            Pair("PLWSL", 7),
            Pair("FHTLT", 31),
            Pair("ZDVW", 37)
        )
    ),
    Pair(
        Pair("FHTLT", 6),
        listOf(
            Pair("WPTQ", 6),
            Pair("BMBT", 2),
            Pair("ZLQW", 8),
            Pair("KTJDG", 18),
            Pair("XMNCP", 1),
            Pair("MZWV", 6),
            Pair("RJRHP", 1)
        )
    ),
    Pair(Pair("ZLQW", 6), listOf(Pair("XDBXC", 15), Pair("LTCX", 2), Pair("VRPVC", 1))),
    Pair(
        Pair("ZDVW", 1),
        listOf(
            Pair("WPTQ", 13),
            Pair("LTCX", 10),
            Pair("RJRHP", 3),
            Pair("XMNCP", 14),
            Pair("MZWV", 2),
            Pair("ZLQW", 1)
        )
    ),
    Pair(Pair("WPTQ", 4), listOf(Pair("BMBT", 5))),
    Pair(Pair("KTJDG", 9), listOf(Pair("ORE", 189))),
    Pair(Pair("XMNCP", 2), listOf(Pair("MZWV", 1), Pair("XDBXC", 17), Pair("XCVML", 3))),
    Pair(Pair("XDBXC", 2), listOf(Pair("VRPVC", 12), Pair("CNZTR", 27))),
    Pair(Pair("XCVML", 5), listOf(Pair("KTJDG", 15), Pair("BHXH", 12))),
    Pair(Pair("MZWV", 7), listOf(Pair("BHXH", 3), Pair("VRPVC", 2))),
    Pair(Pair("VRPVC", 7), listOf(Pair("ORE", 121))),
    Pair(Pair("RJRHP", 6), listOf(Pair("XCVML", 7))),
    Pair(Pair("LTCX", 5), listOf(Pair("BHXH", 5), Pair("VRPVC", 4)))
)
