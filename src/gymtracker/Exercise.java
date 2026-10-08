/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gymtracker;

import java.util.ArrayList;

/**
 *
 * @author erich
 */
public abstract class Exercise {

    protected String name;
    protected ArrayList<ExerciseSet> sets;

    public Exercise(String name) {
        this.name = name;
        this.sets = new ArrayList<>();
    }

    

    public String getName() {
        return name;
    }
    
    public void addSet(ExerciseSet set){
        this.sets.add(set);
    }

    public abstract double calculateTotalVolume(User user);
    
}
