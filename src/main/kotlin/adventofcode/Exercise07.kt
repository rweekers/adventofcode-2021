package adventofcode

import java.time.Instant
import kotlin.math.absoluteValue

class Exercise07(input: String) {

    private val inputList: List<Int> = commaSeparatedLineAsListOfInts(input)

    fun solve(fuelCost: (Int) -> Int): Int {
        val min = inputList.minOrNull() ?: throw IllegalArgumentException("Excepting at least one element!")
        val max = inputList.maxOrNull() ?: throw IllegalArgumentException("Excepting at least one element!")

        return (min..max).map { i ->
            i to inputList.sumOf { fuelCost((it - i).absoluteValue) }
        }.minByOrNull { it.second }?.second ?: throw IllegalStateException("Excepting to find one result")
    }
}

fun main() {
    val exc7 = Exercise07("/input07.txt")
    val start = Instant.now().toEpochMilli()
    println("The answer to the silver exercise is ${exc7.solve { it }}")
    val afterSilver = Instant.now().toEpochMilli()
    println("The answer to the gold exercise is ${exc7.solve { (it.times(it.plus(1))).div(2) }}ms")
    val end = Instant.now().toEpochMilli()
    println("Silver took ${afterSilver - start}ms and gold ${end - afterSilver}")
}

