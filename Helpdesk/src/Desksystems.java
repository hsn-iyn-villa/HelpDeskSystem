public class Desksystems {
    static void client() {
        String choise = "1";
        while (choise != "4" && choise != "3") {
            Misc.clear();
            Header.helpdesklogo();
            System.out.println("Hello "+Main.user+",");
            System.out.println(" ");
            System.out.println("Choose an option from the menu below: ");
            System.out.println(" ");
            System.out.println(" 1- Report a problem ");
            System.out.println(" 2- Check for response ");
            System.out.println(" 3- Delete the Account ");
            System.out.println(" 4- Log Out ");
            System.out.println(" ");
            System.out.print(Main.user+"@helpdesk:~# ");
            choise = Main.scan.nextLine().intern();
            if (choise=="1") {
                Server.postproblem();                
            } else if (choise=="2") {
                Server.responsecheck();
            } else if (choise=="3") {
                Aunthentication.deleteacc();
            } else if (choise=="4") {
                Aunthentication.logoutacc();
            } else {
                System.out.println("wrong option!! choose from 1, 2, 3, 4 ");
                System.out.println(" ");
                Misc.sleep(1);
            }
        }
    }
    static void technician() {
        String choise = "1";
        while (choise != "3" && choise != "2") {
            Misc.clear();
            Header.helpdesklogo();
            System.out.println("Hello "+Main.user+",");
            System.out.println(" ");
            System.out.println("Choose an option from the menu below: ");
            System.out.println(" ");
            System.out.println(" 1- Attend to a case ");
            System.out.println(" 2- Delete the Account ");
            System.out.println(" 3- Log Out ");
            System.out.println(" ");
            System.out.print(Main.user+"@helpdesk:~# ");
            choise = Main.scan.nextLine().intern();
            if (choise=="1") {
                Server.attendcase();
            } else if (choise=="2") {
                Aunthentication.deleteacc();
            } else if (choise=="3") {
                Aunthentication.logoutacc();
            } else {
                System.out.println("wrong option!! choose from 1, 2, 3 ");
                System.out.println(" ");
                Misc.sleep(1);
            }
        }
    }
}