import kotlin.math.abs

var columns = Input("day1.txt").readLines()
    .map { it.split("\\s+".toRegex()).map{ str -> str.toInt() } }
    .transpose()
    .map { it.sorted() }

fun part1(): Int {
    return columns[0].zip(columns[1]).sumOf { abs(it.first - it.second) }
}

fun part2(): Int {
    return columns[0].map { a -> a to (columns[1].count { b -> b == a }) }.sumOf { it.first * it.second }
}

fun main() {
    AoC.run(1, ::part1, ::part2);
}