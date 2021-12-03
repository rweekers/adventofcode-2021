package adventofcode

class Exercise01(input: String) {

    private val inputList: List<Long> = resourceAsListOfLong(input)

    fun silverExercise(): Int {
        return inputList
            .zipWithNext()
            .count { (a, b) -> a < b }
    }

    fun goldExercise(): Int {
        return inputList
            .windowed(3) { it.sum() }
            .zipWithNext()
            .count { (a, b) -> a < b }
    }
}

fun main() {
    val exc1 = Exercise01("/input01.txt")
    println("The answer to the silver exercise is ${exc1.silverExercise()}")
    println("The answer to the gold exercise is ${exc1.goldExercise()}")
}
