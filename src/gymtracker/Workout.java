/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gymtracker;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author erich
 */
public class Workout {

    private String workoutType;
    private List<Exercise> exercises;
    private User user;

    public Workout(String workoutType, User user) {
        this.user = user;
        this.workoutType = workoutType;
        this.exercises = new ArrayList<>();
    }

    public void addExercise(Exercise e) {
        this.exercises.add(e);
    }

    public double calculateTotalWorkoutVolume(User user) {
        double total = 0;
        for (Exercise ex : exercises) {
            total += ex.calculateTotalVolume(user);
        }
        return total;
    }

    public void printWorkout() {
        for (Exercise ex : exercises) {
            int count = 1;
            System.out.println(ex.getName());
            for (ExerciseSet exSet : ex.sets) {
                System.out.print("Set: " + count + ": " + exSet.getReps() + "reps|" + exSet.getWeight() + "kg");
                count++;
            }
            System.out.println("");
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(this.workoutType + ": \n");
        for (Exercise ex : exercises) {
            if (ex.sets.isEmpty()) {
                continue;
            }
            sb.append(ex.getName()).append("\n");

            int count = 1;
            for (ExerciseSet exSet : ex.sets) {
                sb.append("Set: ").append(count)
                        .append(" ").append(exSet.getReps()).append("reps|")
                        .append(exSet.getWeight()).append("kg");

                sb.append(" ");
                count++;
            }
            sb.append("\n");
        }
        sb.append("Volum total: "+ calculateTotalWorkoutVolume(user));
        sb.append("\n");
        
        return sb.toString();
    }

}
