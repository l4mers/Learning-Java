package day1

import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class CaloriesTest {

    private val calories = Calories()


    @Nested
    inner class GetAlfaElf{
        @Test
        fun `calories carried by the elf with most calories`(){
            assert(calories.getBigBetaElf() == 72240)
        }
        @Test
        fun `calories carried by the three elf with most calories`(){
            assert(calories.getBigBetaElfs() == 210957)
        }
    }
}