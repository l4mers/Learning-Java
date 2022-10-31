package plants;

//Carnivorous ärver från Plant klassen
public class Carnivorous extends Plant{
    /*
    Inkapsling private eftersom värdet enbart hanteras i denna subklass.
    Värdet behöver inte kunna nås av andra klasser eller via ett objekt
     */
    final private double multiplayer;
    public Carnivorous(double height, String name) {
        super(height, name);
        food = Food.PROTEIN_DRINK;
        multiplayer = 0.2;
    }
    //Polymorfism
    public void calculateFood() {
        foodAmount = food.baseAmount + multiplayer * height;
    }
}
