package com.example.pgp;
/*
 * Updated 4/13/23
 *
 * Class contains methods for user usertype
 * William Vaughan
 * Greyson Williams
 */

import java.util.Objects;
import java.util.Scanner;

import static com.example.pgp.PgpApplication.clearScreen;
import static com.example.pgp.PgpApplication.data;

public class User {
    protected int index;

    public User(int index) {
        this.index = index;
        menu();
    }

    /**
     * Creates user menu for selecting options, end returns to pin menu
     * add or subtract ifs to increase or decrease menu size
     */
    public void menu() {
        String menuC;
        boolean x = false;
        Scanner input = new Scanner(System.in);
        while (!x) {
            System.out.println("Hello " + data[index][1] + ",\nPlease choose an option\n" +
                    "or type 'end' and press\n" +
                    "enter to log out");

            System.out.println("1.) Pump Gas\n2.) Check Total Used\n3.) Check Tank Level");

            menuC = input.next();

            if (Objects.equals(menuC, "1")) {
                getFuel();
                x = true;
            } else if (Objects.equals(menuC, "2")) {
                checkFuel();
            } else if (Objects.equals(menuC, "3")) {
                tank();
            } else if (Objects.equals(menuC, "end")) {
                x = true;
            } else {
                System.out.println("That is not a valid entry");
            }
        }

    }

    /**
     * Method takes an entered amount of fuel in gallons and "dispenses" it.
     * Value it then added to total user amount and saved to CSV file.
     * If fuel level in tank is too low, pump is disabled and user is warned.
     * If pump is disabled, user is warned.
     */
    public void getFuel() {
        Scanner input = new Scanner(System.in);
        CsvFile file = new CsvFile("src/main/java/com/example/pgp/pinData.csv");
        if (Objects.equals(data[1][3], "0")) {
            System.out.println("Pump is disabled,\nplease contact an administrator.");
        } else if (Double.parseDouble(data[0][3]) < 100) {
            System.out.println("Level in tank is too low,\npump has been disabled.\nPlease contact an administrator.");
            data[1][3] = "0";
            file.writeData(data);
        } else {
            int amount;
            System.out.println("Please enter an amount in gallons:");
            amount = input.nextInt();
            System.out.println("Fuel dispensed, Thank you");

            data[index][3] = String.valueOf(Double.parseDouble(data[index][3]) + amount);
            data[0][3] = String.valueOf(Double.parseDouble(data[0][3]) - amount);
            file.writeData(data);
        }
        System.out.println("Press enter to continue...");
        input.nextLine();
        clearScreen();
    }

    /**
     * Prints total amount of fuel used by user over lifetime
     */
    public void checkFuel() {
        Scanner input = new Scanner(System.in);
        System.out.println("You have used " + data[index][3] + " gallons of fuel.");
        System.out.println("Press enter to continue...");
        input.nextLine();
        clearScreen();
    }

    /**
     * Prints current gas level in tank
     */
    public void tank() {
        System.out.println("There are " + data[0][3] + "g in tank.");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Press enter to continue...");
        scanner.nextLine();
        clearScreen();
    }
}
