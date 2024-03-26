package lt.viko.eif.dgenzuras.libraryapp;

import lt.viko.eif.dgenzuras.libraryapp.database.DBloader;
import java.util.Scanner;

/**
 * Main class starts the system and starts the DBloader class.
 *
 * @author dainius.genzuras@stud.viko.lt
 * @since 1.0
 * @see DBloader
 */

public class Main {

    public static void main(String[] args){
        System.out.println();
        System.out.println("Select operation:\n[0] Send packet\n[1] Quit");
        System.out.print("Your selection: ");

        Scanner in = new Scanner(System.in);
        int operation = in.nextInt();

        switch(operation) {
            default:
                return;

            case 0: // send packet
                new DBloader().SendPacket();
                break;
        }

        main(args);
    }
}