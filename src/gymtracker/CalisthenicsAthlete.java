/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gymtracker;

/**
 *
 * @author erich
 */
public class CalisthenicsAthlete extends User {

    private int pullUpMaxReps;
    private String targetSkill;

    public CalisthenicsAthlete(int pullUpMaxReps, String targetSkill, String username, int age, String gender, int weight, int height, String phase) throws VarstaInvalidaException {
        super(username, age, gender, weight, height, phase);
        this.pullUpMaxReps = pullUpMaxReps;
        this.targetSkill = targetSkill;
    }

    public int getPullUpMaxReps() {
        return pullUpMaxReps;
    }
    
    
    
    public String athleteLevel() {
        if(pullUpMaxReps < 8) {
            return "Incepator";
        } else if(pullUpMaxReps >= 8 && pullUpMaxReps <= 15) {
            return "Mediu";
        } else if(pullUpMaxReps > 15) {
            return "Poti incepe pregatirea pentru skill-uri avansate";
        }
        return null;
    }

    @Override
    public String saveToDocument() {
        return "User: " + getUsername() + "\n"
                + "Type: Calisthenics\n"
                + "Phase: " + getPhase() + "\n"
                + "Age: " + getAge() + "\n"
                + "Gender: " + getGender() + "\n"
                + "Weight: " + getWeight() + "\n"
                + "Height: " + getHeight() + "\n"
                + "MaxPullups: " + pullUpMaxReps + "\n"
                + "TargetSkill: " + targetSkill;
    }
}
