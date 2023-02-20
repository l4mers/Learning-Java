package day6

import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class SignalTest{

    private val signal = Signal("bvwbjplbgvbhsrlpgdmjqwftvncz")
    private val signal2 = Signal("nppdvjthqldpwncqszvftbrmjlhg")
    private val signal3 = Signal("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg")
    private val signal4 = Signal("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw")

    private val signal5 = Signal("mjqjpqmgbljsphdztnvjfqwrcgsmlb")
    private val signal6 = Signal("bvwbjplbgvbhsrlpgdmjqwftvncz")
    private val signal7 = Signal("nppdvjthqldpwncqszvftbrmjlhg")
    private val signal8 = Signal("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg")
    private val signal9 = Signal("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw")


    @Nested
    inner class PartOne{
        @Test
        fun `Marker End`(){
            assert(signal.getMarker() == 5)
            assert(signal2.getMarker() == 6)
            assert(signal3.getMarker() == 10)
            assert(signal4.getMarker() == 11)
        }
    }
    @Nested
    inner class PartTwo{
        @Test
        fun `Marker End`(){
            assert(signal5.getMessage() == 19)
            assert(signal6.getMessage() == 23)
            assert(signal7.getMessage() == 23)
            assert(signal8.getMessage() == 29)
            assert(signal9.getMessage() == 26)
        }
    }
}