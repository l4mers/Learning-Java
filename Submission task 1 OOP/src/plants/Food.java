package plants;

/*
* Enum som har statiska värden kopplade till typ av mat
* */
public enum Food {
    MINERAL_WATER ("Mineral Water", 0.02),
    PROTEIN_DRINK ("Protein Drink", 0.1),
    TAP_WATER ("Tap Water", 0.5);

    public final String type;
    public final double baseAmount;

    Food(String s, double b){
        type = s;
        baseAmount = b;
    }
}
