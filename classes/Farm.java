package classes;

import java.util.*;

public class Farm {

    private String name; 
    private String type;
    private double balance;
    private Farmer farmer;
    private Hashtable<String, Integer> bonuses;
    private List<Crops> crops;
    private List<Animal> animals;

    public Boolean setName(String newName) {

        name = newName;
    }

    public void setType(String newType) {

        if (newType == "Plain") {
            bonuses.put(newType, 1);
        }

    }
}

