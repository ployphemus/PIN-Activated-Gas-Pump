package com.example.pgp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//first two rows in csv array are tank and pump, do not modify

@SpringBootApplication
public class PgpApplication {
    public static String[][] data;
    static String verNum = "0.01";
    public static void main(String[] args) {
        SpringApplication.run(PgpApplication.class, args);
        clearScreen();
        System.out.println("Pump Control Software \nver " + verNum);
        csvFile file = new csvFile("pinData.csv");                                                               //initialize csvFile and get csv file contents
        data = file.getData();                                                                                          //store csv file to 2d array for public access
        System.out.println("\n\nPlease Enter Four Digit PIN:");                                                         //get pin and match with db to figure out role
        clearScreen();

    }
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

}
