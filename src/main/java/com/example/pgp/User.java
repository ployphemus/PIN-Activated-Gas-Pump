package com.example.pgp;

import java.util.Objects;
import java.util.Scanner;

import static com.example.pgp.PgpApplication.clearScreen;
import static com.example.pgp.PgpApplication.data;

public class User {
    protected int index;

    public User(int index) {
        this.index = index;
        menu();
        //TODO create user access stuff: pump gas, check lifetime pump use
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
                x = true;
            } else if (Objects.equals(menuC, "3")) {
                tank();
            } else if (Objects.equals(menuC, "end")) {
                x = true;
            } else {
                System.out.println("That is not a valid entry");
            }
        }

    }

    public void getFuel() {
        //TODO make tank warning
        int amount;
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter an amount in gallons:");
        amount = input.nextInt();

        System.out.println("Fuel dispensed, Thank you");
        CsvFile file = new CsvFile("src/main/java/com/example/pgp/pinData.csv");
        data[index][3] = String.valueOf(Double.parseDouble(data[index][3]) + amount);
        data[0][3] = String.valueOf(Double.parseDouble(data[0][3]) - amount);
        file.writeData(data);

    }

    public void checkFuel() {
        System.out.println("You have used 481.77 gallons of fuel.");
    }

    public void tank() {
        System.out.println("There are " + data[0][3] + "g in tank.");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Press enter to continue...");
        scanner.nextLine();
        clearScreen();
    }
}
