package adventofcode


internal object Utils

fun resourceAsListOfString(fileName: String): List<String> =
    Utils.javaClass.getResource(fileName).openStream().bufferedReader()
        .readLines()

fun resourceAsListOfLong(fileName: String): List<Long> =
    resourceAsListOfString(fileName).map { it.toLong() }

fun commaSeparatedLineAsListOfInts(fileName: String): List<Int> =
    Utils.javaClass.getResource(fileName).openStream().bufferedReader().readLine().commaSeparatedToListOfInt()

fun lineAsListOfInts(fileName: String): List<Int> =
    Utils.javaClass.getResource(fileName).openStream().bufferedReader().readLine().toListOfInt()

fun lineAsListOfStrings(fileName: String, index: Int): List<String> =
    Utils.javaClass.getResource(fileName).openStream().bufferedReader().readLines()[index].split(",")

fun gcd(a: Long, b: Long): Long = if (b == 0L) a else gcd(b, a % b)
fun lcm(a: Long, b: Long): Long = a / gcd(a, b) * b

fun <T> transpose(original: List<List<T>>): List<List<T>> {
    val transposed: MutableList<List<T>> = mutableListOf()
    val newNumberOfRows: Int = original[0].size
    for (i in 0 until newNumberOfRows) {
        val col: MutableList<T> = mutableListOf()
        for (originalRow in original) {
            col.add(originalRow[i])
        }
        transposed.add(col)
    }
    return transposed
}