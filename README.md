# Projet_info
package Main;

import Controller.Keyboard;
import Model.Game;
import View.Window;
import java.lang.RuntimeException;
 
import java.util.Scanner;
 
public class Main {
    public static void main(String[] args) {
         
        Scanner scanner = new Scanner( System.in );
        System.out.println("Entrez la taille de la map : ");
        int size = scanner.nextInt();
        if(size<10){
            throw new RuntimeException("la map doit être au moins une 10x10"); }
         
        Window window = new Window();
         
        Game game = new Game(window,size);
        Keyboard keyboard = new Keyboard(game);
        window.setKeyListener(keyboard);
    }
}

