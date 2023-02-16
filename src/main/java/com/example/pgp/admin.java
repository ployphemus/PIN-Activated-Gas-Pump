package com.example.pgp;

import java.util.Objects;
import java.util.Scanner;

public class admin {
    protected String pin;
    protected String name;
    protected String userType;
    protected String fuel = "00.00";
    public admin() {

        //TODO create admin stuff: add delete user, turn pump on/off
    }
    public void menu(){
        String menuC;
        boolean x = false;
        while(!x){
            System.out.println("Please choose an option\n" +
                               "or type 'end' and press\n" +
                               "enter to log out");

            System.out.println("1.) Add User\n2.) Remove User\n3.) Check Tank Level\n4.) Change Pump Status");
            Scanner input = new Scanner(System.in);
            menuC = input.next();

            if(Objects.equals(menuC, "1")){addUser();}
            else if(Objects.equals(menuC, "2")){}
            else if(Objects.equals(menuC, "3")){}
            else if(Objects.equals(menuC, "4")){}
            else if(Objects.equals(menuC, "end")){x = true;}
            else {System.out.println("That is not a valid entry");}
        }
    }
    public void addUser(){
        Boolean x = false;

        while(x != true){

        }
    }

    public void remUser(){

    }
    public void pump(){

    }
}
