class Day5 {
    private val input = Input("day5.txt").readLines()

    private val rules = mutableListOf<Pair<Int, Int>>()
    init {
        input.subList(0, input.indexOf("")).forEach {
            val nums = it.split('|').map { x -> x.toInt() }
            rules.add(nums[0] to nums[1])
        }
    }

    private val lists = input.subList(input.indexOf("") + 1, input.size).map {
        it.split(',').map { list -> list.toInt() }.toList()
    }

    private fun List<Int>.isValid(): Boolean {
        for (i in 0 .. lastIndex) {
            for (j in i + 1 .. this.lastIndex) {
                if (rules.contains(this[j] to this[i])) {
                    return false
                }
            }
        }
        return true
    }

    fun part1(): Int {
        return lists
            .filter { list -> list.isValid() }
            .sumOf { it[it.size / 2] }
    }

    fun part2(): Int {
        return lists
            .filter { list -> !list.isValid() }
            .map { list ->
                list.sortedWith { o1, o2 ->
                    if (rules.contains(o1 to o2)) -1 else 1
                }
            }
            .sumOf { it[it.size / 2] }
    }
}

fun main() {
    val day5 = Day5()
    println("Day 5\nPart 1: ${day5.part1()}\nPart 2: ${day5.part2()}")
}