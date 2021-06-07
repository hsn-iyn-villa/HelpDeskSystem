import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * server
 */
public class Server {
    static void postproblem () {
        String issue = null;
        int ticketnum = 1;
        File ticketinfo = new File("tickets/T0000"+String.valueOf(ticketnum)+".txt");
        while (ticketinfo.exists()) {
            ticketnum++;
            ticketinfo = new File("tickets/T0000"+String.valueOf(ticketnum)+".txt");
        }
        try {
            System.out.println("What is the problem you are facing?? ");
            issue = Main.scan.nextLine();
            FileWriter writer = new FileWriter("tickets/T0000"+String.valueOf(ticketnum)+".txt");
            BufferedWriter ticket = new BufferedWriter(writer);
            ticket.write("Issue: "+issue);
            ticket.close();
            System.out.println(" ");
            System.out.println("Your question has been successfully posted... Our technicians will be right with you!! ");
            System.out.println("your Ticket no is T0000"+ticketnum);
            System.out.println(" ");
            Misc.sleep(5);
        } catch (Exception e) {
            
        }
    }
    static void responsecheck () {
        String ticketnum = null;
        String issue = null;
        String response = "Response: We will give you a response as soon as possible!! ";
        System.out.print("Ticket Number: ");
        ticketnum = Main.scan.nextLine();
        File issuesfile = new File("tickets/"+ticketnum+".txt");
        try {
            Scanner scanfile = new Scanner(issuesfile);
            issue = scanfile.nextLine();
            System.out.println(issue);
            if (scanfile.hasNextLine()) {
                response = scanfile.nextLine();
            }
            System.out.println(response);
            scanfile.close();
            System.out.println(" ");
            System.out.print("Press ENTER to continue!! ");
            Main.scan.nextLine();
        } catch (Exception e) {
            System.out.println("There is no ticket under this ticket number... check again!!");
            System.out.println(" ");
            Misc.sleep(3);
        }
    } 
    static void attendcase () {
        int amnt = new File("tickets/").list().length;
        long count = 0;
        String ticketnum = null;
        for (int i = 0; i < amnt; i++) {
            ticketnum = "T0000"+(i+1);

            try {
                Path file = Paths.get("tickets/"+ticketnum+".txt");
                count = Files.lines(file).count();

            } catch (Exception e) {
                e.getStackTrace();
            }
            if (count==1) {
                System.out.println(ticketnum);
                try {
                    File issuesfile = new File("tickets/"+String.valueOf(ticketnum)+".txt");
                    Scanner scanfile = new Scanner(issuesfile);
                    String issue = scanfile.nextLine();
                    System.out.println(issue);
                    scanfile.close();
                    System.out.println("What is the responsve for the above concern?? ");
                    String response = Main.scan.nextLine();
                    FileWriter writer = new FileWriter("tickets/"+String.valueOf(ticketnum)+".txt");
                    BufferedWriter ticket = new BufferedWriter(writer);
                    ticket.write("issue: "+issue);
                    ticket.newLine();
                    ticket.write("Response: "+response);
                    ticket.close();
                    System.out.println(" ");
                    System.out.println("Your response has been successfully posted... ");
                    Misc.sleep(1);
                } catch (Exception e) {
                    
                }

            }
        }
        System.out.println("There are no unattended tickets any more... check again in a while!! ");
        Misc.sleep(2);
    }
}