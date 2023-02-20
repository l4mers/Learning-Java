package day11

class GameAfter(input: List<String>) {
    private val monkeys = setUpMonkeys(input)

    private val monkeyBusinessIterations = 20
    private val monkeyBusinessRidiculousIterations = 10000
    private var shouldBeDivided = true

    fun calculateMonkeyBusiness(): Long {
        repeat(monkeyBusinessIterations) {
            loop()
        }
        return returnMonkeyBusiness(monkeys)
    }

    fun calculateRidiculousMonkeyBusiness(): Long {
        shouldBeDivided = false
        repeat(monkeyBusinessRidiculousIterations) {
            loop()
        }
        return returnMonkeyBusiness(monkeys)
    }
    private fun loop(){
        for (monkey in monkeys) {
            if (monkey.itemList.isNotEmpty()) {
                applyOperations(monkey, shouldBeDivided)
                monkey.monkeyBusiness += monkey.itemList.size
                monkey.itemList.clear()
            }
        }
    }
    private fun applyOperations(monkey: Monkey, shouldBeDivided: Boolean){
        for (item in monkey.itemList){
            val worryLvl = monkey.applyOperation(item)
            val realWorryLvl: Long = worryLvl.div(3)
            if(shouldBeDivided){
                addToItemList(monkey, realWorryLvl)
            }else{
                addToItemList(monkey, worryLvl)
            }
        }
    }
    private fun addToItemList(monkey: Monkey, worryLvl: Long){
        if((worryLvl % monkey.divider) == 0L){
            monkeys[monkey.monkey1].itemList.add(worryLvl)
        } else {
            monkeys[monkey.monkey2].itemList.add(worryLvl)
        }
    }

    private fun returnMonkeyBusiness(input: List<Monkey>): Long {
        val sortedList = input.sortedByDescending { it.monkeyBusiness }
        return sortedList[0].monkeyBusiness * sortedList[1].monkeyBusiness
    }

    private fun setUpMonkeys(input: List<String>): List<Monkey> {
        val monkeys = mutableListOf<Monkey>()
        var itemList = mutableListOf<Long>()
        var operator = ""
        var operation = ""
        var divider: Long = 0
        var monkey1: Int = 0
        for (ss in input) {
            val s = ss.trim()
            when {
                s.startsWith("Starting items:") -> {
                    val items = s.replace("Starting items: ", "").split(", ")
                    itemList.addAll(items.map { it.toLong() })
                }

                s.startsWith("Operation") -> {
                    operation = s.substringAfterLast(" ")
                    operator = s.replace("Operation: new = old ", "").substringBeforeLast(" ")
                }

                s.startsWith("Test: divisible by ") -> {
                    divider = s.replace("Test: divisible by ", "").toLong()
                }

                s.startsWith("If true: throw to monkey ") -> {
                    monkey1 = s.replace("If true: throw to monkey ", "").toInt()
                }

                s.startsWith("If false: throw to monkey ") -> {
                    val monkey2 = s.replace("If false: throw to monkey ", "").toInt()
                    monkeys.add(Monkey(itemList, operator, operation, divider, monkey1, monkey2))
                    itemList = mutableListOf()
                }
            }
        }
        return monkeys
    }

    inner class Monkey(
        val itemList: MutableList<Long>,
        private val operator: String,
        private val operation: String,
        val divider: Long,
        val monkey1: Int,
        val monkey2: Int
    ) {
        var monkeyBusiness: Long = 0
        fun applyOperation(item: Long): Long =
            if(operation == "old"){
                item * item
            } else if(operator == "*"){
                item * operation.toLong()
            } else {
                item + operation.toLong()
            }
    }
}