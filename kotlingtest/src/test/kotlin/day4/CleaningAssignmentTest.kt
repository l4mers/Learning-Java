package day4

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class CleaningAssignmentTest{
    private val ca = CleaningAssignment("""
        10-10,10-10
        9-10,9-10
        10-16,11-15
        11-15,10-16
        21-31,41-53
        10-15,12-17
    """.trimIndent().lines())

    @Nested
    inner class PartOne{
        @Test
        fun `total Overlap Test`(){
            assert(ca.countAssignmentsTotallyOverlap() == 4)
        }
    }
    @Nested
    inner class PartTwo{
        @Test
        fun ` Overlap`(){
            println(ca.countAssignmentsOverlapping())
            assert(ca.countAssignmentsOverlapping() == 5)
        }
    }
}