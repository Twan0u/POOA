package dataccess;

import composants.*;
import exceptions.*;

import java.io.*;
import java.util.*;

import java.sql.*;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class Data {
  private static ArrayList<Client> clients = new ArrayList<>();
  static Client[] getAllClients()throws ClientException{
      /* Chargement du driver JDBC pour MySQL */
      //jdbc:mysql://nomhote:port/nombdd
    try {
      Class.forName( "com.mysql.jdbc.Driver" );
    } catch ( ClassNotFoundException e ) {
      /* Gérer les éventuelles erreurs ici. */
    }

    /* Connexion à la base de données */
    String url = "jdbc:mysql//192.168.1.22:3306/bdd_pooa";
    String utilisateur = "POOA";
    String motDePasse = "POOA_e";
        Connection con = null;
        Statement statement = null;

        try {
            con = DriverManager.getConnection(url, utilisateur, motDePasse);
            statement = con.createStatement();

            /* Exécution d'une requête de lecture */
ResultSet resultat = statement.executeQuery( "SELECT id, email, mot_de_passe, nom  FROM Utilisateur;" );

/* Récupération des données du résultat de la requête de lecture */
while ( resultat.next() ) {
    int idUtilisateur = resultat.getInt( "id" );
    String emailUtilisateur = resultat.getString( "email" );
    String motDePasseUtilisateur = resultat.getString( "mot_de_passe" );
    String nomUtilisateur = resultat.getString( "nom" );

    System.out.println(emailUtilisateur);
        System.out.println(nomUtilisateur);
}

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            if (statement != null) {
                try {
                    // Le stmt.close ferme automatiquement le rset.
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
}
    return null;
  }
  static Client getClient(int index){
    return null;
  }
  static Beer[] getAllBeers()throws BeerException{return null;}

  static BusinessUnit[] getBusinessOf(int index)throws BusinessUnitException,LocalityException{
    return clients.get(index).getBusiness();
  }
  public static void main(String[] args){
    System.out.println("Hello");
    try{
    Client [] test = getAllClients();
  }catch(Exception e){}
  }
}
