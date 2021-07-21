import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class Accounts {
   
    //global variable
    static String userName="Guest";
    
    //create a account
    public void createAccount () {

        //variables
        int choise=0;
        String fName=null;
        String lName=null;
        String uName=null;
        String email=null;
        String sklexp=null;
        String dsgntn=null;
        String dptmnt=null;
        String psswd=null;
        String cnfmPsswd=null;

        //objects
        File userFile = new File("Users/"+uName+".txt");

        //menu
        System.out.println("Choose from the menu below: ");
        System.out.println("(1) Create a account for client");
        System.out.println("(2) Create a account for technician");

        //taking inputs
        while (choise!=1&&choise!=2) {
            Misc.prompt("/");
            choise = Misc.input.nextInt(); Misc.input.nextLine();
            switch (choise) {
                case 1:
                    System.out.print("First Name: ");
                    fName = Misc.input.nextLine();
                    System.out.print("Last Name: ");
                    lName = Misc.input.nextLine();
                    while (uName==null||userFile.exists()) {
                        System.out.print("User Name: ");
                        uName = Misc.input.nextLine();
                        userFile = new File("Users/"+uName+".txt");
                        if (userFile.exists()) {
                            System.out.println("'"+uName+"' - This user name is already taken... choose another!! ");
                        }
                    }
                    System.out.print("Email: ");
                    email = Misc.input.nextLine();
                    System.out.print("Designation: ");
                    dsgntn = Misc.input.nextLine();
                    System.out.print("Department: ");
                    dptmnt = Misc.input.nextLine();
                break;
                case 2:
                    System.out.print("First Name: ");
                    fName = Misc.input.nextLine();
                    System.out.print("Last Name: ");
                    lName = Misc.input.nextLine();
                    while (uName==null||userFile.exists()) {
                        System.out.print("User Name: ");
                        uName = Misc.input.nextLine();
                        userFile = new File("Users/"+uName+".txt");
                        if (userFile.exists()) {
                            System.out.println("'"+uName+"' - This user name is already taken... choose another!! ");
                        }
                    }
                    System.out.print("Skill and experience: ");
                    sklexp = Misc.input.nextLine();                  
                break;
                default:
                    System.out.println("'"+choise+"' is not a choise from the menu... please choose a choise from the menu!!");
                break;
            }
        }
        while (psswd==null||psswd!=cnfmPsswd) {
            System.out.print("Password: ");
            psswd = Misc.input.nextLine().intern();
            System.out.print("Confirm Password: ");
            cnfmPsswd = Misc.input.nextLine().intern();
            if (psswd!=cnfmPsswd) {
                System.out.println("Your password and confirm password didn't match... Please try again");
            }
        }

        //write account data to a file
        try {
            FileWriter fWriter = new FileWriter("Users/"+uName+".txt");
            BufferedWriter bWriter = new BufferedWriter(fWriter);
            bWriter.write("User Account Data");
            bWriter.newLine();
            bWriter.write("-----------------");
            bWriter.newLine();
            bWriter.write("First Name: "+fName);
            bWriter.newLine();
            bWriter.write("Last Name: "+lName);
            bWriter.newLine();
            bWriter.write("User Name: "+uName);
            bWriter.newLine();
            bWriter.write("Password: "+psswd);
            bWriter.newLine();
            if (choise==1) {
                bWriter.write("Account Type: Client");
                bWriter.newLine();
                bWriter.write("Email: "+email);
                bWriter.newLine();
                bWriter.write("Designation: "+dsgntn);
                bWriter.newLine();
                bWriter.write("Department: "+dptmnt);            
            } else {
                bWriter.write("Account Type: Technician");
                bWriter.newLine();
                bWriter.write("skill and experience: "+sklexp);                
            }
            bWriter.close();
            System.out.println("User account sucessfully created");
        } catch (Exception e) {
            System.out.println("An Unknown error has occured");
        }
        Misc.sleep(1);

    }

    //login to your account
    public void logIn () {

        //variables
        String Uname=null;
        String psswd=null;
        String rpsswd=null;
        String accType=null;

        //objects
        File userFile = new File("Users/"+Uname+".txt");
        Desk desk = new Desk();

        //validating user name
        while (userFile.exists()==false) {
            System.out.print("User Name: ");
            Uname=Misc.input.nextLine();
            userFile = new File("Users/"+Uname+".txt");
            if (userFile.exists()==false) {
                System.out.println("'"+Uname+"' - this user doesn't exists in the database");
            }
        }

        //fetch real data
        try {
            Scanner fileScanner = new Scanner(userFile);
            for (int i = 0; i < 5; i++) {
                fileScanner.nextLine();
            }
            rpsswd=fileScanner.nextLine().substring(10).intern();
            accType=fileScanner.nextLine().substring(14).intern();
            fileScanner.close();
        } catch (Exception e) {
            System.out.println("An unknown error has occured... Please try again later!! ");
        }

        //verify the password
        while (psswd==null||psswd!=rpsswd) {
            System.out.print("Password: ");
            psswd=Misc.input.nextLine().intern();
            if (psswd!=rpsswd) {
                System.out.println("The password you enter is wrong!! check again... ");
            }
        }

        //change user name and forward to the desk
        userName=Uname;
        if (accType=="Client") {
            desk.clientDesk();
        } else {
            desk.techDesk();
        }
    }

    //logout from the account
    public void logout () {
        userName = "guest";
    }

    //delete the user account
    public void deleteAccount () {
        
        //objects
        File userFile = new File("Users/"+userName+".txt");

        //delete user account
        if (userFile.delete()) {
            System.out.println("user account has been successfully deleted");
        } else {
            System.out.println("An unknown error has occured!! Please login and try again... ");
        }
        Misc.sleep(2);

        //logout
        logout();

    }


}
