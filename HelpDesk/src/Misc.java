import java.util.Scanner;

public class Misc {
    
    //clear screen
    static void clearScreen() {
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }

    //global scanner
    static Scanner input = new Scanner(System.in);

    //sleep
    static void sleep(int s) {
        try{
            Thread.sleep(s*1000);
        }
        catch(InterruptedException ex){
            Thread.currentThread().interrupt();
        }
    }

    //prompt
    static void prompt (String directory) {
        System.out.print(Accounts.userName+"@helpDesk~"+directory+": ");
    }
    

}
