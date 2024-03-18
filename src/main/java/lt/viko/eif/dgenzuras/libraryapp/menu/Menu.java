package lt.viko.eif.dgenzuras.libraryapp.menu;

import lt.viko.eif.dgenzuras.libraryapp.database.DBloader;

import java.util.Scanner;

/**
 * Menu class lets the user choose system's operations.
 *
 * @author dainius.genzuras@stud.viko.lt
 * @since 1.0
 * @see lt.viko.eif.dgenzuras.libraryapp.Main
 */

public class Menu {
    public static int displayMenu(Scanner input) {
        System.out.println(" Type in your selected operation number ");
        System.out.println(" ====================================== ");
        System.out.printf("|1) %20s \n", "Load readers");
        System.out.printf("|6) %20s \n", "Quit");
        System.out.println("Your selection:");
        return input.nextInt();
    }

    public static void showMenu() {
        Scanner input = new Scanner(System.in);
        int userChoice = displayMenu(input);
        do {
            userChoice = displayMenu(input);
            switch (userChoice) {
                case 1:
                    DBloader.loadReaders();
                    break;

                case 6:
                    System.out.println("The program has ended successfully.");
                    System.exit(0);
                    break;

                default:
                    System.out.println("There is no such operation.");
                    break;
            }
        } while (userChoice != 6);
    }
}
