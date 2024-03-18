package lt.viko.eif.dgenzuras.libraryapp;

import lt.viko.eif.dgenzuras.libraryapp.database.DBloader;
import lt.viko.eif.dgenzuras.libraryapp.menu.Menu;

/**
 * Main class starts the system and starts the DBloader and Menu classes.
 *
 * @author dainius.genzuras@stud.viko.lt
 * @since 1.0
 * @see DBloader
 * @see Menu
 */

public class Main {
    public static void main(String[] args){
        new DBloader();
        Menu.showMenu();
    }
}