/*
Programme de Gestion d'une brasserie
écrit par Antoine Lambert et Nathan Surquin
dans le cadre du cours de Programation avancée Orientée Objets
*/
import composants.*;
import composants.exceptions.*;

import javax.swing.*;
import java.io.*;


// ajouter une discount par order line

public class BrassiGestion {

    private static Client [] clients;
    private static int NUM_CLIENTS = 5; // trouver une solution

    public static void main(String[] args) {

      loadData();
      new Interface(); // Gui

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
        OrderLine orderline1 = new OrderLine(bucheBlonde,order1,10);
        OrderLine orderline2 = new OrderLine(trappe,order2,12);
        OrderLine orderline3 = new OrderLine(beer1,order1,3);
        OrderLine orderline4 = new OrderLine(beer2,order1,12);
        OrderLine orderline5 = new OrderLine(beer3,order1,45);
        OrderLine orderline6 = new OrderLine(beer4,order1,42);
        OrderLine orderline7 = new OrderLine(beer5,order1,700);
        System.out.println(order1.toString());
        System.out.println(order2.toString());
        new Interface("Titre","bouton",bob.getBusiness(),bob.getBusinessCount());
      }
      catch(BeerException beerException){
        JOptionPane.showMessageDialog (null, beerException.getMessage(),"ERREUR", JOptionPane.ERROR_MESSAGE);
      }
      catch(OrderException OrderException){
        JOptionPane.showMessageDialog (null, OrderException.getMessage(),"ERREUR", JOptionPane.ERROR_MESSAGE);
      }
      catch(BusinessUnitException e){
        JOptionPane.showMessageDialog (null, e.getMessage(),"ERREUR", JOptionPane.ERROR_MESSAGE);
      }

      catch(LocalityException localityException){
        JOptionPane.showMessageDialog (null, localityException.getMessage(),"ERREUR", JOptionPane.ERROR_MESSAGE);
      }*/



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

      String [] out = new String[NUM_CLIENTS];
      for(int i = 0; i<NUM_CLIENTS; i++){
        out[i] = clients[i].getName() + "-" + clients[i].getNumber();
      }
      return out;
    }

    public static void loadData(){
      clients = new Client[NUM_CLIENTS];
      try{

            // LOAD ALL CLIENTS

            clients[0] = new Client (1, "Antoine", "0032498194975", 80, "TVA_062232095251");
            clients[1] = new Client (2, "Benjamin", "0032498191234", 50, "TVA_062232095223");
            clients[2] = new Client (3, "Corentin", "0032498195678", 10, "TVA_062232095654");
            clients[3] = new Client (4, "Emilie", "0032498198910", 25, "TVA_0622320956789");
            clients[4] = new Client (5, "BOB", "0032498191112", 66, "TVA_062232095765");
            System.out.println("ok");
            //Load The Localities

            Locality local = new Locality (1, "Localité du petit bois", "7911");
            Locality local2 = new Locality (2, "Localité du saucisson", "7900");

            //Load the Businessunits

            BusinessUnit bubu = new BusinessUnit (1, clients[0], local, "Rue de la faim", "12");
            BusinessUnit blyat = new BusinessUnit (2, clients[0], local2, "Rue des potiers", "3");

      }catch(Exception e){
        JOptionPane.showMessageDialog (null, e.getMessage(),"ERREUR", JOptionPane.ERROR_MESSAGE);
      }
    }

    public static String[] getBusinessOfClient(int num){
      BusinessUnit [] tab = clients[num].getBusiness();

      String [] out = new String[2]; // pas d'autres idées pour le moment

      for(int i = 0; i<2; i++){
        out[i] = tab[i].getStreetName();
      }
      return out;
    }

    //System.exit(0);
    //JOptionPane.showMessageDialog (null, "Message","INFO", JOptionPane.PLAIN_MESSAGE);

}
