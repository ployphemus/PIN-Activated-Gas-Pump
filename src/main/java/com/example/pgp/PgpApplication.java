package com.example.pgp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PgpApplication {

    static String verNum = "0.01";
    public static void main(String[] args) {
        SpringApplication.run(PgpApplication.class, args);
        clearScreen();
        System.out.println("Pump Control Software \nver " + verNum);

        System.out.println("\n\nPlease Enter Four Digit PIN:");

    }
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

}
