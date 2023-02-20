package chefshierarki

open class Employee() {
    var hierarchyList: ArrayList<Employee> = ArrayList()
    var name: String = ""

    fun getNisse(emp: Employee, nisse: String){
        if(emp.name === nisse){
            if(emp.hierarchyList.size > 0){
                emp.hierarchyList.forEach{
                    println(it.name)
                    it.getNisseName(it)}
            }
        }else{
            if(emp.hierarchyList.size > 0){
                emp.hierarchyList.forEach{it.getNisse(it, nisse)}
            }
        }
    }
    private fun getNisseName(emp: Employee){
        if(emp.hierarchyList.size > 0){
            emp.hierarchyList.forEach{
                println(it.name)
                emp.getNisseName(it)
            }
        }
    }
}