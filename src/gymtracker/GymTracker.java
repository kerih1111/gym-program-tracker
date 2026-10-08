/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package gymtracker;

import java.util.Scanner;
import java.io.*;

/**
 *
 * @author erich
 */
public class GymTracker {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DataManager dm = new DataManager();
        User currentUser = null;
        Workout workout = null;
        int option = 0;
        String username;

        while (true) {

            System.out.print("Introdu numele user: ");
            username = scanner.nextLine();
            if (dm.userExists(username)) {
                try {
                    System.out.println("\nBine ai revenit, " + username + "\n");
                    currentUser = dm.loadUser(username);
                    break;
                } catch (VarstaInvalidaException e) {
                    System.err.println("eroare, varsta salvata pe disc e corupta.");
                    System.out.println("Incercati sa introduceti alt utilizator.");
                }
            } else {
                System.out.println("User-ul nu a fost gasit. Vrei sa il adaugi?(y/n)");

                String choice = scanner.nextLine();
                if (choice.equalsIgnoreCase("y")) {

                    try {
                        System.out.println("User nou va fi creat, va rugam introduceti datele.");
                        System.out.print("Varsta(ex: 24): ");
                        int age = Integer.parseInt(scanner.nextLine());
                        currentUser.valideazaVarsta(age);
                        System.out.print("Gen(M/F): ");
                        String gender = scanner.nextLine();
                        System.out.print("Greutate(ex: 83): ");
                        int weight = Integer.parseInt(scanner.nextLine());
                        System.out.print("Inaltime(ex: 173): ");
                        int height = Integer.parseInt(scanner.nextLine());
                        System.out.print("Introdu faza de pregatire(Bulk/Cut): ");
                        String phase = scanner.nextLine();

                        System.out.println("\nAlege tipul de sportiv:");
                        System.out.println("1. Powerlifter");
                        System.out.println("2. Bodybuilder");
                        System.out.println("3. Calisthenics");
                        System.out.print("Optiune(1/2/3): ");
                        int tipAlegere = Integer.parseInt(scanner.nextLine());

                        switch (tipAlegere) {
                            case 1:
                                System.out.println("Powerlifter ales.");
                                System.out.print("Introdu 1RM Squat (kg): ");
                                int squat = Integer.parseInt(scanner.nextLine());
                                System.out.print("Introdu 1RM Bench Press (kg): ");
                                int bench = Integer.parseInt(scanner.nextLine());
                                System.out.print("Introdu 1RM Deadlift (kg): ");
                                int deadlift = Integer.parseInt(scanner.nextLine());

                                currentUser = new Powerlifter(squat, bench, deadlift, username, age, gender, weight, height, phase);
                                break;
                            case 2:
                                System.out.println("Bodybuilder ales.");
                                System.out.print("Introdu procentajul de grasime(ex 12.3): ");
                                double bf = Double.parseDouble(scanner.nextLine());

                                currentUser = new Bodybuilder(bf, username, age, gender, weight, height, phase);
                                break;
                            case 3:
                                System.out.println("Calisthenics ales.");
                                System.out.print("Introdu numarul maxim de repetari la tractiuni: ");
                                int maxPullups = Integer.parseInt(scanner.nextLine());
                                System.out.print("Introdu skill-ul tinta(ex: Planche): ");
                                String skill = scanner.nextLine();

                                currentUser = new CalisthenicsAthlete(maxPullups, skill, username, age, gender, weight, height, phase);
                                break;

                            default:
                                System.out.println("Optiune invalida. Profit generic creat.");
                                currentUser = new User(username, age, gender, weight, height, phase);
                                break;
                        }
                        dm.addUser(currentUser);
                        System.out.println("Profilul a fost creat si salvat!");
                        break;

                    } catch (VarstaInvalidaException e) {
                        System.err.println("Eroare la date " + e.getMessage());
                        System.out.println("Inregistrarea a fost anulata. ");
                        currentUser = null;
                    }
                }
            }
        }

