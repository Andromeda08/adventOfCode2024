import kotlin.math.abs

val reports = Input("day2.txt").readLines()
    .map { line ->
        line.split(' ')
            .map { it.toInt() }
    }

fun isSafe(report: List<Int>): Boolean {
    val differences = report.windowed(2).map { it[0] - it[1] }
    return differences.all { abs(it) in 1..3 }
            && (differences.map { it >= 0 }.toSet().size == 1)
}

fun day2part1(): Int {
    return reports.count { isSafe(it) }
}

fun tolerantIsSafe(report: List<Int>): Boolean {
    return report.indices
        .map { i -> report.filterIndexed { idx, _ -> i != idx } }
        .map { isSafe(it) }
        .any { it }
}

fun day2part2(): Int {
    return reports.count { tolerantIsSafe(it) }
}

fun main() {
    AoC.run(2, ::day2part1, ::day2part2)
}