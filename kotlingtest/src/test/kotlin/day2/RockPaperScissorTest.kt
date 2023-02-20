package day2

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class RockPaperScissorTest{
    private val rpc = RockPaperScissor("""
        A Y
        B X
        C Z
    """.trimIndent().lines())

    @Nested
    inner class PartOne{
        @Test
        fun `Score test`(){
            assert(rpc.getScore() == 15)
        }
    }
    @Nested
    inner class PartTwo{
        @Test
        fun `Score by even`(){
            assert(rpc.getScoreSecond() == 12)
        }
    }
}