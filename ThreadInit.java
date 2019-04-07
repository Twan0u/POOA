public class ThreadInit extends Thread{

  public static void main(String[] args) {
    ThreadInit me = new ThreadInit();
    me.start();
  }

  public void run(){
      try{
        System.out.println("Lancement de l'initialisation de l'application");
        Thread.sleep(4000);
        // replacer par un chargement des données depuis un fichier de config
        // Ajouter une vérification de la connexion avec la base de donnée
        System.out.println("initialisation de l'application terminée");
      }
      catch(Exception e){
        System.out.println("erreur dans L'initialisation de l'application");
      }
  }

}