        boolean running = true;
        while (running) {
            System.out.println("1. Vezi datele despre user. ");
            System.out.println("2. Schimba user-ul. ");
            System.out.println("3. Adauga exercitii la antrenament. ");
            System.out.println("4. Calculator Stiintific. ");
            System.out.println("5. Adauga masa. ");
            System.out.println("6. Iesi din program. ");
            System.out.print("Alegere: ");

            option = Integer.valueOf(scanner.nextLine());

            switch (option) {
                case 1:
                    System.out.println("\n ---");
                    System.out.println(currentUser.saveToDocument());
                    System.out.println(" --- \n");
                    break;
                case 2:
                    dm.printUsers();
                    System.out.println("\nAcum esti la user-ul " + currentUser.getUsername());
                    while (true) {
                        System.out.print("Scrie userul pe care vrei sa-l accesezi: ");
                        username = scanner.nextLine();

                        if (!dm.userExists(username)) {
                            System.out.println("User-ul nu exista.");
                        } else {
                            try {
                                User userNou = dm.loadUser(username);
                                currentUser = userNou;
                                System.out.println("User schimbat cu succes. Bine ai venit, " + currentUser.getUsername() + ".\n");
                                break;
                            } catch (VarstaInvalidaException e) {
                                System.err.println("Nu s-a putut schimba utilizatorul.");
                            }
                        }
                    }
                    break;
                case 3:
                    workout = logNewWorkout(scanner, currentUser);
                    System.out.println("Adaugat cu succes!");
                    dm.addWorkout(workout, currentUser);
                    break;
                case 4:
                    System.out.println("Salut, " + currentUser.getUsername() + " ai ales calculatorul stiintific, calculate pe baza greutatii: " + currentUser.getWeight()
                            + "kg, a inaltimii: " + currentUser.getHeight() + "cm, sexului: " + currentUser.getGender() + " si varstei: " + currentUser.getAge() + "ani.\n");

                    boolean flag = true;
                    while (flag) {
                        System.out.print("1. BMI ");
                        System.out.print("2. BMR ");
                        System.out.print("3. TDEE ");
                        System.out.print("4. MaxHeartRate ");
                        System.out.println("5. Calculator specific atleti ");
                        System.out.print("6. Inchide calculator stiintific");
                        System.out.print("\nOptiune: ");
                        int optiune = Integer.parseInt(scanner.nextLine());

                        if (optiune == 6) {
                            flag = false;
                            continue;
                        }
                        switch (optiune) {
                            case 1:
                                double BMI = currentUser.calculateBMI();
                                System.out.println("BMI = " + BMI);
                                break;
                            case 2:
                                double BMR = currentUser.calculateBaseBMR();
                                System.out.println("BMR = " + BMR);
                                break;
                            case 3:
                                double TDEE = currentUser.calculateTDEE();
                                System.out.println("TDEE = " + TDEE);
                                break;
                            case 4:
                                double heartRate = currentUser.calculateMaxHeartRate();
                                System.out.println("Max Heart rate = " + heartRate);
                                break;
                            case 5:
                                if (currentUser instanceof Powerlifter) {
                                    System.out.println("Powerlifter, greutatea totala ridicata(SBD): " + ((Powerlifter) currentUser).getTotalPo());
                                } else if (currentUser instanceof Bodybuilder) {
                                    System.out.println("Bodybuilder, Lean body mass: " + ((Bodybuilder) currentUser).calculateLeanBodyMass() + " Raw FFMI: " + ((Bodybuilder) currentUser).calculateRawFFMI());
                                } else if (currentUser instanceof CalisthenicsAthlete) {
                                    System.out.println("Calisthenics, max pull-ups level: " + ((CalisthenicsAthlete) currentUser).getPullUpMaxReps() + "reps, nivel: " + ((CalisthenicsAthlete) currentUser).athleteLevel());
                                }
                        }
                    }
                    break;
                case 5:
                    int TDEE = currentUser.phaseTDEE();
                    System.out.println("Phase-ul curent este: " + currentUser.getPhase() + " trebuie sa mananci " + TDEE + " calorii.");
                    System.out.println("Targetul caloric ajustat este: " + TDEE);
                    System.out.println("Calorii consumate: " + currentUser.getCaloriiConsumate());
                    try {

                        System.out.println("Introdu tipul de mancare(scrie masa/bautura)");
                        String tipMancare = scanner.nextLine();
                        MancareFactory.valideazaTip(tipMancare);
                        System.out.println("Introdu numele specific al mancarii");
                        String numeMancare = scanner.nextLine();

                        System.out.println("Introdu numarul de calorii: ");
                        int calMasa = Integer.parseInt(scanner.nextLine());
                        System.out.println("Introdu gramele de proteine: ");
                        int protMasa = Integer.parseInt(scanner.nextLine());

                        Mancare masaCurenta = MancareFactory.createMancare(tipMancare, numeMancare, calMasa, protMasa);

                        if (masaCurenta != null) {
                            currentUser.mananca(masaCurenta);
                            System.out.println("Total calorii mancate: " + currentUser.getCaloriiConsumate());
                            int caloriiRamase = TDEE - currentUser.getCaloriiConsumate();
                            if (caloriiRamase > 0) {
                                System.out.println("Mai ai de mancat " + caloriiRamase + "calorii");
                            } else {
                                System.out.println("Ai atins targetul caloric pentru astazi.");
                            }
                        } else {
                            System.out.println("Tipul de mancare nu a fost recunoscut.");
                        }
                    } catch (TipMancareInvalidaException e) {
                        System.err.println("Eroare " + e.getMessage());
                    }
                    break;

                case 6:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        }

    }

