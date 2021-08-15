
public class Desk {


    //startup desk with a guest user
    public void startup () {

        //variables
        int choice=0;

        //objects
        Accounts acc = new Accounts();

        while (choice!=3) {
            //clear the console
            Misc.clearScreen();

            //main menu
            System.out.println("(1) Login");
            System.out.println("(2) Create a Account");
            System.out.println("(3) Shutdown");

            //take input
            Misc.prompt("/");
            choice=Misc.input.nextInt(); Misc.input.nextLine();

            //take decision according to the input
            switch (choice) {
                case 1:
                    acc.logIn();
                    break;
                case 2:
                    acc.createAccount();
                    break;
                case 3:
                    Misc.clearScreen();
                    break;
                default:
                    System.out.println("'"+choice+"' is not a choice from the main menu... please choose a choice from the menu!!");
                    Misc.sleep(1);
                    break;
            }
        }

    }
}
