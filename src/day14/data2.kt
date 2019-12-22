package day14

val src1 = "10 ORE => 10 A\n" +
        "1 ORE => 1 B\n" +
        "7 A, 1 B => 1 C\n" +
        "7 A, 1 C => 1 D\n" +
        "7 A, 1 D => 1 E\n" +
        "7 A, 1 E => 1 FUEL"

val src2 = "9 ORE => 2 A\n" +
        "8 ORE => 3 B\n" +
        "7 ORE => 5 C\n" +
        "3 A, 4 B => 1 AB\n" +
        "5 B, 7 C => 1 BC\n" +
        "4 C, 1 A => 1 CA\n" +
        "2 AB, 3 BC, 4 CA => 1 FUEL"

val src3 = "157 ORE => 5 NZVS\n" +
        "165 ORE => 6 DCFZ\n" +
        "44 XJWVT, 5 KHKGT, 1 QDVJ, 29 NZVS, 9 GPVTF, 48 HKGWZ => 1 FUEL\n" +
        "12 HKGWZ, 1 GPVTF, 8 PSHF => 9 QDVJ\n" +
        "179 ORE => 7 PSHF\n" +
        "177 ORE => 5 HKGWZ\n" +
        "7 DCFZ, 7 PSHF => 2 XJWVT\n" +
        "165 ORE => 2 GPVTF\n" +
        "3 DCFZ, 7 NZVS, 5 HKGWZ, 10 PSHF => 8 KHKGT"

val src4 = "171 ORE => 8 CNZTR\n" +
        "7 ZLQW, 3 BMBT, 9 XCVML, 26 XMNCP, 1 WPTQ, 2 MZWV, 1 RJRHP => 4 PLWSL\n" +
        "114 ORE => 4 BHXH\n" +
        "14 VRPVC => 6 BMBT\n" +
        "6 BHXH, 18 KTJDG, 12 WPTQ, 7 PLWSL, 31 FHTLT, 37 ZDVW => 1 FUEL\n" +
        "6 WPTQ, 2 BMBT, 8 ZLQW, 18 KTJDG, 1 XMNCP, 6 MZWV, 1 RJRHP => 6 FHTLT\n" +
        "15 XDBXC, 2 LTCX, 1 VRPVC => 6 ZLQW\n" +
        "13 WPTQ, 10 LTCX, 3 RJRHP, 14 XMNCP, 2 MZWV, 1 ZLQW => 1 ZDVW\n" +
        "5 BMBT => 4 WPTQ\n" +
        "189 ORE => 9 KTJDG\n" +
        "1 MZWV, 17 XDBXC, 3 XCVML => 2 XMNCP\n" +
        "12 VRPVC, 27 CNZTR => 2 XDBXC\n" +
        "15 KTJDG, 12 BHXH => 5 XCVML\n" +
        "3 BHXH, 2 VRPVC => 7 MZWV\n" +
        "121 ORE => 7 VRPVC\n" +
        "7 XCVML => 6 RJRHP\n" +
        "5 BHXH, 4 VRPVC => 5 LTCX"

