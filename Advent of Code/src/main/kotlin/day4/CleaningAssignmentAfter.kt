package day4

class CleaningAssignmentAfter(private val input: List<String>) {

    fun countAssignmentsTotallyOverlap(): Int =
        input.map { line ->
            val assignments = line.split(",", "-").map { it.toInt() }
            val a = Assignment(assignments[0], assignments[1])
            val b = Assignment(assignments[2], assignments[3])
            if (a.start >= b.start && a.end <= b.end || b.start >= a.start && b.end <= a.end) {
                1
            } else {
                0
            }
        }.sum()

    fun countAssignmentsOverlapping(): Int =
        input.map { line ->
            val assignments = line.split(",", "-").map { it.toInt() }
            val a = Assignment(assignments[0], assignments[1])
            val b = Assignment(assignments[2], assignments[3])
            val overlaps = (a.start..a.end).filter { it in b.start..b.end }.count()
            if (overlaps > 0) {
                1
            } else {
                0
            }
        }.sum()

    data class Assignment(val start: Int, val end: Int)
}