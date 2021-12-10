package adventofcode

import java.util.*

class Exercise10(input: String) {

    private sealed interface ParseResult
    private class Corrupted(val corruptSymbol: Symbol) : ParseResult {
        fun calculateScore(): Long =
            corruptedValues.getValue(corruptSymbol)
    }
    private class Incomplete(val completionSymbols: List<Symbol>) : ParseResult {
        fun calculateScore(): Long =
            completionSymbols
                .fold(0) { carry, symbol ->
                    (carry * 5) + completionValues.getValue(symbol)
                }
    }

    private val input: List<List<Char>> = resourceAsListOfString(input).map { it.toList() }

    fun silverExercise(): Long {
        return input.map { parseLine(it) }
            .filterIsInstance<Corrupted>()
            .sumOf { it.calculateScore() }

    }

    fun goldExercise(): Long {
        val closingCharacters = input.map { parseLine(it) }
            .filterIsInstance<Incomplete>()
            .map { it.calculateScore() }
            .sorted()
        return closingCharacters[closingCharacters.lastIndex / 2]
    }

    private fun parseLine(charList: List<Char>): ParseResult {
        val stack = Stack<Symbol>()

        charList.map { Symbol.findBySymbolChar(it) }.forEach { symbol ->
            when {
                !symbol.closing -> stack.push(symbol.adversary())
                stack.pop().family != symbol.family -> return Corrupted(symbol)
            }
        }
        return Incomplete(stack.asReversed())
    }

    private enum class Symbol(
        val closing: Boolean,
        val family: SymbolFamily,
        val symbol: Char,
        val adversary: Char
    ) {
        OPENING_BRACKET(false, SymbolFamily.BRACKETS, '[', ']'),
        CLOSING_BRACKET(true, SymbolFamily.BRACKETS, ']', '['),
        OPENING_CURLY_BRACE(false, SymbolFamily.CURLY_BRACES, '{', '}'),
        CLOSING_CURLY_BRACE(true, SymbolFamily.CURLY_BRACES, '}', '{'),
        OPENING_PARENTHESES(false, SymbolFamily.PARENTHESES, '(', ')'),
        CLOSING_PARENTHESES(true, SymbolFamily.PARENTHESES, ')', '('),
        OPENING_ARROW(false, SymbolFamily.ARROWS, '<', '>'),
        CLOSING_ARROW(true, SymbolFamily.ARROWS, '>', '<');

        fun adversary(): Symbol {
            return findBySymbolChar(this.adversary)
        }

        companion object {
            fun findBySymbolChar(symbolChar: Char): Symbol =
                values().first { it.symbol == symbolChar }
        }
    }

    private enum class SymbolFamily {
        BRACKETS,
        CURLY_BRACES,
        PARENTHESES,
        ARROWS
    }

    companion object {
        private val corruptedValues = mapOf(
            Symbol.CLOSING_PARENTHESES to 3L,
            Symbol.CLOSING_BRACKET to 57L,
            Symbol.CLOSING_CURLY_BRACE to 1197L,
            Symbol.CLOSING_ARROW to 25137L
        )
        private val completionValues = mapOf(
            Symbol.CLOSING_PARENTHESES to 1L,
            Symbol.CLOSING_BRACKET to 2L,
            Symbol.CLOSING_CURLY_BRACE to 3L,
            Symbol.CLOSING_ARROW to 4L
        )
    }
}

fun main() {
    val exc10 = Exercise10("/input10.txt")
    println("The answer to the silver exercise is ${exc10.silverExercise()}")
    println("The answer to the gold exercise is ${exc10.goldExercise()}")
}