val src5 = "8 LHFV => 3 PMVMQ\n" +
        "2 ZXNM, 1 PSVLS, 4 GRDNT, 26 GLZH, 3 VHJX, 16 BGPF, 1 LHVTN => 4 BTQL\n" +
        "10 NKHSG, 20 FCPC, 11 GRDNT => 5 HDJB\n" +
        "6 WPZN, 1 LHFV => 7 BGPF\n" +
        "1 WDXT, 1 PLCNZ => 3 QHFKR\n" +
        "12 LCHZ => 1 TPXCK\n" +
        "11 LSNG => 4 XFGH\n" +
        "195 ORE => 4 GRNC\n" +
        "8 XFGQ => 1 GRDNT\n" +
        "1 FBRG => 5 LCHZ\n" +
        "7 XZBJ, 8 RSZF, 9 SVDX => 9 LWDP\n" +
        "20 WDXT => 5 RQFRT\n" +
        "1 LXQWG, 1 GLZH => 6 SDLJ\n" +
        "4 XFGH => 1 GCZLZ\n" +
        "1 WPZN => 1 FBRG\n" +
        "19 XZBJ => 5 WXGV\n" +
        "1 GDXC => 6 WDXT\n" +
        "1 WXGV, 1 NKHSG, 2 LWDP => 5 FCNPB\n" +
        "4 LWDP, 5 BGPF => 9 KLRB\n" +
        "1 GMRN => 4 GLZH\n" +
        "1 RQFRT => 5 SVDX\n" +
        "2 HWKG => 7 LHFV\n" +
        "2 LCHZ, 13 JTJT, 10 TPXCK => 3 RSZF\n" +
        "29 MZTVH => 6 TSGR\n" +
        "9 NRFLK, 1 SVDX => 5 NKHSG\n" +
        "123 ORE => 9 GDXC\n" +
        "1 PZPBV, 21 PMVMQ, 1 GCZLZ => 8 SKZGB\n" +
        "3 GRNC, 5 GDXC => 8 QZVM\n" +
        "6 VTDQ, 13 TCQW, 3 FCNPB, 48 PSVLS, 3 RLNF, 73 BTQL, 5 MHRVG, 26 BGPF, 26 HDJB, 5 XFGQ, 6 HTFL => 1 FUEL\n" +
        "5 QZVM, 2 JTJT => 1 PXKHG\n" +
        "3 LSNG, 1 PMVMQ => 8 VTDQ\n" +
        "31 XFGH => 1 FCPC\n" +
        "9 PSVLS => 8 FWGTF\n" +
        "1 GRNC => 3 WPZN\n" +
        "16 JBXDX, 4 GRNC => 6 HWKG\n" +
        "1 SKZGB, 5 RSZF => 4 XZBJ\n" +
        "134 ORE => 9 CTDRZ\n" +
        "1 SVDX, 2 TPXCK => 7 JTJT\n" +
        "6 RQFRT, 4 KBCW => 3 BGNLR\n" +
        "12 KLRB, 12 LHFV => 4 HTFL\n" +
        "2 GMRN => 6 XFGQ\n" +
        "16 WNSW, 12 SKZGB => 8 LXQWG\n" +
        "2 NRFLK, 2 CTDRZ => 9 JBXDX\n" +
        "1 PZPBV => 8 RLNF\n" +
        "2 JTJT, 5 GCZLZ => 3 WNSW\n" +
        "5 WXGV, 2 LCHZ => 2 SCDS\n" +
        "1 QHFKR => 3 GMRN\n" +
        "10 JTJT, 2 HRCG => 8 KBCW\n" +
        "7 HWKG => 4 PSVLS\n" +
        "7 WNSW, 1 PXKHG, 3 BGNLR => 9 MZTVH\n" +
        "15 TPXCK, 11 LHFV => 5 HRCG\n" +
        "1 LSNG, 1 HWKG => 3 PZPBV\n" +
        "7 BGPF => 9 PLCNZ\n" +
        "1 ZGWT => 6 ZXNM\n" +
        "26 NKHSG, 1 LHFV, 2 JTJT, 26 WXGV, 6 SDLJ, 1 KLRB, 1 TSGR => 8 TCQW\n" +
        "154 ORE => 4 NRFLK\n" +
        "1 GMRN => 3 VHJX\n" +
        "5 QZVM, 3 GDXC => 7 LSNG\n" +
        "5 WNSW => 5 ZGWT\n" +
        "6 QHFKR, 8 PZPBV, 10 FBRG, 13 FWGTF, 1 LHVTN, 4 SCDS, 8 VHJX, 7 TSGR => 6 MHRVG\n" +
        "12 GLZH => 5 LHVTN"