    private static Workout logNewWorkout(Scanner scanner, User user) {
        System.out.println("Introdu numele antrenamentului: ");
        String workoutName = scanner.nextLine();
        Workout workout = new Workout(workoutName, user);

        boolean flag = true;
        while (flag) {
            System.out.println("\n---Alege grupa musculara---");
            System.out.print("1. Piept ");
            System.out.print("2. Spate ");
            System.out.print("3. Picioare ");
            System.out.print("4. Brate ");
            System.out.print("5. Termina antrenamentul.");
            System.out.print("\nOptiune: ");
            int optiuneGrupa = Integer.parseInt(scanner.nextLine());

            if (optiuneGrupa == 5) {
                flag = false;
                continue;
            }

            String exName = "";
            boolean isBW = false;

            switch (optiuneGrupa) {
                case 1:
                    System.out.println("\n1.Bench Press\n2.Dips\n3.Chest Flyes");
                    int Choice = Integer.parseInt(scanner.nextLine());
                    switch (Choice) {
                        case 1:
                            exName = "Bench Press";
                            isBW = false;
                            break;
                        case 2:
                            exName = "Dips";
                            isBW = true;
                            break;
                        case 3:
                            exName = "Chest Flyes";
                            isBW = false;
                            break;
                    }
                    break;
                case 2:
                    System.out.println("\n1.Pull-ups\n2.Rows\n3.Pull-over");
                    Choice = Integer.parseInt(scanner.nextLine());
                    switch (Choice) {
                        case 1:
                            exName = "Pull-ups";
                            isBW = true;
                            break;
                        case 2:
                            exName = "Rows";
                            isBW = false;
                            break;
                        case 3:
                            exName = "Pull-over";
                            isBW = false;
                            break;
                    }
                    break;
                case 3:
                    System.out.println("\n1.Squats\n2.Romanian Deadlifts\n3.Leg Extension");
                    Choice = Integer.parseInt(scanner.nextLine());
                    switch (Choice) {
                        case 1:
                            exName = "Squats";
                            isBW = false;
                            break;
                        case 2:
                            exName = "Romanian Deadlifts";
                            isBW = false;
                            break;
                        case 3:
                            exName = "Leg Extension";
                            isBW = false;
                            break;
                    }
                    break;
                case 4:
                    System.out.println("\n1.Biceps Curls\n2.Lateral raises\n3.Triceps Pushdown");
                    Choice = Integer.parseInt(scanner.nextLine());
                    switch (Choice) {
                        case 1:
                            exName = "Biceps Curls";
                            isBW = false;
                            break;
                        case 2:
                            exName = "Lateral raises";
                            isBW = false;
                            break;
                        case 3:
                            exName = "Triceps Pushdown";
                            isBW = false;
                            break;
                    }
                    break;
            }
            Exercise exercise = null;
            if (isBW) {
                exercise = new CalisthenicsExercise(exName);
            } else {
                exercise = new CompoundExercise(exName);
            }
            System.out.println("Seturi: ");
            int sets = Integer.parseInt(scanner.nextLine());
            try {
                for (int i = 1; i <= sets; i++) {
                    System.out.println("Set " + i + ": ");
                    System.out.print("Greutate: ");
                    int weight = Integer.parseInt(scanner.nextLine());
                    if (weight < 0 || weight > 500) {
                        throw new GreutateInvalidaException("Greutatea " + weight + " e invalida.");
                    }
                    System.out.print("Repetari: ");
                    int reps = Integer.parseInt(scanner.nextLine());
                    ExerciseSet workingSet = new ExerciseSet(reps, weight, isBW);
                    exercise.addSet(workingSet);
                }
                workout.addExercise(exercise);
            } catch (GreutateInvalidaException e) {
                System.err.println("Eroare set: " + e.getMessage());
            }
        }
        return workout;
    }
}
