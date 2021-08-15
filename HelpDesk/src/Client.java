public class Client {

    private String designation = null;
    private String department = null;
    private String email = null;

    public void clientDesk() {

        //variables
        int choice;

        //objects
        Accounts acc = new Accounts();
        Tickets ticket = new Tickets();

        //menu
        boolean finished = false;
        while (!finished) {
            Misc.clearScreen();
            System.out.println("Choose a option from below: ");
            System.out.println("(1) Create a ticket");
            System.out.println("(2) recent tickets");
            System.out.println("(3) search tickets");
            System.out.println("(4) delete the account");
            System.out.println("(5) Log out");
            Misc.prompt("/");
            choice = Misc.input.nextInt();Misc.input.nextLine();
            switch (choice) {
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
                    finished = true;
                    break;
                case 5:
                    acc.logout();
                    finished = true;
                    break;
                default:
                    System.out.println("'"+choice+"' is not a choice from the main menu... please choose a choice from the menu!!");
                    Misc.sleep(1);
                    break;
            }
        }

    }

    //stores attributes thats only for the client
    public void createClient() {
        email = Misc.input.nextLine();
        System.out.print("Designation: ");
        designation = Misc.input.nextLine();
        System.out.print("Department: ");
        department = Misc.input.nextLine();
    }

    //get email of the client
    public String getEmail() {

        return email;
    }

    //get designation of the client
    public String getDesignation() {

        return designation;
    }

    //get department of the client
    public String getDepartment() {

        return department;
    }



}
