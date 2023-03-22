package com.example.pgp;

import java.util.Objects;
import java.util.Scanner;

import static com.example.pgp.PgpApplication.data;

public class fuelTruck {
    private int index;

    public fuelTruck(int index) {
        this.index = index;
        menu();
        //TODO create truck stuff: add fuel, check lifetime fuel add, get total fuel price from API
    }
    /**
     * Creates user menu for selecting options, end returns to pin menu
     * add or subtract ifs to increase or decrease menu size
     */
    public void menu(){
        String menuC;
        boolean x = false;
        Scanner input = new Scanner(System.in);
        while(!x){
            System.out.println("Hello " + data[index][1]+ ",\nPlease choose an option\n" +
                    "or type 'end' and press\n" +
                    "enter to log out");

            System.out.println("1.) Add Fuel\n2.) Check Total Added\n3.) Check Tank Level");

            menuC = input.next();

            if(Objects.equals(menuC, "1")){addFuel();x = true;}
            else if(Objects.equals(menuC, "2")){checkAddedFuel();x = true;}
            else if(Objects.equals(menuC, "3")){}
            else if(Objects.equals(menuC, "end")){x = true;}
            else {System.out.println("That is not a valid entry");}
        }

    }
    public void addFuel(){
        System.out.println("Please enter gallons of gas you intend to add:\n1053.33\n");
        System.out.println("Estimated cost per gallon is $3.50 for a total cost of $3686.66");
        csvFile file = new csvFile("src/main/java/com/example/pgp/pinData.csv");
        data[index][3] = String.valueOf(Double.parseDouble(data[index][3]) + 55.33);
        file.writeData(data);
    }

    public void checkAddedFuel(){
        //hard code for proto-display
        System.out.println("Driver has delivered 123841 G of fuel");
    }
}
