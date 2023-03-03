package chefshierarki

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class TomtenTest{

    var tomte = Tomten()

    @Nested
    inner class VG{
        @Test
        fun `Get em all`(){
            val nameList = tomte.getUnderlings(tomte, "Tomten")
            assert(nameList[0] == "Glader")
            assert(nameList[1] == "Tröger")
            assert(nameList[2] == "Trötter")
            assert(nameList[3] == "Skumtomten")
            assert(nameList[4] == "Dammråttan")
            assert(nameList[5] == "Blyger")
            assert(nameList[6] == "Butter")
            assert(nameList[7] == "Rådjuret")
            assert(nameList[8] == "Nyckelpigan")
            assert(nameList[9] == "Haren")
            assert(nameList[10] == "Räven")
            assert(nameList[11] == "Gråsuggan")
            assert(nameList[12] == "Myran")
            assert(nameList[13] == "Bladlusen")

        }
        @Test
        fun `Glader Test`(){
            val nameList = tomte.getUnderlings(tomte, "Glader")
            nameList.forEach { println(it) }
//            assert(nameList[0] == "Glader")
//            assert(nameList[1] == "Tröger")
//            assert(nameList[2] == "Trötter")
//            assert(nameList[3] == "Skumtomten")
//            assert(nameList[4] == "Dammråttan")
//            assert(nameList[5] == "Blyger")
//            assert(nameList[6] == "Butter")
//            assert(nameList[7] == "Rådjuret")
//            assert(nameList[8] == "Nyckelpigan")
//            assert(nameList[9] == "Haren")
//            assert(nameList[10] == "Räven")
//            assert(nameList[11] == "Gråsuggan")
//            assert(nameList[12] == "Myran")
//            assert(nameList[13] == "Bladlusen")

        }
    }
}