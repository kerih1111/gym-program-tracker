/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gymtracker;

import java.io.*;
import java.util.Scanner;

/**
 *
 * @author erich
 */
public class DataManager {

    private static final String users = "Database/users/";
    private static final String workouts = "Database/workouts/";

    public boolean userExists(String username) {
        File file = new File(users + username + ".txt");
        return file.exists();
    }

    public void addUser(User newUser) {
        String filePath = users + newUser.username + ".txt";

        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            writer.print(newUser.saveToDocument());
        } catch (IOException e) {
            System.err.println("Eroare la creare.");
        }
    }

    public void addUser(String username, int age, String gender, int weight, int height, String phase) throws VarstaInvalidaException {
        User user = new User(username, age, gender, weight, height, phase);
        addUser(user);
    }

    public void addWorkout(Workout workout, User newUser) {
        String fileName = workouts + newUser.username + "_workouts.txt";

        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName, true))) {
            writer.print(workout.toString());
            writer.print("\n");
        } catch (IOException e) {
            System.err.println("Eroare la creare.");
        }
    }

    public void printUsers() {
        File folder = new File(users);
        File[] listOfFiles = folder.listFiles();

        if (listOfFiles != null) {
            for (File file : listOfFiles) {
                if (file.isFile()) {
                    String username = file.getName().replace(".txt", "");
                    System.out.println(username);
                }
            }
        }
    }


    public User loadUser(String username) throws VarstaInvalidaException {
        int weight = 0;
        int height = 0;
        int age = 0;
        String phase = null;
        String gender = null;
        String type = null;

        File file = new File(users + username + ".txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            // skip prima linie(username)
            reader.readLine();
            
            // tipul atletului (type, ex bodybuilder)
            type = reader.readLine().split(": ")[1];
            phase = reader.readLine().split(": ")[1];
            age = Integer.parseInt(reader.readLine().split(": ")[1]);
            gender = reader.readLine().split(": ")[1];

            weight = Integer.parseInt(reader.readLine().split(": ")[1]);
            height = Integer.parseInt(reader.readLine().split(": ")[1]);

            switch (type) {
                case "Powerlifter":
                    int sq = Integer.parseInt(reader.readLine().split(": ")[1]);
                    int bp = Integer.parseInt(reader.readLine().split(": ")[1]);
                    int dl = Integer.parseInt(reader.readLine().split(": ")[1]);
                    return new Powerlifter(sq, bp, dl, username, age, gender, weight, height, phase);

                case "Bodybuilder":
                    double bf = Double.parseDouble(reader.readLine().split(": ")[1]);
                    return new Bodybuilder(bf, username, age, gender, weight, height, phase);

                case "Calisthenics":
                    int pullups = Integer.parseInt(reader.readLine().split(": ")[1]);
                    String skill = reader.readLine().split(": ")[1];
                    return new CalisthenicsAthlete(pullups, skill, username, age, gender, weight, height, phase);

                default:
                    return new User(username, age, gender, weight, height, phase);
            }
        } catch (IOException e) {
            System.err.println("Error reading user data: " + e.getMessage());
        }
        return null;
    }

}
