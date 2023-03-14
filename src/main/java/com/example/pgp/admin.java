package com.example.pgp;

import java.util.Objects;
import java.util.Scanner;
import static com.example.pgp.PgpApplication.data;

public class admin {
    protected int pin;
    protected int index;
    protected String name;
    protected int userType = 0;
    protected String fuel = "00.00";
    public admin(int index) {
        this.index = index;
        menu();
        //TODO create admin stuff: delete user, turn pump on/off
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

            System.out.println("1.) Add User\n2.) Remove User\n3.) Check Tank Level\n4.) Change Pump Status");

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
        boolean test = false;
        Scanner input = new Scanner(System.in);
        while(test != true) {
            System.out.println("Please enter a PIN\nMust be 4 digits\nor longer");
            try {
                this.pin = input.nextInt();
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
            if (String.valueOf(this.pin).length() < 4){
                System.out.println("PIN has less then 4 digits!");
            }
            else{test = true;}
        }
        test = false;
        while(test != true) {
            System.out.println("Please enter a name");
            this.name = input.next();
            if (this.name == null){
                System.out.println("Nothing was entered!");
            }
            else{test = true;}
        }
        test = false;
        while(test != true) {
            System.out.println("Please choose a role\n1 = Admin\n2 = User\n3 = Truck Driver");
            try {
                this.userType = input.nextInt();
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
            if (this.userType == 0){
                System.out.println("Nothing was entered!");
            }
            else if(this.userType< 0 || this.userType> 3){
                System.out.println("Entered value is not valid!");
            }
            else{test = true;}
        }
        String[] newRow = {String.valueOf(pin),name,String.valueOf(userType),"00.00"};
        csvFile file = new csvFile("src/main/java/com/example/pgp/pinData.csv");
        file.readData();
        file.addRow(newRow);
        file.writeDataInternal();
    }

    public void remUser(){

    }
    public void pump(){

    }
}
