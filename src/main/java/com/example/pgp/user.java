package com.example.pgp;

import java.util.Objects;
import java.util.Scanner;

import static com.example.pgp.PgpApplication.data;

public class user {
    protected int index;

    public user(int index) {
        this.index = index;
        menu();
        //TODO create user access stuff: pump gas, check lifetime pump use
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

            System.out.println("1.) Pump Gas\n2.) Check Total Used\n3.) Check Tank Level");

            menuC = input.next();

            if(Objects.equals(menuC, "1")){}
            else if(Objects.equals(menuC, "2")){}
            else if(Objects.equals(menuC, "3")){}
            else if(Objects.equals(menuC, "end")){x = true;}
            else {System.out.println("That is not a valid entry");}
        }

    }
    public void getFuel(){

    }
    public void checkFuel(){

    }

}
