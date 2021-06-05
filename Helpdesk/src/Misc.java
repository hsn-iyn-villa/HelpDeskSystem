public class Misc {
    static void sleep(int ms) {
        try {
            Thread.sleep(ms*1000);
        } catch (Exception e) {
            
        }
    }
    static void clear() {
        System.out.print("\033[H\033[2J");  
        System.out.flush();
    }
}
