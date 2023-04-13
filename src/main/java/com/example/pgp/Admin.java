package com.example.pgp;
/*
 * Updated 4/13/23
 * Class contains methods for admin usertype
 * William Vaughan
 */

import java.util.Objects;
import java.util.Scanner;

import static com.example.pgp.PgpApplication.clearScreen;
import static com.example.pgp.PgpApplication.data;

public class Admin {
    protected int pin;               //internal
    protected int index;
    protected String name;
    protected int userType = 0;
    protected String fuel = "00.00";

    //Constructor
    public Admin(int index) {
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

            System.out.println("1.) Add User\n2.) Remove User\n3.) Check Tank Level\n4.) Change Pump Status\n5.) Exit Program");

            menuC = input.next();

            if (Objects.equals(menuC, "1")) {
                addUser();
            } else if (Objects.equals(menuC, "2")) {
                remUser();
            } else if (Objects.equals(menuC, "3")) {
                tank();
            } else if (Objects.equals(menuC, "4")) {
                pump();
            } else if (Objects.equals(menuC, "5")) {
                System.exit(0);
            } else if (Objects.equals(menuC, "end")) {
                x = true;
            } else {
                System.out.println("That is not a valid entry");
            }
        }
    }

    /**
     * Takes entered PIN, Name, and UserType and saves to CSV file
     */
    public void addUser() {
        boolean test = false;
        Scanner input = new Scanner(System.in);
        while (test != true) {
            System.out.println("Please enter a PIN\nMust be 4 digits\nor longer");
            try {
                this.pin = input.nextInt();
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
            if (String.valueOf(this.pin).length() < 4) {
                System.out.println("PIN has less then 4 digits!");
            } else {
                test = true;
            }
        }
        test = false;
        while (test != true) {
            System.out.println("Please enter a name");
            this.name = input.next();
            if (this.name == null) {
                System.out.println("Nothing was entered!");
            } else {
                test = true;
            }
        }
        test = false;
        while (test != true) {
            System.out.println("Please choose a role\n1 = Admin\n2 = User\n3 = Truck Driver");
            try {
                this.userType = input.nextInt();
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
            if (this.userType == 0) {
                System.out.println("Nothing was entered!");
            } else if (this.userType < 0 || this.userType > 3) {
                System.out.println("Entered value is not valid!");
            } else {
                test = true;
            }
        }
        String[] newRow = {String.valueOf(pin), name, String.valueOf(userType), "00.00"};
        CsvFile file = new CsvFile("src/main/java/com/example/pgp/pinData.csv");
        file.readData();
        file.addRow(newRow);
        file.writeDataInternal();
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

    /**
     * Removes user based on entered PIN
     */
    public void remUser() {
        Scanner input = new Scanner(System.in);
        String pinRem;
        System.out.println("Please enter a PIN to remove: ");
        pinRem = input.next();
        CsvFile file = new CsvFile("src/main/java/com/example/pgp/pinData.csv");
        file.readData();
        file.removeRow(pinRem);
        file.writeDataInternal();
        System.out.println("Press enter to continue...");
        input.nextLine();
    }

    /**
     * Prints current pump status and gives option to change it.
     */
    public void pump() {
        Scanner input = new Scanner(System.in);
        CsvFile file = new CsvFile("src/main/java/com/example/pgp/pinData.csv");
        String swtch;
        System.out.print("Pump is currently: ");
        if (data[1][3] == "1") {
            System.out.println("!!ON!!\n\nTurn OFF? y/n: ");
        } else {
            System.out.println("!!OFF!!\n\nTurn ON? y/n: ");
        }
        swtch = input.next();
        if (Objects.equals(swtch, "y")) {
            if (Objects.equals(data[1][3], "1")) {
                data[1][3] = "0";
                System.out.println("Pump is now !!OFF!!");
            } else {
                data[1][3] = "1";
                System.out.println("Pump is now !!ON!!");
            }
        }
        file.writeData(data);
        System.out.println("Press enter to continue...");
        input.nextLine();
        clearScreen();
    }
}
