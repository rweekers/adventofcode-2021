package adventofcode

class Exercise02(input: String) {

    private val positionChanges = resourceAsListOfString(input)
        .map { it.split(" ") }
        .map { (a, b) ->
            PositionChange(
                when (a) {
                    "forward" -> Direction.HORIZONTAL
                    "up" -> Direction.UP
                    "down" -> Direction.DOWN
                    else -> throw IllegalArgumentException()
                }, b.toInt()
            )
        }

    fun silverExercise(): Int {
        return positionChanges
            .fold(NauticalPosition()) { acc, curr -> acc.applyPositionChangeSilver(curr) }
            .positionMultiplication()
    }

    fun goldExercise(): Int {
        return positionChanges
            .fold(NauticalPosition()) { acc, curr -> acc.applyPositionChangeGold(curr) }
            .positionMultiplication()
    }

    private data class NauticalPosition(val horizontal: Int = 0, val depth: Int = 0, val aim: Int = 0) {
        fun applyPositionChangeSilver(positionChange: PositionChange): NauticalPosition {
            return when (positionChange.direction) {
                Direction.HORIZONTAL -> NauticalPosition(this.horizontal.plus(positionChange.steps), this.depth)
                Direction.UP -> NauticalPosition(this.horizontal, this.depth.minus(positionChange.steps))
                Direction.DOWN -> NauticalPosition(this.horizontal, this.depth.plus(positionChange.steps))
            }
        }

        fun applyPositionChangeGold(positionChange: PositionChange): NauticalPosition {
            return when (positionChange.direction) {
                Direction.HORIZONTAL -> NauticalPosition(
                    this.horizontal.plus(positionChange.steps),
                    this.depth.plus(positionChange.steps.times(this.aim)),
                    this.aim
                )
                Direction.UP -> NauticalPosition(this.horizontal, this.depth, this.aim.minus(positionChange.steps))
                Direction.DOWN -> NauticalPosition(this.horizontal, this.depth, this.aim.plus(positionChange.steps))
            }
        }

        fun positionMultiplication(): Int =
            horizontal * depth
    }

    private data class PositionChange(val direction: Direction, val steps: Int)

    private enum class Direction {
        HORIZONTAL,
        UP,
        DOWN
    }
}

fun main() {
    val exc2 = Exercise02("/input02.txt")
    println("The answer to the silver exercise is ${exc2.silverExercise()}")
    println("The answer to the gold exercise is ${exc2.goldExercise()}")
}
