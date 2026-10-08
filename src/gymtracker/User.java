/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gymtracker;

/**
 *
 * @author erich
 */
public class User {

    public String username;
    protected int age;
    protected String gender;
    protected int weight;
    protected int height;
    private String phase;
    private int caloriiConsumate;

    public User(String username, int age, String gender, int weight, int height, String phase) {
        
        this.username = username;
        this.age = age;
        this.gender = gender;
        this.weight = weight;
        this.height = height;
        this.phase = phase;
        this.caloriiConsumate = 0;
    }
    
     public static void valideazaVarsta(int varsta) throws VarstaInvalidaException {
        if(varsta < 10 || varsta > 90)  {
            throw new VarstaInvalidaException("Varsta " + varsta + " nu e permisa.");
        }
    }

    public String getUsername() {
        return username;
    }

    public int getHeight() {
        return height;
    }

    public int getWeight() {
        return weight;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getPhase() {
        return phase;
    }

    public void mananca(Mancare aliment) {
        this.caloriiConsumate += aliment.getCalorii();
    }

    public int getCaloriiConsumate() {
        return caloriiConsumate;
    }

    public void resetCaloriiConsumate() {
        this.caloriiConsumate = 0;
    }

    public int phaseTDEE() {
        if (phase.equalsIgnoreCase("bulk")) {
            return (calculateTDEE() + 500);
        } else if (phase.equalsIgnoreCase("cut")) {
            return (calculateTDEE() - 350);
        }
        return 0;
    }

    public double calculateBMI() {
        double heightInMeters = this.height / 100.0;
        return this.weight / (heightInMeters * heightInMeters);
    }

    public int calculateBaseBMR() {
        if (this.gender.equalsIgnoreCase("M")) {
            return (int) (88.362 + (13.397 * this.weight) + (4.799 * this.height) - (5.677 * this.age));
        } else {
            return (int) (447.593 + (9.247 * this.weight) + (3.098 * this.height) - (4.330 * this.age));
        }
    }

    public int calculateMaxHeartRate() {
        return (int) (208 - (0.7 * this.age));
    }

    public int calculateTDEE() {
        int bmr = this.calculateBaseBMR();
        return (int) (bmr * 1.55);
    }

    public String saveToDocument() {
        return "User: " + username + "\n"
                + "Type: Generic\n"
                + "Phase: " + phase + "\n"
                + "Age: " + age + "\n"
                + "Gender: " + gender + "\n"
                + "Weight: " + weight + "\n"
                + "Height: " + height;
    }

}
