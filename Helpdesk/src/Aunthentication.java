import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Aunthentication {
    static void createacc() {
        String username = "config";
        File userfile = new File("users/"+username+".txt");
        System.out.print("First Name: ");
        String firstname = Main.scan.nextLine().intern();
        System.out.print("Last Name: ");
        String lastname = Main.scan.nextLine().intern();
        while (username=="config" || userfile.exists()) {
            System.out.print("User Name: ");
            username = Main.scan.nextLine().intern();
            userfile = new File("users/"+username+".txt");
            if (username=="config" || userfile.exists()) {
                System.out.println(" ");
                System.out.println("This user name is already using by other user... Choose another user name!! ");
                System.out.println(" ");
            }
        }
        String techchoise = "NA";
        while (techchoise!="y" && techchoise!="Y" && techchoise!="n" && techchoise!="N") {
            System.out.print("Are you a technician working for helpdesk system ( Y / N ): ");
            techchoise = Main.scan.nextLine().intern();
            if (techchoise!="y" && techchoise!="Y" && techchoise!="n" && techchoise!="N") {
                System.out.println("wrong option!! choose from Y / N ");
            }
        }
        String password = null;
        String confirmpassword = null;
        while (password!=confirmpassword || password==null) {
            System.out.print("password: ");
            password = Main.scan.nextLine().intern();
            System.out.print("Confirm the password: ");
            confirmpassword = Main.scan.nextLine().intern();
            if (password!=confirmpassword || password==null) {
                System.out.println(" ");
                System.out.println("password didn't match... Try again!!");
                System.out.println(" ");
            }
        }
        try {
            FileWriter fwriter = new FileWriter("users/"+username+".txt");
            BufferedWriter writer = new BufferedWriter(fwriter);
            writer.write(firstname);
            writer.newLine();
            writer.write(lastname);
            writer.newLine();
            writer.write(username);
            writer.newLine();
            writer.write(techchoise);
            writer.newLine();
            writer.write(password);
            writer.close();
            System.out.println(" ");
            System.out.println("Your account has been successfully created... Please login!!");
            System.out.println(" ");
            Misc.sleep(2);
        } catch (IOException e) {
            System.out.println(" ");
            System.out.println("Unable to create the account... please try again later!! ");
            System.out.println(" ");
            Misc.sleep(2);
        }
    }
    static void loginacc() {
        File userfile = new File("users/"+Main.user+".txt");
        while (Main.user=="guest" || userfile.exists()==false) {
            System.out.print("user name: ");
            Main.user = Main.scan.nextLine();
            userfile = new File("users/"+Main.user+".txt");
            if (Main.user=="guest" || userfile.exists()==false) {
                System.out.println(" ");
                System.out.println("This user name doesn't exist... Please create a account if you don't have one!! ");
                System.out.println(" ");
            }
        }

        String realpassword = null;
        String password = null;
        String techchoice = null;
        try {
            Scanner scanfile = new Scanner(userfile);
            scanfile.nextLine();
            scanfile.nextLine();
            scanfile.nextLine();
            techchoice = scanfile.nextLine().intern();
            realpassword = scanfile.nextLine().intern();
            scanfile.close();
        } catch (Exception e) {
            
        }
        while (password==null || password!=realpassword) {
            System.out.print("Password: ");
            password = Main.scan.nextLine().intern();
            if (password==null || password!=realpassword) {
                System.out.println("The password is wrong... Try again!! ");
            }
        }
        if (techchoice=="y" || techchoice=="Y") {
            Desksystems.technician();
        } else {
            Desksystems.client();
        }
    }
    static void logoutacc() {
        Main.user="guest";
    }
    static void deleteacc() {
        File account = new File("users/"+Main.user+".txt"); 
        if (account.delete()) { 
            System.out.println("Account seccessfully deleted!! ");
            Misc.sleep(2);
        } else {
            System.out.println("Failed to delete the account... try again leter!! ");
            Misc.sleep(2);
        }
        Main.user="guest";
    }
}