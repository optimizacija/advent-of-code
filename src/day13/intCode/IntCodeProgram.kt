package day13.intCode

import day13.toBigIntegerMutableList
import day13.toDigits
import java.math.BigInteger

class IntCodeProgram {
    var intCode: MutableList<BigInteger>
        private set

    var inputFun: () -> BigInteger
    var outputFun: (BigInteger) -> Unit

    private var opCode: IntOpCode = IntOpCode.Invalid
    private val modes = arrayOf(0, 0, 0)
    private val args = arrayOf(0, 0, 0).toBigIntegerMutableList()
    private var jumped = false
    private var instructionPointer = 0
    private var relativeBase = 0

    constructor(
        code: MutableList<BigInteger>,
        inputFunCallback: () -> BigInteger = {error("input function callback not provided")},
        outputFunCallback: (BigInteger) -> Unit = {error("output function callback not provided")}
    ) {
        intCode = MutableList(100000) { 0.toBigInteger() }
        for (i in code.indices) {
            intCode[i] = code[i]
        }
        inputFun = inputFunCallback
        outputFun = outputFunCallback
    }

    fun run() {
        while (step()) {
        }
    }

    fun step(): Boolean {
        readModeCode()
        if (shouldHalt()) return false
        readArgs()
        // debugPrint()
        executeCommand()
        updateInstructionPointer()
        return true
    }

    private fun debugPrint() {
        print("${opCode.name}: ")
        for (i in 0..(opCode.length - 2)) {
            val value = read(i)
            print("$value ")
        }
        println()
    }

    fun runToOpCode(intOpCode: IntOpCode): Boolean {
        // returns false if halted before getting to output
        do {
            if (!step()) return false
        } while (opCode != intOpCode)
        return true
    }

    fun runToOpCodes(intOpCodes: Array<IntOpCode>): Boolean {
        // returns false if halted before getting to output
        do {
            if (!step()) return false
        } while (intOpCodes.all { it != opCode })
        return true
    }

    fun runUpToOpCode(intOpCode: IntOpCode): Boolean {
        // returns false if halted before getting to output
        while (intOpCode != getNextIntOpCode()) {
            if (!step()) return false
        }
        return true
    }

    fun runUpToOpCodes(intOpCodes: Array<IntOpCode>): Boolean {
        // returns false if halted before getting to output
        while (intOpCodes.all { it != getNextIntOpCode() }) {
            if (!step()) return false
        }
        return true
    }

    private fun readModeCode() {
        val digits = intCode[instructionPointer].toDigits(5)

        // opCode
        val opDigits = digits.takeLast(2)
        opCode = IntOpCode.fromInt(opDigits[0] * 10 + opDigits[1])

        // read arg modes
        val modeDigits = digits.dropLast(2).reversed()
        for (i in modeDigits.indices) {
            modes[i] = modeDigits[i]
        }
    }

    private fun getNextIntOpCode(): IntOpCode {
        return IntOpCode.fromInt(intCode[instructionPointer].toString().takeLast(2).toInt())
    }

    private fun shouldHalt(): Boolean {
        return opCode == IntOpCode.Halt
    }

    private fun readArgs() {
        for (i in 0..(opCode.length - 2)) {
            args[i] = intCode[instructionPointer + i + 1]
        }
    }

    private fun read(argIndex: Int): BigInteger {
        return when (modes[argIndex]) {
            // position
            0 -> intCode[args[argIndex].toInt()]
            // immediate
            1 -> args[argIndex]
            // relative
            2 -> intCode[relativeBase + args[argIndex].toInt()]
            else -> throw Exception("Invalid mode ${modes[argIndex]}")
        }
    }

    private fun write(value: BigInteger) {
        val lastIndex = opCode.length - 2
        val index = args[lastIndex].toInt()
        when (modes[lastIndex]) {
            0 -> intCode[index] = value
            1 -> error("write is not supported in immediate mode (1)")
            2 -> intCode[relativeBase + index] = value
            else -> error("unknown mode ${modes[lastIndex]}")
        }

    }

    private fun updateInstructionPointer() {
        if (jumped)
            jumped = false
        else
            instructionPointer += opCode.length
    }

    private fun executeCommand() {
        when (opCode) {
            IntOpCode.Invalid -> error("tried executing IntOpCode.Invalid")
            IntOpCode.Add -> execAdd()
            IntOpCode.Mul -> execMul()
            IntOpCode.Input -> execInput()
            IntOpCode.Output -> execOutput()
            IntOpCode.JumpNonZero -> execJumpNonZero()
            IntOpCode.JumpZero -> execJumpZero()
            IntOpCode.LessThan -> execLessThan()
            IntOpCode.Equals -> execEquals()
            IntOpCode.AdjustRelativeBase -> execAdjustRelativeBase()
            IntOpCode.Halt -> return
        }
    }

    private fun execAdd() {
        val val1 = read(0)
        val val2 = read(1)

        write(val1 + val2)
    }

    private fun execMul() {
        val val1 = read(0)
        val val2 = read(1)

        write(val1 * val2)
    }

    private fun execInput() {
        write(inputFun())
    }

    private fun execOutput() {
        outputFun(read(0))
    }

    private fun execJumpNonZero() {
        val val1 = read(0)

        if (val1 != 0.toBigInteger()) {
            instructionPointer = read(1).toInt()
            jumped = true
        }
    }

    private fun execJumpZero() {
        val val1 = read(0)

        if (val1 == 0.toBigInteger()) {
            instructionPointer = read(1).toInt()
            jumped = true
        }
    }

    private fun execLessThan() {
        val val1 = read(0)
        val val2 = read(1)

        if (val1 < val2) {
            write(1.toBigInteger())
        } else {
            write(0.toBigInteger())
        }
    }

    private fun execEquals() {
        val val1 = read(0)
        val val2 = read(1)

        if (val1 == val2) {
            write(1.toBigInteger())
        } else {
            write(0.toBigInteger())
        }
    }

    private fun execAdjustRelativeBase() {
        relativeBase += read(0).toInt()
    }
}
