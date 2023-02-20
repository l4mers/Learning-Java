package chefshierarki


class NordPolen() {


    val lastLayer = mapOf("Dammråttan" to "")
    val lastLayer2 = mapOf("Bladlusen" to "")
    val mostInnerMap1 = mapOf("Skumtomten" to lastLayer)
    val mostInnerMap2 = mapOf("Gråsuggan" to "", "Myran" to lastLayer2)

    val innerMap1 = mapOf(
        "Tröger" to "",
        "Trötter" to mostInnerMap1,
        "Blyger" to ""
    )

    val innerMap2 = mapOf(
        "Rådjuret" to "",
        "Nyckelpigan" to "",
        "Haren" to "",
        "Räven" to mostInnerMap2
    )

    val firstMap = mapOf(
        "Glader" to innerMap1,
        "Butter" to innerMap2
    )

    val finalMap = mapOf("Tomten" to firstMap)

//    val key = finalMap["Tomten"]?.get("Glader")?.get("Trötter")?.get("Skumtomten")
//        ?.get("Dammråttan")?.keys?.firstOrNull()

}
