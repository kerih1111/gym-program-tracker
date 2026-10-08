/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gymtracker;

/**
 *
 * @author erich
 */
public class CompoundExercise extends Exercise{

    public CompoundExercise(String name) {
        super(name);
    }



    @Override
    public double calculateTotalVolume(User user) {
        double totalVolume = 0;
        
        for(ExerciseSet s : this.sets) {
            int reps = s.getReps();
            int weight = s.getWeight();
            
            int volumeSet = reps * weight;
            
            totalVolume += volumeSet;
        }
        return totalVolume;
    }
    
    
    
}
