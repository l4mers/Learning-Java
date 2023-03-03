package chefshierarki

class Skumtomten() : Employee() {
    init {
        this.name = "Skumtomten"
        hierarchyList.add(Dammrottan())
    }
}