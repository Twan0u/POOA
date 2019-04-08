/*
Programme de Gestion d'une brasserie
écrit par Antoine Lambert et Nathan Surquin
dans le cadre du cours de Programation avancée Orientée Objets
*/

public class BusinessUnit{
  /*Variables d'instances*/
  private static int counterid; // A VERIFIER
  private int idBusinessUnit;
  private Client client;
  private Locality locality;// Tableau des différentes localisations de l'entreprise
  private String streetName;
  private String streetNumber;

/*Methode constructeur pour les objets Client*/
  public BusinessUnit(int id, Client client, Locality locality, String streetName, String streetNumber){
    setIdBusinessUnit(id);
    setClient(client);
    setLocality(locality);
    setStreetName(streetName);
    setStreetNumber(streetNumber);
  }

  /* GETTEURS */
  public int getIdBusinessUnit(){
    return this.idBusinessUnit;
  }
  public Client getClient(){
    return this.client;
  }
  public Locality getLocality(){
    return this.locality;
  }
  public String getStreetName(){
    return this.streetName;
  }
  public String getStreetNumber(){
    return this.streetNumber;
  }

  /* SETTEURS */
  private void setIdBusinessUnit(int id){
    //VERIFIER
    this.idBusinessUnit = id;
  }
  private void setClient(Client client){
    //VERIFIER
    this.client = client;
  }
  private void setLocality(Locality locality){
    //VERIFIER
    this.locality = locality;
  }
  private void setStreetName(String streetName){
    //VERIFIER
    this.streetName = streetName;
  }
  private void setStreetNumber(String streetNumber){
    //VERIFIER
    this.streetNumber = streetNumber;
  }


  /* Utils */
  public String toString(){
    return getIdBusinessUnit() + " - " + client.toString() + " - " + locality.toString() + " - " + getStreetName() + " - " + getStreetNumber();
  }
}
