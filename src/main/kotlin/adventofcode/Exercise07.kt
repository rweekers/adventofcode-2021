package adventofcode

import java.time.Instant
import kotlin.math.absoluteValue

class Exercise07(input: String) {

    private val inputList: List<Int> = commaSeparatedLineAsListOfInts(input)
    private val fuelMap: MutableMap<Int, Int> = hashMapOf();

    fun solve(gold: Boolean = false): Int {
        val min = inputList.minOrNull() ?: throw IllegalArgumentException("Excepting at least one element!")
        val max = inputList.maxOrNull() ?: throw IllegalArgumentException("Excepting at least one element!")

        return (min..max).map { i ->
            i to inputList.sumOf { calculateFuelSumUntil(gold, it, i) }
        }.minByOrNull { it.second }?.second ?: throw IllegalStateException("Excepting to find one result")
    }

    private fun calculateFuelSumUntil(gold: Boolean, s: Int, t: Int): Int =
        if (gold) {
            val steps = (s - t).absoluteValue
            fuelMap.computeIfAbsent(steps) { (0..steps).fold(0) { acc, curr -> acc + curr } }
        } else {
            (s - t).absoluteValue
        }
}

fun main() {
    val exc7 = Exercise07("/input07.txt")
    val start = Instant.now().toEpochMilli()
    println("The answer to the silver exercise is ${exc7.solve()}")
    val afterSilver = Instant.now().toEpochMilli()
    println("The answer to the gold exercise is ${exc7.solve(true)}")
    val end = Instant.now().toEpochMilli()
    println("Silver took ${afterSilver - start}ms and gold ${end - afterSilver}ms")
}

