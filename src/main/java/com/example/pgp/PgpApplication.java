package com.example.pgp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Objects;
import java.util.Scanner;

//!!!first two rows in csv array are tank and pump, do not modify!!!

@SpringBootApplication
public class PgpApplication {
    static String[][] data;
    static String verNum = "0.03";

    public static void main(String[] args) {
        SpringApplication.run(PgpApplication.class, args);
        int keepOpen = 1;
        String pin;
        int index;
        csvFile file = new csvFile("src/main/java/com/example/pgp/pinData.csv"); //initialize csvFile and get csv file contents
        Scanner input = new Scanner(System.in);
        while(keepOpen == 1) {

            file.readData();        //store csv to class array/reload array from modified csv file
            data = file.getData();  //store csv file to 2d array for public access
            clearScreen();
            System.out.println("Pump Control Software \nver " + verNum);

            System.out.println("\n\nPlease Enter Four Digit PIN:");   //get pin and match with db to figure out role
            pin = input.next();
            index = checkPin(pin);

            if(index != 0){
                if(Objects.equals(data[index][2], "1")){
                    new admin(index);
                }
                else if(Objects.equals(data[index][2], "2")){
                    new user(index);
                }
                else if(Objects.equals(data[index][2], "3")){
                    new fuelTruck(index);
                }
                else{
                    System.out.println("!CSV file may be corrupted,\nplease have an admin check it!");
                }
            }
        }
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static int checkPin(String pin){
        for(int i = 2;i< data.length;i++){ //skips pump and tank entries
            if(Objects.equals(data[i][0], pin)){
                System.out.println("PIN authenticated");
                return i;
            }
        }
        System.out.println("PIN !!NOT!! authenticated!");
        clearScreen();
        return 0;
    }

}
