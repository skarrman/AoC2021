import java.io.File
import java.lang.Integer.min
import kotlin.math.abs

fun main() {
    val input = File("input.txt").readText().split(",").map { it.toInt() }
    val answer = (0..input.maxOf { it }).fold(Int.MAX_VALUE) { m, i ->
        min(m, input.fold(0) { s, p ->
            s + when (System.getenv("part")) {
                "part2" -> (abs(i - p) * (abs(i - p) + 1)) / 2
                else -> abs(i - p)
            }
        })
    }
    println(answer)
}
