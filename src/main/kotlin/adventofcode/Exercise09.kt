package adventofcode

class Exercise09(input: String) {

    private val grid: Grid<Int> = Grid(resourceAsListOfString(input).map { it.toListOfInt() })

    fun silverExercise(): Int {
        return grid.points.filter { grid.isLow(it) }.sumOf { grid.heightForPoint(it) + 1 }
    }

    fun goldExercise(): Int {
        return grid.points.asSequence().filter { grid.isLow(it) }.map { getPointsInBasinFor(it) }
            .sortedByDescending { it.size }.take(3).fold(1) { acc, points -> acc * points.size }
    }

    private fun Grid<Int>.heightForPoint(point: Point): Int {
        return this.rawInput[point.y][point.x]
    }

    private fun Grid<Int>.isLow(point: Point): Boolean {
        return this.getNeighbours(point)
            .all { neighbour -> this.heightForPoint(neighbour) > this.heightForPoint(point) }
    }

    private fun getPointsInBasinFor(point: Point): Set<Point> {
        val visited = mutableSetOf(point)
        val pointsInBasin = mutableSetOf(point)

        while (pointsInBasin.isNotEmpty()) {
            val p = pointsInBasin.first()
            pointsInBasin.remove(p)
            if (grid.heightForPoint(p) < 9) {
                grid.getNeighbours(p).let { n ->
                    pointsInBasin.addAll(n.filter { !visited.contains(it) })
                    visited.addAll(n)
                }
            }
        }
        return visited.filter { grid.heightForPoint(it) < 9 }.toSet()
    }
}

fun main() {
    val exc9 = Exercise09("/input09.txt")
    println("The answer to the silver exercise is ${exc9.silverExercise()}")
    println("The answer to the gold exercise is ${exc9.goldExercise()}")
}

