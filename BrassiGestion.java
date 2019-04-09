/*
Programme de Gestion d'une brasserie
écrit par Antoine Lambert et Nathan Surquin
dans le cadre du cours de Programation avancée Orientée Objets
*/
import composants.*;
import composants.exceptions.*;

import javax.swing.*;
import java.io.*;

public class BrassiGestion {

    public static void main(String[] args) {

      // test de création d'interface
      //new Interface("Titre","Boutton"); // Gui

      //test de création de client


      try{
        Client bob = new Client (12, "Bob", "0032498194975", 30, "Suceuse de TVA");
        Locality local = new Locality (1, "Localité de Mes Fesses", "7911");
        BusinessUnit bubu = new BusinessUnit (3, bob, local, "Rue de ta mère", "12");
        System.out.println(bubu.toString());
      }
      catch(LocalityException localityexception){
        JOptionPane.showMessageDialog (null, localityexception.getMessage(),"ERREUR", JOptionPane.ERROR_MESSAGE);
      }







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
