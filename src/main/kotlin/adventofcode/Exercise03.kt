package adventofcode

class Exercise03(input: String) {

    private val inputList: List<List<String>> = resourceAsListOfString(input).map { it.toListOfSingleStrings() }
    private val transposedInputList: List<List<String>> = transpose(inputList)

    fun silverExercise(): Int {
        val gammaRate = convertToRating(transposedInputList
            .map { it.mostCommon { x, y -> x.toInt().compareTo(y.toInt()) } }
        )
        val epsilonRate = convertToRating(transposedInputList
            .map { it.leastCommon { x, y -> x.toInt().compareTo(y.toInt()) } }
        )
        return gammaRate.times(epsilonRate)
    }

    fun goldExercise(): Int {
        val oxygenGeneratorRating = convertToRating(determineRatingList(inputList).first())
        val co2ScrubberRating = convertToRating(determineRatingList(inputList, 0, false).first())
        return oxygenGeneratorRating.times(co2ScrubberRating)
    }

    private fun convertToRating(list: List<String>): Int {
        return list.fold(StringBuilder()) { acc, curr -> acc.append(curr) }
            .toString().toInt(2)
    }

    private fun test(input: List<List<String>>): List<List<String>> {
        (0 until input.first().size).fold(listOf<String>()) { acc, _ ->
            acc
        }
        return input
    }

    private tailrec fun determineRatingList(input: List<List<String>>, counter: Int = 0, mostCommon: Boolean = true): List<List<String>> {
        val check = transpose(input)
        val filterValue = if (mostCommon) check[counter].mostCommon { x, y ->
            y.toInt().compareTo(x.toInt())
        } else check[counter].leastCommon { x, y -> x.toInt().compareTo(y.toInt()) }
        val newList = input.filter { it[counter] == filterValue }
        val newCounter = counter + 1
        return if (newCounter == input.first().size) newList else determineRatingList(newList, newCounter, mostCommon)
    }
}

fun main() {
    val exc3 = Exercise03("/input03.txt")
    println("The answer to the silver exercise is ${exc3.silverExercise()}")
    println("The answer to the gold exercise is ${exc3.goldExercise()}")
}

