package chefshierarki

class Glader() : Employee() {

    init {
        this.name = "Glader"
        hierarchyList.add(Troger())
        hierarchyList.add(Trotter())
        hierarchyList.add(Blyger())
    }
}