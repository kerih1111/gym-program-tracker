/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gymtracker;

/**
 *
 * @author erich
 */
public class ExerciseSet {
    private int reps;
    private int weight;
    private boolean isBodyweight;

    public ExerciseSet(int reps, int weight, boolean isBodyweight) {
        this.reps = reps;
        this.weight = weight;
        this.isBodyweight = isBodyweight;
    }

    public int getReps() {
        return reps;
    }

    public int getWeight() {
        return weight;
    }
    
    
    
    
}
