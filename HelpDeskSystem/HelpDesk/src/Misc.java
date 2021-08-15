
public class Misc extends Utility {
    
    //clear screen
    static void clearScreen() {
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }



    //sleep
    static void sleep(int s) {
        try{
            Thread.sleep(s*1000);
        }
        catch(InterruptedException ex){
            Thread.currentThread().interrupt();
        }
    }
    
}
