package com.example.pgp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Objects;

//!!!first two rows in csv array are tank and pump, do not modify!!!

@SpringBootApplication
public class PgpApplication {
    static String[][] data;
    static String verNum = "0.02";

    public static void main(String[] args) {
        SpringApplication.run(PgpApplication.class, args);
        int keepOpen = 1;
        csvFile file = new csvFile("pinData.csv"); //initialize csvFile and get csv file contents
        data = file.getData();  //store csv file to 2d array for public access
        while(keepOpen == 1) {
            clearScreen();
            System.out.println("Pump Control Software \nver " + verNum);

            System.out.println("\n\nPlease Enter Four Digit PIN:");   //get pin and match with db to figure out role

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
        return 0;
    }

}
