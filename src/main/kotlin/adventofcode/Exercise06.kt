package adventofcode

class Exercise06(input: String) {

    private val inputList: List<Int> = commaSeparatedLineAsListOfInts(input)

    fun solve(totalDays: Int): Long {
        val countsPerTimerDay = inputList.groupBy { it }.mapValues { it.value.count().toLong() }
        return (0 until totalDays).fold(countsPerTimerDay) { acc, _ ->
            (0..7)
                .associateWith { acc.getOrDefault(it + 1, 0) }
                .plus(6 to acc.getOrDefault(7, 0) + acc.getOrDefault(0, 0))
                .plus(8 to acc.getOrDefault(0, 0))
        }.values.sum()
    }
}

fun main() {
    val exc6 = Exercise06("/input06.txt")
    println("The answer to the silver exercise is ${exc6.solve(80)}")
    println("The answer to the gold exercise is ${exc6.solve(256)}")
}

