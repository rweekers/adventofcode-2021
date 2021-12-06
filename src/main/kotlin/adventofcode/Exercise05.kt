package adventofcode

class Exercise05(input: String) {

    private val inputList: List<String> = resourceAsListOfString(input)
    private val traversions = inputList.map {
        it.split(" -> ").map { c ->
            c.split(",").map { p ->
                p.toInt()
            }
        }
    }.map { Pair(Point(it[0][0], it[0][1]), Point(it[1][0], it[1][1])) }

    fun solve(predicate: (Pair<Point, Point>) -> Boolean): Int {
        return traversions
            .filter { predicate(it) }
            .flatMap { it.first.lineTo(it.second) }
            .groupingBy { it }.eachCount()
            .count { it.value > 1 }
    }
}

fun main() {
    val exc5 = Exercise05("/input05.txt")
    println("The answer to the silver exercise is ${exc5.solve { it.first.isVerticalOrHorizontalWith(it.second) }}")
    // Probably all input is straight or diagonal, but let's filter anyway
    println("The answer to the gold exercise is ${exc5.solve { it.first.isVerticalOrHorizontalWith(it.second) || 
            it.first.isDiagonalTo(it.second) }}")
}

