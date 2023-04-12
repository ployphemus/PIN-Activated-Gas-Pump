package com.example.pgp;
/*
  Updated 4/11/23

  Class contains methods for Driver usertype
  William Vaughan
  ???
 */

import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

import static com.example.pgp.GasApi.getGasPrice;
import static com.example.pgp.PgpApplication.clearScreen;
import static com.example.pgp.PgpApplication.data;

public class FuelTruck {
    private int index;

    public FuelTruck(int index) throws IOException {
        this.index = index;
        menu();
    }

    /**
     * Creates user menu for selecting options, end returns to pin menu
     * add or subtract ifs to increase or decrease menu size
     */
    public void menu() throws IOException {
        String menuC;
        boolean x = false;
        Scanner input = new Scanner(System.in);
        while (!x) {
            System.out.println("Hello " + data[index][1] + ",\nPlease choose an option\n" +
                    "or type 'end' and press\n" +
                    "enter to log out");

            System.out.println("1.) Add Fuel\n2.) Check Total Added\n3.) Check Tank Level");

            menuC = input.next();

            if (Objects.equals(menuC, "1")) {
                addFuel();
                x = true;
            } else if (Objects.equals(menuC, "2")) {
                checkAddedFuel();
                x = true;
            } else if (Objects.equals(menuC, "3")) {
            } else if (Objects.equals(menuC, "end")) {
                x = true;
            } else {
                System.out.println("That is not a valid entry");
            }
        }

    }

    public void addFuel() throws IOException {
        Scanner input = new Scanner(System.in);
        Boolean tooBig = true;
        double gasP = getGasPrice(35.9132, -79.0558);
        double amountAval = 50000 - Double.parseDouble(data[0][3]);
        System.out.println("Please enter gallons of gas you intend to add:");
        int gAmount;
        while (tooBig) {
            gAmount = input.nextInt();
            if (gAmount + Double.parseDouble(data[0][3]) > 50000) {
                System.out.println("!Added amount will overflow tank!\nPlease enter an amount smaller than "
                        + amountAval + "g");
            } else {
                System.out.println("Estimated total cost of fuel is: $" + gAmount * gasP);
                CsvFile file = new CsvFile("src/main/java/com/example/pgp/pinData.csv");
                data[index][3] = String.valueOf(Double.parseDouble(data[index][3]) + 55.33);
                file.writeData(data);
                tooBig = false;
            }
        }
        System.out.println("Press enter to continue...");
        input.nextLine();
        clearScreen();
    }

    public void checkAddedFuel() {
        System.out.printf("Driver has delivered %10.2f G of fuel\n", Double.parseDouble(data[index][3]));
        Scanner scanner = new Scanner(System.in);
        System.out.println("Press enter to continue...");
        scanner.nextLine();
        clearScreen();
    }

    public void tank() {
        System.out.println("There are " + data[0][3] + "g in tank.");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Press enter to continue...");
        scanner.nextLine();
        clearScreen();
    }
}
