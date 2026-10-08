/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gymtracker;

/**
 *
 * @author erich
 */
public class Powerlifter extends User {

    private int squat1RM;
    private int bench1RM;
    private int deadlift1RM;
    private int totalPo;

    public Powerlifter(int squat1RM, int bench1RM, int deadlift1RM, String username, int age, String gender, int weight, int height, String phase) throws VarstaInvalidaException {
        super(username, age, gender, weight, height, phase);
        this.squat1RM = squat1RM;
        this.bench1RM = bench1RM;
        this.deadlift1RM = deadlift1RM;
        this.totalPo = squat1RM + bench1RM + deadlift1RM;
    }
    
    public int getSquat1RM() { return squat1RM; }
    public int getBench1RM() { return bench1RM; }
    public int getDeadlift1RM() { return deadlift1RM; }
    public int getTotalPo() { return totalPo; }

    
    
    @Override
    public String saveToDocument() {
        return "User: " + getUsername() + "\n"
                + "Type: Powerlifter\n"
                + "Phase: " + getPhase() + "\n"
                + "Age: " + getAge() + "\n"
                + "Gender: " + getGender() + "\n"
                + "Weight: " + getWeight() + "\n"
                + "Height: " + getHeight() + "\n"
                + "Squat: " + squat1RM + "\n"
                + "Bench: " + bench1RM + "\n"
                + "Deadlift: " + deadlift1RM;
    }

}
