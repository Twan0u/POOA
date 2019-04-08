/*
Programme de Gestion d'une brasserie
écrit par Antoine Lambert et Nathan Surquin
dans le cadre du cours de Programation avancée Orientée Objets
*/

import javax.swing.*;
import java.io.*;

public class BrassiGestion {

    public static void main(String[] args) {
      new Interface("1","2"); // Gui

      //initialisation();//Lance un thread qui initialise l'application
      //System.exit(0);
      //JOptionPane.showMessageDialog (null, "Message","INFO", JOptionPane.PLAIN_MESSAGE);
    }

    /*public static void initialisation(){
      ThreadInit threadInit = new ThreadInit();
      threadInit.start();
      try{
        threadInit.join();
      }
      catch(InterruptedException err){
        System.out.println(err);
      }
    }*/

}
