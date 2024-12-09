import kotlin.math.abs

class Day6 {
    private var initialGuard = Guard(Pos(-1, -1))
    private val input = Input("day6.txt").readLines()
        .map { it.toList() }
        .mapIndexed { y, list ->
            list.mapIndexed { x, ch ->
                if (ch == '^') {
                    initialGuard = Guard(Pos(x, y))
                    '.'
                } else {
                    ch
                }
            }
        }

    data class Pos(var x: Int, var y: Int)

    class Guard(var pos: Pos) {
        private var exited = false
        private var orientation = 0

        val path = mutableListOf<Pos>()

        fun step(field: List<List<Char>>): Boolean{
            if (exited) {
                return false
            }

            var hitPos = Pos(-1, -1)
            when (orientation) {
                0 -> {
                    var i = pos.y
                    try {
                        while (field[i][pos.x] != '#') {
                            path.add(Pos(pos.x, i))
                            i--
                        }
                    } catch (e: IndexOutOfBoundsException) {
                        exited = true
                    }
                    pos.y = (i + 1)
                }
                1 -> {
                    var i = pos.x
                    try {
                        while (field[pos.y][i] != '#') {
                            path.add(Pos(i, pos.y))
                            i++
                        }
                    } catch (e: IndexOutOfBoundsException) {
                        exited = true
                    }
                    pos.x = (i - 1)
                }
                2 -> {
                    var i = pos.y
                    try {
                        while (field[i][pos.x] != '#') {
                            path.add(Pos(pos.x, i))
                            i++
                        }
                    } catch (e: IndexOutOfBoundsException) {
                        exited = true
                    }
                    pos.y = (i - 1)
                }
                3 -> {
                    var i = pos.x
                    try {
                        while (field[pos.y][i] != '#') {
                            path.add(Pos(i, pos.y))
                            i--
                        }
                    } catch (e: IndexOutOfBoundsException) {
                        exited = true
                    }
                    pos.x = (i + 1)
                }
            }

            orientation = (orientation + 1) % 4
            return true
        }
    }

    fun part1(): Int {
        val guard = Guard(initialGuard.pos)

        var step: Boolean
        do {
            step = guard.step(input)
        } while (step)

        return guard.path.toSet().size
    }
}

fun main() {
    val day6 = Day6()
    println("Day 6\nPart 1: ${day6.part1()}")
}