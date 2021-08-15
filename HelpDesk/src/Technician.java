public class Technician {

    private String skillAndExp;


    public void techDesk() {

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
            System.out.println("(1) Attend recent tickets");
            System.out.println("(2) Search & Attend ticket");
            System.out.println("(3) Statistics");
            System.out.println("(4) Delete the account");
            System.out.println("(5) Log out");
            Misc.prompt("/");
            choice = Misc.input.nextInt();
            Misc.input.nextLine();
            switch (choice) {
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
                    finished = true;
                    break;
                case 5:
                    acc.logout();
                    finished = true;
                    break;
                default:
                    System.out.println("'" + choice + "' is not a choice from the main menu... please choose a choice from the menu!!");
                    Misc.sleep(1);
                    break;
            }
        }

    }

    //sets the skill and experience of the technician
    public void setSkillAndExp() {
        System.out.print("Skill and experience: ");
        skillAndExp = Misc.input.nextLine();
    }

    //gets the skill and experience of the technician
    public String getSkillAndExp () {
        return skillAndExp;
    }

}
