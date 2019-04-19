package composants;
/*
Programme de Gestion d'une brasserie
écrit par Antoine Lambert et Nathan Surquin
dans le cadre du cours de Programation avancée Orientée Objets
*/
import java.util.*;
import composants.exceptions.ClientException;

// identification id client, a attribuer
// identification client est à vérifier

public class Client {

  private static int MAX_LONG_NOM = 64;
  private static int MAX_LONG_NUM_TEL = 18;
  private static int MAX_LONG_VAT = 32;

  /* Variables d'instances */

  private int number;
  private String name;
  private String phoneNumber;
  private double discount;
  private String VATNumber; // Facultatif

  private ArrayList<BusinessUnit> businessUnits = new ArrayList<>();

/*Methode constructeur pour les objets Client*/
  public Client(int number, String name, String phoneNumber, double discount, String VATNumber)throws ClientException{
    setNumber(number);
    setName(name);
    setPhoneNumber(phoneNumber);
    setDiscount(discount);
    setVATNumber(VATNumber);
  }
  public Client(int number, String name, String phoneNumber, double discount)throws ClientException{
    this(number, name, phoneNumber, discount, null);
  }
  public Client(int number, String name, String phoneNumber)throws ClientException{
    this(number, name, phoneNumber, 0, null);
  }


  void addBusinessUnit(BusinessUnit business){
    this.businessUnits.add(business);
  }


  /* GETTEURS */
  public int getNumber(){
    return this.number;
  }
  public String getName(){
    return this.name;
  }
  public String getPhoneNumber(){
    return this.phoneNumber;
  }
  public double getDiscount(){
    return this.discount;
  }
  public String getVATNumber(){
    return this.VATNumber;
  }
  public BusinessUnit[] getBusiness(){
    if (this.businessUnits.size() == 0){ return null;}
    BusinessUnit [] out= new BusinessUnit[this.businessUnits.size()];
    for (int i=0;i<this.businessUnits.size();i++){
      out[i]=this.businessUnits.get(i);
    }
    return out;
  }
  public int getBusinessCount(){
    return this.businessUnits.size();
  }

  /* SETTEURS */
  private void setNumber(int num){
    // Verification et incrémentation ????
    this.number = num;
  }
  public void setName(String name)throws ClientException{
    if (name == null){
      throw new ClientException("Il n'y a aucun Nom Spécifié pour ce client");
    }else if (name.length() <= 0){
      throw new ClientException("Le nom du client est invalide");
    }else if (name.length() > MAX_LONG_NOM){
      throw new ClientException("Le nom du client est trop long");
    }
    this.name = name;
  }
  public void setPhoneNumber(String phone)throws ClientException{
    if (phone == null){
      throw new ClientException("Il n'y a aucun Numéro de téléphone Spécifié pour ce client");
    }else if (phone.length() <= 0){
      throw new ClientException("Le numéro de téléphone est invalide");
    }else if (phone.length() > MAX_LONG_NUM_TEL){
      throw new ClientException("Le Numéro de téléphone du client est trop long");
    }
    this.phoneNumber = phone;
  }
  public void setDiscount(double discount)throws ClientException{
    if (discount < 0){
      throw new ClientException("La remise est incorrecte pour ce client");
    }else if (discount > 100){
      throw new ClientException("La remise est trop élevée");
    }
    this.discount = discount;
  }
  public void setVATNumber(String vatNum)throws ClientException{
    if (vatNum == null){
      this.VATNumber = null;
      return;
    }else if (vatNum.length() <= 0){
      throw new ClientException("VA");
    }else if (vatNum.length() > MAX_LONG_VAT){
      throw new ClientException("Le Numéro de TVA du client est trop long");
    }
    this.VATNumber = vatNum;
  }

  /* Utils */

  public String toString(){
    return getNumber() + " - " + getName() + " - " + getPhoneNumber() + " - " + getDiscount() + "% - " + getVATNumber();
  }

}
