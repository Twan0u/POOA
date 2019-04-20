import composants.*;
import composants.exceptions.*;

import javax.swing.*;
import java.io.*;

public class BrassiGestion {

    private static Client [] clients;
    private static int MAX_Clients = 5;

    /*  try{
        Order order1 = new Order(1, bubu, bob, false, "10-12-19", "Paid", 365);
        Order order2 = new Order(2, bubu, bob, true, "12-04-19", "Paid", 365);
        Beer trappe = new Beer("Trappe triple",2.80,100);
        Beer bucheBlonde = new Beer("Bûche Blonde",2.10,10);
        Beer beer1 = new Beer("Bûche Blonde",2.20,10);
        Beer beer2 = new Beer("Blonde ambrée",1.50,2);
        Beer beer3 = new Beer("Bûche double",2.50,16);
        Beer beer4 = new Beer("Bûche triple",3.10,11);
        Beer beer5 = new Beer("Triple Karmeliet",1.00,10);
        OrderLine orderline3 = new OrderLine(beer1,order1,3);
    }

  /*  public static void initialisation(){
      ThreadInit threadInit = new ThreadInit();
      threadInit.start();
      try{
        threadInit.join();
      }
      catch(InterruptedException err){
        System.out.println(err);
      }
    }*/


    public static String[] getClients(){
      String [] out = new String[clients.length];
      for(int i = 0; i<out.length; i++){
        out[i] = clients[i].getName() + "-" + clients[i].getId();
      }
      return out;
    }

    public static String getInfoClient(int index){
      return clients[index].toString();
    }

    public static String[] getBusinessOfClient(int num){
      BusinessUnit [] tab = clients[num].getBusiness();
      if (tab == null){return null;}
      String [] out = new String[2]; // pas d'autres  idées pour le moment
      for(int i = 0; i<2; i++){
        out[i] = tab[i].getStreetName();
      }
      return out;
    }

    public static void loadData(){
      clients = new Client[MAX_Clients];
      try{

            // LOAD ALL CLIENTS

            clients[0] = new Client (1, "Antoine", "0032498194975", 80, "TVA_062232095251");
            clients[1] = new Client (2, "Benjamin", "0032498191234", 50, "TVA_062232095223");
            clients[2] = new Client (3, "Corentin", "0032498195678", 10, "TVA_062232095654");
            clients[3] = new Client (4, "Emilie", "0032498198910", 25, "TVA_0622320956789");
            clients[4] = new Client (5, "BOB", "0032498191112", 66, "TVA_062232095765");
            //Load The Localities

            Locality local = new Locality (1, "Localité du petit bois", "7911");
            Locality local2 = new Locality (2, "Localité du saucisson", "7900");

            //Load the Businessunits

            BusinessUnit bubu = new BusinessUnit (1, clients[0], local, "Rue de la faim", "12");
            BusinessUnit blyat = new BusinessUnit (2, clients[0], local2, "Rue des potiers", "3");
            BusinessUnit bubui = new BusinessUnit (1, clients[1], local, "Rue des marchands", "22");
            BusinessUnit blyati = new BusinessUnit (2, clients[1], local2, "Rue Raymond le gros", "16");

      }catch(Exception e){
        JOptionPane.showMessageDialog (null, e.getMessage(),"ERREUR", JOptionPane.ERROR_MESSAGE);
      }
    }


    //System.exit(0);
    //JOptionPane.showMessageDialog (null, "Message","INFO", JOptionPane.PLAIN_MESSAGE);

}
