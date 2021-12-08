package adventofcode

class Exercise08(input: String) {

    private val inputList: List<String> = resourceAsListOfString(input)

    fun silverExercise(): Int {
        val digitsToInclude = listOf('1', '4', '7', '8')
        return inputList
            .map { decode(it) }
            .sumOf { it.toString().count { d -> digitsToInclude.contains(d) } }
    }

    fun goldExercise(): Int {
        return inputList.sumOf { decode(it) }
    }

    private fun getDigits(digitString: List<Set<String>>): Map<Set<String>, String> {
        val one = digitString.first { it.size == 2 }
        val four = digitString.first { it.size == 4 }
        val seven = digitString.first { it.size == 3 }
        val eight = digitString.first { it.size == 7 }
        val fiveSegmentBars = digitString.filter { it.size == 5 }
        val sixSegmentBars = digitString.filter { it.size == 6 }

        val six = sixSegmentBars.first { !it.containsAll(seven) }
        val five = fiveSegmentBars.first { six.containsAll(it) }
        val three = fiveSegmentBars.first { it.containsAll(one) }
        val two = fiveSegmentBars.first { !it.containsAll(five) && !it.containsAll(three) }

        val nine = sixSegmentBars.first { it.containsAll(three) }
        val zero = sixSegmentBars.first { !it.containsAll(six) && !it.containsAll(nine) }

        return mapOf(
            zero to "0",
            one to "1",
            two to "2",
            three to "3",
            four to "4",
            five to "5",
            six to "6",
            seven to "7",
            eight to "8",
            nine to "9"
        )
    }

    private fun decode(input: String): Int {
        val (digits, displayDigits) = input.split("|")
            .map { digitString -> digitString.trim().split(" ").map(String::toSetOfSingleStrings) }
        return displayDigits.map { getDigits(digits).getValue(it) }.concat().toInt()
    }
}

fun main() {
    val exc8 = Exercise08("/input08.txt")
    println("The answer to the silver exercise is ${exc8.silverExercise()}")
    println("The answer to the gold exercise is ${exc8.goldExercise()}")
}

