import javax.swing.*;
import java.io.*;

public class BrassiGestion {

    public static void main(String[] args) {
      //Lance un thread pour l'initialisation de l'application
        ThreadInit threadInit = new ThreadInit();
        threadInit.start();
      //Affiche un splashscreen
        JOptionPane.showMessageDialog (null, "Lancement de L'application BrassiGestion en cours",
        "INFO", JOptionPane.PLAIN_MESSAGE);
        try{
          threadInit.join();
        }catch(InterruptedException err){
          System.out.println(err);
        }
      System.exit(0);
    }

}
