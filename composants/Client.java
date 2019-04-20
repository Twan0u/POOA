package composants;
import java.util.*;
import composants.exceptions.ClientException;

/**
* <b>classe de l'objet Client</b>
*
* @author Antoine Lambert et Nathan Surquin
* @version 1.3
*
* L'objet se compose de:
* <ul>
*   <li>un numéro d'identification</li>
*   <li>un nom</li>
*   <li>un numero de telephonne</li>
*   <li>une ristourne</li>
*   <li>un numero de TVA</li>
* </ul>
*
* <b>Important</b>
* La taille maximum du nom  est fixée avec la variable MAX_LONG_NOM
* La taille maximum du numero de telephonne est fixée avec la variable MAX_LONG_NUM_TEL
* La taille maximum du numero de TVA est fixée avec la variable MAX_LONG_NUM_VAT
*
* <b> A implementer</b>
* <ul>
*   <li>Implementation des IDs</li>
*   <li>Verification des numéros d'identification</li>
* </ul>
*
*/
public class Client {
  /*____VARIABLES____*/

  /**Taille maximum du nom du client*/
  private static int MAX_LONG_NOM = 64;
  /**Taille maximum du numéro de téléphonne*/
  private static int MAX_LONG_NUM_TEL = 18;
  /**Taille maximum du numéro de TVA*/
  private static int MAX_LONG_VAT = 32;
  /**Numéro d'identification client*/
  private int number;
  /**Nom du client*/
  private String name;
  /**Numéro de téléphonne du client*/
  private String phoneNumber;
  /**Ristourne liée au client */
  private double discount;
  /**Numéro de TVA du client (Facultatif)*/
  private String VATNumber;
  /**liste des business appartenant au client*/
  private ArrayList<BusinessUnit> businessUnits = new ArrayList<>();

  /*____Constructeurs____*/

  /** Methode constructeur pour les objets Client
  * @param number
  *             Numero d'identification.
  * @param name
  *             Nom du client.
  * @param phoneNumber
  *             Numero de telephonne.
  * @param discount
  *             Ristourne Client.
  * @param VATNumber
  *             Numero de Tva.
  * @throws ClientException erreur envoyée en cas de données erronnées
  * @since 1.0
  */
  public Client(int number, String name, String phoneNumber, double discount, String VATNumber)throws ClientException{
    setNumber(number);
    setName(name);
    setPhoneNumber(phoneNumber);
    setDiscount(discount);
    setVATNumber(VATNumber);
  }

  /** Methode constructeur pour les objets Client
  * @param number
  *             Numero d'identification.
  * @param name
  *             Nom du client.
  * @param phoneNumber
  *             Numero de telephonne.
  * @param discount
  *             Ristourne Client.
  * @throws ClientException erreur envoyée en cas de données erronnées
  * @since 1.1
  */
  public Client(int number, String name, String phoneNumber, double discount)throws ClientException{
    this(number, name, phoneNumber, discount, null);
  }

  /** Methode constructeur pour les objets Client
  * @param number
  *             Numero d'identification.
  * @param name
  *             Nom du client.
  * @param phoneNumber
  *             Numero de telephonne.
  * @throws ClientException erreur envoyée en cas de données erronnées
  * @since 1.1
  */
  public Client(int number, String name, String phoneNumber)throws ClientException{
    this(number, name, phoneNumber, 0, null);
  }

  /*____METHODES____*/

  /** Ajout d'un business dans la liste des business de ce client
  * @param business
  *             business a ajouter.
  * @see BusinessUnit
  * @since 1.3
  */
  void addBusinessUnit(BusinessUnit business){
    this.businessUnits.add(business);
  }

  /*____GETTEURS____*/

  /** Getteur pour la variable number
  * @return numéro d'identification du client
  * @since 1.0
  */
  public int getNumber(){
    return this.number;
  }

  /** Getteur pour la variable name
  * @return nom du client
  * @since 1.0
  */
  public String getName(){
    return this.name;
  }

  /** Getteur pour la variable phoneNumber
  * @return numéro de telephonne du client
  * @since 1.0
  */
  public String getPhoneNumber(){
    return this.phoneNumber;
  }

  /** Getteur pour la variable discount
  * @return ristourne à faire au client
  * @since 1.0
  */
  public double getDiscount(){
    return this.discount;
  }

  /** Getteur pour la variable VATNumber
  * @return numéro de TVA du client
  * @since 1.0
  */
  public String getVATNumber(){
    return this.VATNumber;
  }

  /** Getteur pour la variable businessUnits
  * @return un tableau contenant tous les business qui appartiennent au client
  * @see BusinessUnit
  * @since 1.3
  */
  public BusinessUnit[] getBusiness(){
    if (this.businessUnits.size() == 0){ return null;}
    BusinessUnit [] out= new BusinessUnit[this.businessUnits.size()];
    for (int i=0;i<this.businessUnits.size();i++){
      out[i]=this.businessUnits.get(i);
    }
    return out;
  }

  /** Calcule le nombre de business pour le client
  * @return nombre de business
  * @since 1.2
  * @deprecated Depuis Version 1.2, remplacé par businessCount()
  */
  public int getBusinessCount(){
    return businessCount();
  }

  /** retourne le nombre de business du client
  * @return nombre de business
  * @since 1.3
  */
  public int businessCount(){
    return this.businessUnits.size();
  }

  /**____SETTEURS____*/

  /** modifie le numéro d'identification d'un client
  * @param num
  *           numéro d'identification
  * @since 1.0
  */
  private void setNumber(int num){
    this.number = num;
  }

  /** Modifie le nom du client
  * @param name
  *           nom du client
  * @throws ClientException envoyée en cas de nom null ou trop court/long (MAX_LONG_NOM)
  * @since 1.1
  */
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

  /** Modifie le numéro de telephonne du client
  * @param phone
  *           numéro de telephonne
  * @throws ClientException envoyée en cas de numero null ou trop court/long (MAX_LONG_NUM_TEL)
  * @since 1.1
  */
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

  /** Modifie la ristourne associée au client
  * @param discount
  *           ristourne du client exprimée en %
  * @throws ClientException envoyée en cas de reduction inférieure à 0 ou suppérieure a 100
  * @since 1.1
  */
  public void setDiscount(double discount)throws ClientException{
    if (discount < 0){
      throw new ClientException("La remise est incorrecte pour ce client");
    }else if (discount > 100){
      throw new ClientException("La remise est trop élevée");
    }
    this.discount = discount;
  }

  /** Modifie le numéro de TVA du client
  * @param vatNum
  *           numéro de TVA
  * @throws ClientException envoyée en cas de numéro de Tva null ou avec un nom trop court/long (MAX_LONG_VAT)
  * @since 1.1
  */
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

  /*____TO STRING____*/

  /** génère une chaine de caractère décrivant l'objet BusinessUnit
  *  sous la forme "NUMERO - NOM - TELEPHONNE - DISCOUNT% - NUM_TVA"
  * @see Client
  * @see Client#getNumber
  * @see Client#getName
  * @see Client#getPhoneNumber
  * @see Client#getDiscount
  * @see Client#VATNumber
  * @return chaine de caractère décrivant le Client
  * @since 1.2
  */
  public String toString(){
    return getNumber() + " - " + getName() + " - " + getPhoneNumber() + " - " + getDiscount() + "% - " + getVATNumber();
  }

}
