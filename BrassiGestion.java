import javax.swing.*;
import java.io.*;

/*
Programme de Gestion d'une brasserie
écrit par Antoine Lambert et Nathan Surquin
dans le cadre du cours de Programation avancée Orientée Objets
*/

public class BrassiGestion {

    public static void main(String[] args) {
      //Lance un thread qui initialise l'application
      
      //Affiche un splashscreen
        JOptionPane.showMessageDialog (null, "Lancement de L'application BrassiGestion en cours",
        "INFO", JOptionPane.PLAIN_MESSAGE);
        try{
          threadInit.join();
        }catch(InterruptedException err){
          System.out.println(err);
        }
      //System.exit(0);
    }

    public static void initialisation(){
      ThreadInit threadInit = new ThreadInit();
      threadInit.start();
    }

}
