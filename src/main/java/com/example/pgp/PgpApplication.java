package com.example.pgp;
/*
 * Updated 4/13/23
 *
 * Class contains main methods and springboot initializer.
 * William Vaughan
 * Greyson Williams
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

//!!!first two rows in csv array are tank and pump, do not modify!!!

@SpringBootApplication
public class PgpApplication {
    static String[][] data;
    static String verNum = "0.1";

    /**
     * Initializes main menu
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        //Springboot not currently needed as API is accessed through pure java and JSON
        //SpringApplication.run(PgpApplication.class, args);
        int keepOpen = 1;
        String pin;
        int index;
        //initialize csvFile and get csv file contents
        CsvFile file = new CsvFile("src/main/java/com/example/pgp/pinData.csv");
        Scanner input = new Scanner(System.in);
        while (keepOpen == 1) {

            //store csv to class array/reload array from modified csv file
            file.readData();
            //store csv file to 2d array for public access
            data = file.getData();
            clearScreen();
            System.out.println("Pump Control Software \nver " + verNum);

            System.out.println("\n\nPlease Enter Four Digit PIN:");
            //get pin and match with db to figure out role
            pin = input.next();
            index = checkPin(pin);

            if (index != 0) {
                if (Objects.equals(data[index][2], "1")) {
                    new Admin(index);
                } else if (Objects.equals(data[index][2], "2")) {
                    new User(index);
                } else if (Objects.equals(data[index][2], "3")) {
                    new FuelTruck(index);
                } else {
                    System.out.println("!CSV file may be corrupted,\nplease have an admin check it!");
                }
            }
        }
    }

    /**
     * Clears term screen.
     * (Does not function in IntelliJ run console)
     */
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * Checks PIN against column 0 of array
     *
     * @param pin PIN to check against array
     * @return int line index of authenticated PIN
     */
    public static int checkPin(String pin) {
        //skips pump and tank entries
        for (int i = 2; i < data.length; i++) {
            if (Objects.equals(data[i][0], pin)) {
                System.out.println("PIN authenticated");
                return i;
            }
        }
        System.out.println("PIN !!NOT!! authenticated!");
        clearScreen();
        return 0;
    }

}
