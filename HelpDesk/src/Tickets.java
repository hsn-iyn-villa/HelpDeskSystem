import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Tickets {
    

    //global variables
    public int ticketNumber=0;
    public String ticketCreatedBy=null;
    public String status=null;
    public String severity=null;
    public int amountOfPeopleRated=0;
    public Double totalRating=0.0;
    public Double ticketRating=0.0;
    public ArrayList<String> msg = new ArrayList<String>();

    public void createTicket () {

        //variable
        int choise=0;
        int cnt=1;
        String tpIssue=null;
        String dscpIssue=null;
        String svrty=null;

        //objects
        File ticketFile = new File("Tickets/T000"+cnt+".txt");

        //check for the available ticket number
        while (ticketFile.exists()) {
            cnt++;
            ticketFile = new File("Tickets/T000"+cnt+".txt");
        }

        //taking inputs needed for creating the ticket
        System.out.print("Type of the issue: ");
        tpIssue=Misc.input.nextLine();
        System.out.print("Description of the issue: ");
        dscpIssue=Misc.input.nextLine();
        System.out.println("Severity of the issue: ");
        System.out.println("(1) High");
        System.out.println("(2) Medium");
        System.out.println("(3) Low");
        while (choise!=1&&choise!=2&&choise!=3) {
            System.out.print("severity: ");
            choise=Misc.input.nextInt();Misc.input.nextLine();
            switch (choise) {
                case 1:
                    svrty="High";
                break;
                case 2:    
                    svrty="Medium";
                break;
                case 3:
                    svrty="Low";
                break;
                default:
                    System.out.println("'"+choise+"' is not a choise from the main menu... please choose a choise from the menu!!");
                break;
            }
        }

        //write the ticket
        //write account data to a file
        try {
            FileWriter fWriter = new FileWriter(ticketFile);
            BufferedWriter bWriter = new BufferedWriter(fWriter);
            bWriter.write("Ticket Data");
            bWriter.newLine();
            bWriter.write("-----------------");
            bWriter.newLine();
            bWriter.write("Ticket Created By: "+Accounts.userName);
            bWriter.newLine();
            bWriter.write("Status: Assigned[all]");
            bWriter.newLine();
            bWriter.write("Severity: "+svrty);
            bWriter.newLine();
            bWriter.write("Amount of people rated: 0");
            bWriter.newLine();
            bWriter.write("Total rating: 0");
            bWriter.newLine();
            bWriter.newLine();
            bWriter.write(tpIssue);
            bWriter.newLine();
            bWriter.write(Accounts.userName+"(Client): "+dscpIssue);
            bWriter.close();
            System.out.println("Ticket sucessfully created... your ticket number is T000"+cnt);
        } catch (Exception e) {
            System.out.println("An Unknown error has occured");
        }
        Misc.sleep(1);

    }

    //fetch ticket data
    public void fetch(int tNum) {
        //objects
        File tFile = new File("Tickets/T000"+tNum+".txt");

        //fetch ticket data
        try {
            Scanner fScanner = new Scanner(tFile);
            fScanner.nextLine();
            fScanner.nextLine();
            ticketCreatedBy=fScanner.nextLine().substring(19).intern();
            status=fScanner.nextLine().substring(8).intern();
            severity=fScanner.nextLine().substring(10).intern();
            amountOfPeopleRated=Integer.parseInt(fScanner.nextLine().substring(24));
            totalRating=Double.parseDouble(fScanner.nextLine().substring(14));
            fScanner.nextLine();
            while (fScanner.hasNextLine()) {
                msg.add(fScanner.nextLine());
            }
            fScanner.close();
            if (amountOfPeopleRated>0) {
                ticketRating=totalRating/amountOfPeopleRated;
            }
        } catch (Exception e) {
            System.out.println("An error has occured");
        }
    }

    //render the ticket
    public void view () {
        
        //render ticket
        Misc.clearScreen();
        if (Objects.equals(status, "Deleted")) {
            //do nothing.... prompt will be in parent function
        } else {
            System.out.println();
            System.out.println("----------------------------------");
            System.out.println();
            System.out.println("Ticket Number: T000"+ticketNumber);
            System.out.println("Ticket Created By: "+ticketCreatedBy);
            System.out.println("Ticket Status: "+status);
            System.out.println("Ticket Severity: "+severity);
            System.out.println("Rating: "+ticketRating);
            for (int i = 0; i < msg.size(); i++) {
                System.out.println(msg.get(i));
            }
            System.out.println();
            System.out.println("----------------------------------");
            System.out.println();
            System.out.println(); 
        }

    }

    //recent tickets
    public void recent () {

        //variables
        String choise=null;

        //objects
        File tFile = new File("Tickets/T0001.txt");

        //count the number of tickets
        ticketNumber=1;
        while (tFile.exists()) {
            ticketNumber++;
            tFile = new File("Tickets/T000"+ticketNumber+".txt");
        }
        ticketNumber--;

        //render the recent tickets
        while (choise!="EX") {
            tFile = new File("Tickets/T000"+ticketNumber+".txt");
            if(tFile.exists()) {
                fetch(ticketNumber);
                view();
                if (status=="Deleted") {
                    if (choise=="NT") {
                        ticketNumber++;
                    } else if (choise=="PR" || choise==null) {
                        ticketNumber--;
                    } else if (choise=="MD") {
                        System.out.println("The ticket has been succesfully deleted!!");
                        Misc.sleep(1);
                        choise="EX";
                    }
                    semireset();
                } else {
                    System.out.println(" [FB] Feedback     [RT] Rate     [MC] Mark Complete     [MD] Mark Delete ");
                    System.out.println(" [PR] Previous     [NT] Next     [EX] Exit ");
                    Misc.prompt(" (T000"+ticketNumber+") ");
                    choise=Misc.input.nextLine().toUpperCase().intern();
                    switch (choise) {
                        case "PR":
                            ticketNumber--;
                        break;
                        case "NT":
                            ticketNumber++;
                        break;
                        case "FB":
                            feedback();
                        break;
                        case "RT":
                            rate();    
                        break;
                        case "MC":
                            complete();
                        break;
                        case "MD":
                            delete();
                        break;
                        case "EX":
                            System.out.println("Exit");
                            Misc.sleep(1);
                            reset();
                        break;
                        default:
                            System.out.println("'"+choise+"' is not a choise from the main menu... please choose a choise from the menu!!");
                            Misc.sleep(1);
                        break;
                    }
                }
                semireset();
            } else {
                choise="EX";
            }
        }

    }

    //search ticket
    public void search() {

        //variable
        String tNum=null;
        String choise=null;

        //objects
        File tFile = new File("Tickets/"+tNum+".txt");

        //check the availability if the ticket
        while (tFile.exists()==false) {
            System.out.print("Ticket Number: ");
            tNum=Misc.input.nextLine().toUpperCase();
            tFile = new File("Tickets/"+tNum+".txt");
            if (tFile.exists()==false) {
                System.out.println("'"+tNum+"' - this ticket doesn't exists");
            } else {
                ticketNumber=Integer.parseInt(tNum.substring(3));
            }
        }

        //menu
        while (choise!="EX") {
            //rendering the ticket
            fetch(ticketNumber);
            view();
            if (Objects.equals(status, "Deleted")) {
                reset();
                choise="EX";
                System.out.println("This ticket has been deleted!!");
                Misc.sleep(2);
            } else {
                System.out.println(" [FB] Feedback       [RT] Rate       [MC] Mark Complete ");
                System.out.println(" [MD] Mark Delete    [EX] Exit");
                System.out.println();
                Misc.prompt(" ( T000"+ticketNumber+" ) ");
                choise=Misc.input.nextLine().toUpperCase().intern();
                switch (choise) {
                    case "FB":
                        feedback();
                    break;
                    case "RT":
                        rate();    
                    break;
                    case "MC":
                        complete();
                    break;
                    case "MD":
                        delete();
                    break;
                    case "EX":
                        reset();
                    break;
                    default:
                        System.out.println("'"+choise+"' is not a choise from the menu... please choose a choise from the menu!!");
                        Misc.sleep(1);
                    break;
                }
                semireset();
            }
        }

    }

    //reset every thing exept ticket Number
    public void semireset() {
        ticketCreatedBy=null;
        status=null;
        severity=null;
        amountOfPeopleRated=0;
        totalRating=0.0;
        ticketRating=0.0;
        msg.clear();
    }

    //reset every thing of the ticket
    public void reset() {
        ticketNumber=0;
    }

    //feedback by clients
    public void feedback() {

        //variables
        String fdMsg=null;

        //taking input
        System.out.print("Message: ");
        fdMsg=Misc.input.nextLine();

        //add feedback to message array
        msg.add(Accounts.userName+"(Client): "+fdMsg);

        //write to ticket file
        write();

    }

    //rate ticket
    public void rate() {

        //variables
        Double rating=-1.0;

        //taking input
        while (rating<0.0 || rating>5.0) {
            System.out.println("Choose between 0.00 to 5.00");
            System.out.print("Rate: ");
            rating=Misc.input.nextDouble();Misc.input.nextLine();
            if (rating<0.0 || rating>5.0) {
                System.out.println("'"+rating+"' is not a suitable amount!! choose between 0.00 - 5.00 ");
            }
        }

        //calculations
        amountOfPeopleRated++;
        totalRating=totalRating+rating;

        //write to ticket file
        write();

    }

    //ticket status to complete
    public void complete() {

        //checking if user created and edit is same
        if (Objects.equals(Accounts.userName, ticketCreatedBy)) {
            
            //changing status to complete
            status="Completed";

            //write to file
            write();

        } else {
            
            System.out.println("Since this ticket is not created by you, you can't change status of the ticket!! ");
            Misc.sleep(2);

        }

    }

    //ticket status to delete
    public void delete() {

        //checking if user created and edit is same
        if (Objects.equals(Accounts.userName, ticketCreatedBy)) {
            
            //changing status to complete
            status="Deleted";

            //write to file
            write();

        } else {
            
            System.out.println("Since this ticket is not created by you, you can't change status of the ticket!! ");
            Misc.sleep(2);

        }

    }

    //write the data to ticket
    public void write() {

        //writing to ticket file
        try {
            FileWriter fWriter = new FileWriter("Tickets/T000"+ticketNumber+".txt");
            BufferedWriter bWriter = new BufferedWriter(fWriter);
            bWriter.write("Ticket Data");
            bWriter.newLine();
            bWriter.write("-----------------");
            bWriter.newLine();
            bWriter.write("Ticket Created By: "+ticketCreatedBy);
            bWriter.newLine();
            bWriter.write("Status: "+status);
            bWriter.newLine();
            bWriter.write("Severity: "+severity);
            bWriter.newLine();
            bWriter.write("Amount of people rated: "+amountOfPeopleRated);
            bWriter.newLine();
            bWriter.write("Total rating: "+totalRating);
            bWriter.newLine();
            for (int i = 0; i < msg.size(); i++) {
                bWriter.newLine();
                bWriter.write(msg.get(i));
            }
            bWriter.close();
        } catch (Exception e) {
            System.out.println("And unknown error has occured");
        }

    }

    //attends tickets by recent tickets
    public void attendRecent() {

        //variables
        String choise=null;
        int count=1;
        
        //objects
        File tFile = new File("Tickets/T0001.txt");

        //count ticket files
        while (tFile.exists()) {
            count++;
            tFile = new File("Tickets/T000"+count+".txt");
        }
        count--;

        //assigned tickets
        for (int i = 0; i < count; i++) {
            ticketNumber=i+1;
            fetch(i+1);
            if (Objects.equals("Assigned["+Accounts.userName+"]", status)) {
                view();
                System.out.println("[AD] Attend     [AN] Assign     [ANYKEY with ENTER] Skip");
                Misc.prompt(" [ Assigned Tickets ] ");
                choise=Misc.input.nextLine().toUpperCase().intern();
                switch (choise) {
                    case "AD":
                        attend();
                    break;
                    case "AN":
                        assign();
                    break;
                    default:
                        
                    break;
                }
            }
            semireset();
        }
        reset();

        //high severity tickets
        for (int i = 0; i < count; i++) {
            ticketNumber=i+1;
            fetch(i+1);
            if (Objects.equals("High", severity)&&Objects.equals("Assigned[all]",status)) {
                view();
                System.out.println("[AD] Attend     [AN] Assign     [ANYKEY with ENTER] Skip");
                Misc.prompt(" [ High Severity ] ");
                choise=Misc.input.nextLine().toUpperCase().intern();
                switch (choise) {
                    case "AD":
                        attend();
                    break;
                    case "AN":
                        assign();
                    break;
                    default:
                        
                    break;
                }
            }
            semireset();
        }
        reset();

        //medium severity tickets
        for (int i = 0; i < count; i++) {
            ticketNumber=i+1;
            fetch(i+1);
            if (Objects.equals("Medium", severity)&&Objects.equals("Assigned[all]",status)) {
                view();
                System.out.println("[AD] Attend     [AN] Assign     [ANYKEY with ENTER] Skip");
                Misc.prompt(" [ Medium Severity ] ");
                choise=Misc.input.nextLine().toUpperCase().intern();
                switch (choise) {
                    case "AD":
                        attend();
                    break;
                    case "AN":
                        assign();
                    break;
                    default:
                        
                    break;
                }
            }
            semireset();
        }
        reset();
        
        //low severity tickets
        for (int i = 0; i < count; i++) {
            ticketNumber=i+1;
            fetch(i+1);
            if (Objects.equals("Low", severity)&&Objects.equals("Assigned[all]",status)) {
                view();
                System.out.println("[AD] Attend     [AN] Assign     [ANYKEY with ENTER] Skip");
                Misc.prompt(" [ Low Severity ] ");
                choise=Misc.input.nextLine().toUpperCase().intern();
                switch (choise) {
                    case "AD":
                        attend();
                    break;
                    case "AN":
                        assign();
                    break;
                    default:
                        
                    break;
                }
            }
            semireset();
        }
        reset();

    }

    //attend to tickets by searching for tickets
    public void searchAttend() {

        //variable
        String tNum=null;
        String choise=null;

        //objects
        File tFile = new File("Tickets/"+tNum+".txt");

        //check the availability if the ticket
        while (tFile.exists()==false) {
            System.out.print("Ticket Number: ");
            tNum=Misc.input.nextLine().toUpperCase();
            tFile = new File("Tickets/"+tNum+".txt");
            if (tFile.exists()==false) {
                System.out.println("'"+tNum+"' - this ticket doesn't exists");
            } else {
                ticketNumber=Integer.parseInt(tNum.substring(3));
            }
        }

        //menu
        while (choise!="EX") {
            //rendering the ticket
            fetch(ticketNumber);
            view();
            if (Objects.equals(status, "Deleted")) {
                reset();
                choise="EX";
                System.out.println("This ticket has been deleted!!");
                Misc.sleep(2);
            } else {
                System.out.println("[AD] Attend     [AN] Assign     [EX] Exit");
                System.out.println();
                Misc.prompt(" ( T000"+ticketNumber+" ) ");
                choise=Misc.input.nextLine().toUpperCase().intern();
                switch (choise) {
                    case "AD":
                        attend();
                    break;
                    case "AN":
                        assign();
                    break;
                    case "EX":
                        reset();
                    break;
                    default:
                        System.out.println("'"+choise+"' is not a choise from the menu... please choose a choise from the menu!!");
                        Misc.sleep(1);
                    break;
                }
                semireset();
            }
        }

    }

    //statistics of the ticket
    public void statistics() {

        //variables
        int count=0;
        int assigned=0;
        int ongoing=0;
        int completed=0;
        int deleted=0;

        //objects
        File tFile = new File("Tickets/T0001.txt");

        //count ticket files
        while (tFile.exists()) {
            count++;
            tFile = new File("Tickets/T000"+count+".txt");
        }
        count--;

        //caculations
        for (int i = 0; i < count; i++) {
            fetch(i+1);
            if (Objects.equals("Assigne", status.substring(0,7))) {
                assigned++;
            }
            if (Objects.equals("Ongoing", status)) {
                ongoing++;
            }
            if (Objects.equals("Completed", status)) {
                completed++;
            }
            if (Objects.equals("Deleted", status)) {
                deleted++;
            }
        }

        //output
        System.out.println("Statistics");
        System.out.println("----------");
        System.out.println("Assigned: "+assigned);
        System.out.println("Ongoing: "+ongoing);
        System.out.println("Completed: "+completed);
        System.out.println("Deleted: "+deleted);
        System.out.println();
        System.out.print("Press ENTER to Continue... ");
        Misc.input.nextLine();

    }

    //attend
    public void attend() {

        //variables
        String attendMsg=null;

        //take input for message
        System.out.print("Message: ");
        attendMsg=Misc.input.nextLine();
        msg.add(Accounts.userName+"(Technician): "+attendMsg);
        status="Ongoing";

        //write to the file
        write();

    }

    //assigned
    public void assign() {

        //variables
        String assignUser=null;
        String accType=null;
        boolean colify=false;

        //objects
        File userFile = new File ("Users/");

        //take assigned user name
        while (assignUser==null||colify==false) {
            System.out.print("User Name: ");
            assignUser=Misc.input.nextLine();
            userFile = new File ("Users/"+assignUser+".txt");
            try {
                Scanner fReader = new Scanner(userFile);
                for (int i = 0; i < 6; i++) {
                    fReader.nextLine();
                }
                accType=fReader.nextLine().substring(14);
                fReader.close();
            } catch (Exception e) {
                
            }
            if (userFile.exists()&&Objects.equals(accType, "Technician")) {
                colify=true;
            } else {
                System.out.println("This Tech user Doesn't exists");
                Misc.sleep(1);
            }
        }
        status="Assigned["+assignUser+"]";

        //write to the file
        write();

    }


}
