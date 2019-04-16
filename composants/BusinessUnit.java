package composants;
/*
Programme de Gestion d'une brasserie
écrit par Antoine Lambert et Nathan Surquin
dans le cadre du cours de Programation avancée Orientée Objets
*/

import composants.exceptions.BusinessUnitException;

//verification des ids et comment en attribuer une ?
// verification que le numéro d'identification est plausible

public class BusinessUnit{

  private static int counterid; // A VERIFIER
  private static int MAX_LONG_STREET = 5;
  private static int MAX_STREETNAME = 40;

  /*Variables d'instances*/
  private int idBusinessUnit;
  private Client client;
  private Locality locality;// Tableau des différentes localisations de l'entreprise
  private String streetName;
  private String streetNumber;

/*Methode constructeur pour les objets Client*/
  public BusinessUnit(int id, Client client, Locality locality, String streetName, String streetNumber)throws BusinessUnitException{
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
  private void setClient(Client client)throws BusinessUnitException{
    if(client == null){
      throw new BusinessUnitException("Le Client est invalide");
    }
    this.client = client;
    client.addBusinessUnit(this);
  }
  private void setLocality(Locality locality)throws BusinessUnitException{
    if(locality == null){
      throw new BusinessUnitException("La Localité est invalide");
    }
    this.locality = locality;
  }
  private void setStreetName(String streetName)throws BusinessUnitException{
    if(streetName == null){
      throw new BusinessUnitException("Le nom de la rue est invalide");
    }else if(streetName.length() > MAX_STREETNAME){
      throw new BusinessUnitException("Le nom de la rue est trop long");
    }
    this.streetName = streetName;
  }
  private void setStreetNumber(String streetNumber)throws BusinessUnitException{
    if(locality == null){
      throw new BusinessUnitException("Le numero est invalide");
    }else if(streetNumber.length() > MAX_LONG_STREET){
      throw new BusinessUnitException("Le numero est trop long");
    }
    this.streetNumber = streetNumber;
  }


  /* Utils */
  public String toString(){
    return "Client:\t\t " + client.getName() + " - " + client.getPhoneNumber() + "\nSitué à:\t" + locality.getName() + " " + locality.getPostCode()  + " \n\t\t" + this.getStreetNumber() + " " + this.getStreetName() ;
  }
}
