fun <T> List<List<T>>.transpose(): List<List<T>> {
    return (this[0].indices).map { i -> (this.indices).map { j -> this[j][i] } }
}

class AoC {
    companion object {
        fun run(day: Int, part1: (() -> Int)? = null, part2: (() -> Int)? = null): Unit {
            val value1 = if (part1 != null) part1() else "No solution"
            val value2 = if (part2 != null) part2() else "No solution"
            println("[Day $day] Part 1: $value1, Part 2: $value2")
        }
    }
}