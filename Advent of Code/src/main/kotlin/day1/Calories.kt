package day1

import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths
import java.util.stream.Stream

//2022-12-01 Första uppgift egenlösning
class Calories() {

    private val sumList = mutableListOf<Int>()

    init {
        val addList = mutableListOf<Int>()
        try{
            val stream: Stream<String> = Files.lines(Paths.get("src/main/kotlin/day1/calories.txt"))
            stream.forEach{lines ->
                if(lines.equals("")){
                    sumList.add(addList.stream().toList().sum())
                    addList.clear()
                }else{
                    addList.add(Integer.valueOf(lines))
                }
            }

        }catch (e: IOException){
            e.stackTrace
        }
    }

    //svar 1
    fun getBigBetaElf(): Int{
        return sumList.maxOrNull() ?: 0
    }

    //svar 2
    fun getBigBetaElfs(): Int{
        sumList.sortDescending()
        return sumList[0] + sumList[1] + sumList[2]
    }

}
