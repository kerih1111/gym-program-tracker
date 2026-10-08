/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gymtracker;

/**
 *
 * @author erich
 */
public class Bodybuilder extends User {

    private double bodyFatPercentage;

    public Bodybuilder(double bodyFatPercentage, String username, int age, String gender, int weight, int height, String phase) throws VarstaInvalidaException {
        super(username, age, gender, weight, height, phase);
        this.bodyFatPercentage = bodyFatPercentage;
    }
    
    public double calculateLeanBodyMass() {
        double fatMass = this.getWeight() * (this.bodyFatPercentage / 100.0);
        return this.getWeight() - fatMass;
    }
    
    public double calculateRawFFMI() {
        double heightInMeters = this.getHeight() / 100.0;
        return calculateLeanBodyMass() / (heightInMeters * heightInMeters);
    }

    @Override
    public String saveToDocument() {
        return "User: " + getUsername() + "\n"
                + "Type: Bodybuilder\n"
                + "Phase: " + getPhase() + "\n"
                + "Age: " + getAge() + "\n"
                + "Gender: " + getGender() + "\n"
                + "Weight: " + getWeight() + "\n"
                + "Height: " + getHeight() + "\n"
                + "Bodyfat: " + bodyFatPercentage + "\n";
    }
}
