/*
Programme de Gestion d'une brasserie
écrit par Antoine Lambert et Nathan Surquin
dans le cadre du cours de Programation avancée Orientée Objets
*/

public class ThreadInit extends Thread{

  public static void main(String[] args) {
    ThreadInit me = new ThreadInit();
    me.start();
  }

  public void run(){
      try{
        System.out.println("Initialisation de l'application");
        // Chargement des données depuis un fichier de config
        // Lance l'interface graphique
        new Interface("Bouton");
        // Ajouter une vérification de la connexion avec la base de donnée
        System.out.println("Fin initialisation de l'applicaiton");
      }
      catch(Exception e){
        System.out.println("erreur dans L'initialisation de l'application");
      }
  }

}
