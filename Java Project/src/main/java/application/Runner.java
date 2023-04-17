package application;

import java.util.Scanner;

public class Runner {

    ProjectManagerApp projectManagerApp = new ProjectManagerApp();
     static Scanner scanner;
    public static void main(String[] args) {
        scanner = new Scanner(System.in);
    }


    public boolean yesno(String String){
        System.out.println(String);
        char ans =scanner.next().charAt(0);
        if(ans=='y'){
            return true;
        }
        else{
            return false;
        }
    }
    
}
