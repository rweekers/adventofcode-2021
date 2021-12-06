package adventofcode

class Exercise04(input: String) {

    private val inputList: List<String> = resourceAsListOfString(input)
    private val bingoNumbers = inputList.first().split(",").map { it.toInt() }
    private var bingoBoards: List<BingoBoard>

    init {
        val cardsRaw = inputList.drop(2)
        val chunkSize = cardsRaw.indexOf("")
        bingoBoards = cardsRaw.filter { it.isNotBlank() }.chunked(chunkSize).map { board ->
            board.map {
                it.trim().split(Regex(" +")).map { numberString -> BingoCardNumber(numberString.toInt()) }
            }
        }.map { BingoBoard(it) }
    }

    fun silverExercise(): Int {
        bingoNumbers.forEach { n -> bingoBoards.map { it.drawNumber(n) } }
        for (draw in bingoNumbers) {
            bingoBoards = bingoBoards.map { it.drawNumber(draw) }
            if (bingoBoards.any { it.hasBingo() }) {
                return draw.times(bingoBoards.first { it.hasBingo() }.winningScore())
            }
        }
        return 0
    }

    fun goldExercise(): Int {
        bingoNumbers.forEach { n -> bingoBoards.map { it.drawNumber(n) } }
        for (element in bingoNumbers) {
            if (bingoBoards.size == 1) {
                bingoBoards = bingoBoards.map { it.drawNumber(element) }
                if (bingoBoards.first().hasBingo()) {
                    return element.times(bingoBoards.first { it.hasBingo() }.winningScore())
                }
            } else {
                bingoBoards = bingoBoards.map { it.drawNumber(element) }.filter { !it.hasBingo() }
            }
        }
        return 0
    }

    private data class BingoBoard(val numbers: List<List<BingoCardNumber>>) {
        fun drawNumber(cardNumber: Int): BingoBoard {
            return BingoBoard(numbers.map { row ->
                row.map {
                    if (it.number == cardNumber) BingoCardNumber(
                        it.number, true
                    ) else it
                }
            })
        }

        fun hasBingo(): Boolean {
            return numbers.any { it.all { card -> card.drawn } }
                    || transpose(numbers).any { it.all { card -> card.drawn } }
        }

        fun winningScore(): Int {
            return numbers
                .sumOf { it.filter { card -> !card.drawn }.sumOf { card -> card.number } }
        }
    }

    private data class BingoCardNumber(val number: Int, val drawn: Boolean = false)
}

fun main() {
    val exc4 = Exercise04("/input04.txt")
    println("The answer to the silver exercise is ${exc4.silverExercise()}")
    println("The answer to the gold exercise is ${exc4.goldExercise()}")
}

