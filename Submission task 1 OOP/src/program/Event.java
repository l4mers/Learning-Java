package program;

import plants.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Objects;

public class Event implements ActionListener, ItemListener {
    Panel panel;
    ArrayList<Plant> plants;
    Event(Panel panel){
        this.panel = panel;
        plants = new ArrayList<>();

        setUpPlants();
    }
    private void setUpPlants() {
        plants.add(new Cactus(0.02, "Igge"));
        plants.add(new Palm(5, "Laura"));
        plants.add(new Carnivorous(0.7, "Meatloaf"));
        plants.add(new Palm(1, "Putte"));

        String[] plantNames = new String[plants.size()];

        for (int i = 0; i < plants.size(); i++) {
            plantNames[i] = plants.get(i).getName();
        }
        panel.populateDropDown(plantNames);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (panel.quit.equals(source)) {
            System.exit(0);
        } else if (panel.back.equals(source)) {
            panel.mainScreen();
        } else if (panel.start.equals(source)){
            panel.plantSelectionScreen();
        } else if (panel.show.equals(source)){
            getPlant();
            panel.infoScreen();
        }
    }
    private void getPlant() {
        for (Plant plant: plants) {
            if(Objects.requireNonNull(panel.plantSelection.getSelectedItem()).toString().equals(plant.getName())){
                plant.calculateFood();
                panel.info.setText(plant.getFood());
            }
        }
    }
    @Override
    public void itemStateChanged(ItemEvent e) {
    }
}
