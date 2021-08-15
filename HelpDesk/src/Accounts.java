import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class Accounts {

    //global variable
    static String userName="Guest";

    //private attributes
    private String firstName = null;
    private String lastName = null;
    private String uName = null;
    private String password = null;
    private String accType = null;

    //create a account
    public void createAccount () {

        //variables
        int choice=0;

        String cnfmPsswd=null;

        //menu
        System.out.println("Choose from the menu below: ");
        System.out.println("(1) Create a account for client");
        System.out.println("(2) Create a account for technician");

        //objects
        Client client = new Client();
        Technician technician = new Technician();

        //taking inputs
        boolean finished = false;
        while (!finished) {
            Misc.prompt("/");
            choice = Misc.input.nextInt(); Misc.input.nextLine();
            switch (choice) {
                case 1:
                    System.out.print("First Name: ");
                    firstName = Misc.input.nextLine();
                    System.out.print("Last Name: ");
                    lastName = Misc.input.nextLine();
                    checkUsername();
                    System.out.print("Email: ");
                    client.createClient();
                    finished = true;
                    break;
                case 2:
                    System.out.print("First Name: ");
                    firstName = Misc.input.nextLine();
                    System.out.print("Last Name: ");
                    lastName = Misc.input.nextLine();
                    checkUsername();
                    technician.setSkillAndExp();
                    finished = true;
                    break;
                default:
                    System.out.println("'"+choice+"' is not a choice from the menu... please choose a choice from the menu!!");
                    break;
            }
        }
        while (password == null|| !password.equals(cnfmPsswd)) {
            System.out.print("Password: ");
            password = Misc.input.nextLine().intern();
            System.out.print("Confirm Password: ");
            cnfmPsswd = Misc.input.nextLine().intern();
            if (!password.equals(cnfmPsswd)) {
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
            bWriter.write("First Name: "+firstName);
            bWriter.newLine();
            bWriter.write("Last Name: "+lastName);
            bWriter.newLine();
            bWriter.write("User Name: "+uName);
            bWriter.newLine();
            bWriter.write("Password: "+password);
            bWriter.newLine();
            if (choice==1) {
                bWriter.write("Account Type: Client");
                bWriter.newLine();
                bWriter.write("Email: "+client.getEmail());
                bWriter.newLine();
                bWriter.write("Designation: "+client.getDesignation());
                bWriter.newLine();
                bWriter.write("Department: "+client.getDepartment());
            } else {
                bWriter.write("Account Type: Technician");
                bWriter.newLine();
                bWriter.write("skill and experience: "+technician.getSkillAndExp());
            }
            bWriter.close();
            System.out.println("User account sucessfully created");
        } catch (Exception e) {
            System.out.println("An Unknown error has occured");
        }
        Misc.sleep(1);

    }

    //method to check whether a username exists
    public void checkUsername() {
        File userFile = new File("Users/"+uName+".txt");

        while (uName==null||userFile.exists()) {
            System.out.print("User Name: ");
            uName = Misc.input.nextLine();
            userFile = new File("Users/"+uName+".txt");
            if (userFile.exists()) {
                System.out.println("'"+uName+"' - This user name is already taken... choose another!! ");
            }
        }
    }

    //login to your account
    public void logIn () {

        //variables
        String rpsswd=null;

        //objects
        File userFile = new File("Users/"+uName+".txt");
        Client client = new Client();
        Technician technician = new Technician();

        //validating user name
        boolean exists = false;

        while (!exists) {
            System.out.println("User Name: ");
            uName = Misc.input.nextLine();
            userFile = new File("Users/"+ uName +".txt");

            if (!userFile.exists()) {
                System.out.println("'"+ uName +"' - this user doesn't exists in the database");
            } else {
                exists = true;
            }
        }

        //fetch real data
        try {
            Scanner fileScanner = new Scanner(userFile);
            for (int i = 0; i < 5; i++) {
                fileScanner.nextLine();
            }
            rpsswd = fileScanner.nextLine().substring(10).intern();
            accType = fileScanner.nextLine().substring(14).intern();
            fileScanner.close();
        } catch (Exception e) {
            System.out.println("An unknown error has occurred... Please try again later!! ");
        }

        //verify the password
        boolean passwordVerified = false;

        while (!passwordVerified) {
            System.out.print("Password: ");
            password = Misc.input.nextLine().intern();
            if (!password.equals(rpsswd)) {
                System.out.println("The password you entered is wrong!! check again... ");
            } else {
                passwordVerified = true;
            }
        }

        //change user name and forward to the desk
        userName = uName;

        if ("Client".equals(accType)) {
            client.clientDesk();
        } else {
            technician.techDesk();
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
            System.out.println("An unknown error has occurred!! Please login and try again... ");
        }
        Misc.sleep(2);

        //logout
        logout();

    }


}
