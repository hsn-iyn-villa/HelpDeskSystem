public class Desk {
    

    //startup desk with a guest user
    public void startup () {

        //variables
        int choise=0;

        //objects
        Accounts acc = new Accounts();

        while (choise!=3) {
            //clear the console
            Misc.clearScreen();

            //main menu
            System.out.println("(1) Login");
            System.out.println("(2) Create a Account");
            System.out.println("(3) Shutdown");

            //take input
            Misc.prompt("/");
            choise=Misc.input.nextInt(); Misc.input.nextLine();

            //take decision according to the input
            switch (choise) {
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
                    System.out.println("'"+choise+"' is not a choise from the main menu... please choose a choise from the menu!!");
                    Misc.sleep(1);
                break;
            }
        }

    }

    //client desk
    public void clientDesk () {

        //variables
        int choise=0;

        //objects
        Accounts acc = new Accounts();
        Tickets ticket = new Tickets();

        //menu
        while (choise!=5 && choise!=4) {
            Misc.clearScreen();
            System.out.println("Choose a option from below: ");
            System.out.println("(1) Create a ticket");
            System.out.println("(2) recent tickets");
            System.out.println("(3) search tickets");
            System.out.println("(4) delete the account");
            System.out.println("(5) Log out");
            Misc.prompt("/");
            choise=Misc.input.nextInt();Misc.input.nextLine();
            switch (choise) {
                case 1:
                    ticket.createTicket();
                break;
                case 2:
                    ticket.recent();
                break;
                case 3:
                    ticket.search();
                break;
                case 4:
                    acc.deleteAccount();
                break;
                case 5:
                    acc.logout();
                break;
                default:
                    System.out.println("'"+choise+"' is not a choise from the main menu... please choose a choise from the menu!!");
                    Misc.sleep(1);
                break;
            }
        }

    }

    //technician desk
    public void techDesk () {

        //variables
        int choise=0;

        //objects
        Accounts acc = new Accounts();
        Tickets ticket = new Tickets();

        //menu
        while (choise!=5 && choise!=4) {
            Misc.clearScreen();
            System.out.println("Choose a option from below: ");
            System.out.println("(1) Attend recent tickets");
            System.out.println("(2) search & Attend ticket");
            System.out.println("(3) Statistics");
            System.out.println("(4) delete the account");
            System.out.println("(5) Log out");
            Misc.prompt("/");
            choise=Misc.input.nextInt();Misc.input.nextLine();
            switch (choise) {
                case 1:
                    ticket.attendRecent();
                break;
                case 2:
                    ticket.searchAttend();
                break;
                case 3:
                    ticket.statistics();
                break;
                case 4:
                    acc.deleteAccount();
                break;
                case 5:
                    acc.logout();
                break;
                default:
                    System.out.println("'"+choise+"' is not a choise from the main menu... please choose a choise from the menu!!");
                    Misc.sleep(1);
                break;
            }
        }

    }


}
