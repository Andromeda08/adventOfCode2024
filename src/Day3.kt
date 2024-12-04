val input = Input("day3.txt").readLines().joinToString()

fun day3part1(): Int {
    val regex = "mul\\(\\d*?,\\d*?\\)".toRegex()
    return regex.findAll(input).toList()
        .map { it.value }
        .map { it.drop(4).takeWhile { i -> i != ')' }.split(',').map { j -> j.toInt() } }
        .sumOf { it[0] * it[1]}
}

fun day3part2(): Int {
    val regex = "mul\\(\\d*?,\\d*?\\)|do\\(\\)|don't\\(\\)".toRegex()

    var enabled = true
    var sum = 0

    regex.findAll(input).toList()
        .map { it.value }
        .forEach {
            when (it) {
                "do()" -> enabled = true
                "don't()" -> enabled = false
                else -> sum += if (enabled) {
                    val nums = it.drop(4).takeWhile { i -> i != ')' }.split(',').map { j -> j.toInt() }
                    nums[0] * nums[1]
                } else 0
            }
        }

    return sum
}

fun main() {
    AoC.run(3, ::day3part1, ::day3part2);
}