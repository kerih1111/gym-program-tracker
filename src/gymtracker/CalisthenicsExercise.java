/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gymtracker;

/**
 *
 * @author erich
 */
public class CalisthenicsExercise extends Exercise{

    public CalisthenicsExercise(String name) {
        super(name);
    }




    @Override
    public double calculateTotalVolume(User user) {
        int totalVolume = 0;
        for(ExerciseSet s : this.sets) {
            int reps = s.getReps();
            int userWeight = user.getWeight();
            int addedWeight = s.getWeight();
            
            int totalRes = userWeight + addedWeight;
            totalVolume += (reps * totalRes);
        }
        return totalVolume;
    }
    
}
