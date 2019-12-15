package day11

import day9.toBigIntegerMutableList

val arr = arrayOf(
    3,
    8,
    1005,
    8,
    330,
    1106,
    0,
    11,
    0,
    0,
    0,
    104,
    1,
    104,
    0,
    3,
    8,
    102,
    -1,
    8,
    10,
    1001,
    10,
    1,
    10,
    4,
    10,
    1008,
    8,
    1,
    10,
    4,
    10,
    101,
    0,
    8,
    29,
    3,
    8,
    102,
    -1,
    8,
    10,
    1001,
    10,
    1,
    10,
    4,
    10,
    1008,
    8,
    0,
    10,
    4,
    10,
    1001,
    8,
    0,
    51,
    1006,
    0,
    78,
    2,
    107,
    9,
    10,
    1006,
    0,
    87,
    3,
    8,
    1002,
    8,
    -1,
    10,
    1001,
    10,
    1,
    10,
    4,
    10,
    108,
    1,
    8,
    10,
    4,
    10,
    1001,
    8,
    0,
    82,
    2,
    1103,
    5,
    10,
    1,
    101,
    8,
    10,
    3,
    8,
    1002,
    8,
    -1,
    10,
    1001,
    10,
    1,
    10,
    4,
    10,
    108,
    0,
    8,
    10,
    4,
    10,
    101,
    0,
    8,
    112,
    1006,
    0,
    23,
    1006,
    0,
    20,
    1,
    2,
    11,
    10,
    1,
    1007,
    12,
    10,
    3,
    8,
    1002,
    8,
    -1,
    10,
    1001,
    10,
    1,
    10,
    4,
    10,
    108,
    1,
    8,
    10,
    4,
    10,
    101,
    0,
    8,
    148,
    3,
    8,
    102,
    -1,
    8,
    10,
    101,
    1,
    10,
    10,
    4,
    10,
    108,
    1,
    8,
    10,
    4,
    10,
    1002,
    8,
    1,
    170,
    2,
    101,
    12,
    10,
    2,
    5,
    7,
    10,
    1,
    102,
    10,
    10,
    3,
    8,
    1002,
    8,
    -1,
    10,
    1001,
    10,
    1,
    10,
    4,
    10,
    1008,
    8,
    1,
    10,
    4,
    10,
    1001,
    8,
    0,
    205,
    1,
    1004,
    10,
    10,
    2,
    6,
    13,
    10,
    3,
    8,
    102,
    -1,
    8,
    10,
    1001,
    10,
    1,
    10,
    4,
    10,
    1008,
    8,
    0,
    10,
    4,
    10,
    1001,
    8,
    0,
    235,
    2,
    102,
    4,
    10,
    1006,
    0,
    16,
    1006,
    0,
    84,
    1006,
    0,
    96,
    3,
    8,
    1002,
    8,
    -1,
    10,
    1001,
    10,
    1,
    10,
    4,
    10,
    108,
    0,
    8,
    10,
    4,
    10,
    1001,
    8,
    0,
    269,
    1006,
    0,
    49,
    2,
    1003,
    6,
    10,
    2,
    1104,
    14,
    10,
    1006,
    0,
    66,
    3,
    8,
    102,
    -1,
    8,
    10,
    101,
    1,
    10,
    10,
    4,
    10,
    108,
    0,
    8,
    10,
    4,
    10,
    1002,
    8,
    1,
    305,
    2,
    1,
    11,
    10,
    101,
    1,
    9,
    9,
    1007,
    9,
    1020,
    10,
    1005,
    10,
    15,
    99,
    109,
    652,
    104,
    0,
    104,
    1,
    21102,
    838479487744,
    1,
    1,
    21102,
    1,
    347,
    0,
    1106,
    0,
    451,
    21101,
    666567967640,
    0,
    1,
    21101,
    358,
    0,
    0,
    1106,
    0,
    451,
    3,
    10,
    104,
    0,
    104,
    1,
    3,
    10,
    104,
    0,
    104,
    0,
    3,
    10,
    104,
    0,
    104,
    1,
    3,
    10,
    104,
    0,
    104,
    1,
    3,
    10,
    104,
    0,
    104,
    0,
    3,
    10,
    104,
    0,
    104,
    1,
    21101,
    28994219048,
    0,
    1,
    21102,
    405,
    1,
    0,
    1105,
    1,
    451,
    21102,
    3375459559,
    1,
    1,
    21101,
    0,
    416,
    0,
    1106,
    0,
    451,
    3,
    10,
    104,
    0,
    104,
    0,
    3,
    10,
    104,
    0,
    104,
    0,
    21102,
    838433665892,
    1,
    1,
    21102,
    1,
    439,
    0,
    1106,
    0,
    451,
    21102,
    988669698816,
    1,
    1,
    21102,
    450,
    1,
    0,
    1105,
    1,
    451,
    99,
    109,
    2,
    21201,
    -1,
    0,
    1,
    21102,
    1,
    40,
    2,
    21101,
    482,
    0,
    3,
    21102,
    472,
    1,
    0,
    1105,
    1,
    515,
    109,
    -2,
    2105,
    1,
    0,
    0,
    1,
    0,
    0,
    1,
    109,
    2,
    3,
    10,
    204,
    -1,
    1001,
    477,
    478,
    493,
    4,
    0,
    1001,
    477,
    1,
    477,
    108,
    4,
    477,
    10,
    1006,
    10,
    509,
    1101,
    0,
    0,
    477,
    109,
    -2,
    2105,
    1,
    0,
    0,
    109,
    4,
    1201,
    -1,
    0,
    514,
    1207,
    -3,
    0,
    10,
    1006,
    10,
    532,
    21101,
    0,
    0,
    -3,
    22102,
    1,
    -3,
    1,
    21201,
    -2,
    0,
    2,
    21102,
    1,
    1,
    3,
    21101,
    551,
    0,
    0,
    1106,
    0,
    556,
    109,
    -4,
    2105,
    1,
    0,
    109,
    5,
    1207,
    -3,
    1,
    10,
    1006,
    10,
    579,
    2207,
    -4,
    -2,
    10,
    1006,
    10,
    579,
    21201,
    -4,
    0,
    -4,
    1105,
    1,
    647,
    21201,
    -4,
    0,
    1,
    21201,
    -3,
    -1,
    2,
    21202,
    -2,
    2,
    3,
    21101,
    0,
    598,
    0,
    1106,
    0,
    556,
    21202,
    1,
    1,
    -4,
    21102,
    1,
    1,
    -1,
    2207,
    -4,
    -2,
    10,
    1006,
    10,
    617,
    21102,
    0,
    1,
    -1,
    22202,
    -2,
    -1,
    -2,
    2107,
    0,
    -3,
    10,
    1006,
    10,
    639,
    22102,
    1,
    -1,
    1,
    21101,
    0,
    639,
    0,
    106,
    0,
    514,
    21202,
    -2,
    -1,
    -2,
    22201,
    -4,
    -2,
    -4,
    109,
    -5,
    2105,
    1,
    0
).toBigIntegerMutableList()