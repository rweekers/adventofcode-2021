package adventofcode


data class Grid<T>(val rawInput: List<List<T>>) {
    val points = rawInput.indices.flatMap { y -> rawInput.first().indices.map { x -> Point(x, y) } }

    fun getNeighbours(point: Point): List<Point> {
        val (x, y) = point
        val left = if (x == 0) null else Point(x - 1, y)
        val top = if (y == 0) null else Point(x, y - 1)
        val right = if (x == rawInput.first().lastIndex) null else Point(x + 1, y)
        val bottom = if (y == rawInput.lastIndex) null else Point(x, y + 1)
        return listOfNotNull(left, top, right, bottom)
    }
}