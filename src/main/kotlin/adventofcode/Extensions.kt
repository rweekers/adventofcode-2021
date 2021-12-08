package adventofcode

import kotlin.math.pow

fun <T> List<T>.innerJoin(otherList: List<T>): List<T> {
    return this.filter { otherList.contains(it) }
}

fun List<String>.concat() = this.joinToString("") { it }.takeWhile { it.isDigit() }

fun Int.toListOfInt(): List<Int> {
    return this.toString().map { it.toString().toInt() }
}

fun Int.takeLastN(n: Int): Int {
    return this.toString().takeLast(n).toInt()
}

fun <T> List<T>.mostCommon(comparator: Comparator<T>): T =
    this.groupingBy { it }.eachCount().toSortedMap(comparator).maxByOrNull { it.value }?.key
        ?: throw IllegalArgumentException()

fun <T> List<T>.leastCommon(comparator: Comparator<T>): T =
    this.groupingBy { it }.eachCount().toSortedMap(comparator).minByOrNull { it.value }?.key
        ?: throw IllegalArgumentException()

fun Int.dropLastN(n: Int): Int {
    return this.toString().dropLast(n).toInt()
}

fun Int.parameterMode(position: Int): Int {
    return this / (10.0.pow(position).toInt()) % 10
}

fun String.commaSeparatedToListOfInt(): List<Int> {
    return this.split(",").map { it.toInt() }
}

fun String.toListOfInt(): List<Int> {
    return this.toCharArray().map { it.toString().toInt() }
}

fun String.toListOfSingleStrings(): List<String> {
    return this.toCharArray().map { it.toString() }
}

fun String.toSetOfSingleStrings(): Set<String> {
    return this.toListOfSingleStrings().toSet()
}

fun Int.greatestCommonDivider(otherInt: Int): Int {
    var n1 = this
    var n2 = otherInt
    while (n1 != n2) {
        if (n1 > n2)
            n1 -= n2
        else
            n2 -= n1
    }
    return n1
}

fun <T> List<T>.everyPair(): List<Pair<T, T>> =
    this.mapIndexed { idx, left ->
        this.drop(idx + 1).map { right ->
            Pair(left, right)
        }
    }.flatten()