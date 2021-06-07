import java.io.File;
import java.util.Scanner;

public class Main {
    static Scanner scan = new Scanner(System.in);
    static String user = "guest";
    static String scanfile = "null.txt";
    public static void main(String[] args) {
        String choise="1";
        File usersfolder = new File("users");
        if (usersfolder.exists()!=true) {
            usersfolder.mkdirs();
        }
        File ticketsfolder = new File("tickets");
        if (ticketsfolder.exists()!=true) {
            ticketsfolder.mkdirs();
        }    
        while (choise!="3") {
            Misc.clear();
            Header.helpdesklogo();
            System.out.println(" ");
            System.out.println("Choose an option from the menu below: ");
            System.out.println(" ");
            System.out.println(" 1- Login ");
            System.out.println(" 2- Create a account ");
            System.out.println(" 3- Exit the system ");
            System.out.println(" ");
            System.out.print(user+"@helpdesk:~# ");
            choise = scan.nextLine().intern();
            if (choise=="1") {
                Aunthentication.loginacc();                
            } else if (choise=="2") {
                Aunthentication.createacc();
            } else if (choise=="3") {
                System.out.println("Thank you for using the help desk system... Have a great day!! ");
                Misc.sleep(1);
                System.out.println(" "); 
            } else {
                System.out.println("wrong option!! choose from 1, 2, 3 ");
                System.out.println(" ");
                Misc.sleep(1);
            }
        }
    }
}