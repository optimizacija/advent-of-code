package day11.IntCode

enum class IntOpCode(val value: Int, val length: Int) {
    Invalid(-1, -1),
    Add(1, 4),
    Mul(2, 4),
    Input(3, 2),
    Output(4, 2),
    JumpNonZero(5, 3),
    JumpZero(6, 3),
    LessThan(7, 4),
    Equals(8, 4),
    AdjustRelativeBase(9, 2),
    Halt(99, 1);

    companion object {
        fun fromInt(value: Int): IntOpCode {
            return values().find { it.value == value }!!
        }
    }
}

