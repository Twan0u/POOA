package composants;
import composants.exceptions.BusinessUnitException;

/**
* <b>classe de l'objet BusinessUnit</b>
*
* @author Antoine Lambert et Nathan Surquin
* @version 1.1
*
* L'objet se compose de:
* <ul>
* <li>un numéro d'identification</li>
* <li>un objet Client</li>
* <li>un objet Locality</li>
* <li>un nom de rue</li>
* <li>un numero de rue</li>
* </ul>
*
* <b>Important</b>
* La taille maximum du nom d'une rue est fixée avec la variable MAX_STREETNAME
* La taille maximum du numero d'une rue est fixée avec la variable MAX_LONG_STREET
*
* <b> A implementer</b>
* <ul>
*   <li>Implementation des IDs</li>
*   <li>Verification des numéros d'identification</li>
* </ul>
*
*/
public class BusinessUnit{
  /*____VARIABLES____*/

  /**Taille maximum de la longueur d'un numéro de batiment (string car possibilité de lettres ex: 23B)*/
  private static int MAX_LONG_STREET = 5;
  /**Taille maximum de la longueur d'un nom de rue*/
  private static int MAX_STREETNAME = 40;
  /**numéro d'identification d'un bâtiment*/
  private int idBusinessUnit;
  /**Objet Client lié avec ce business unit*/
  private Client client;
  /**Objet Locality dans laquelle se trouve le business*/
  private Locality locality;
  /**Nom de la rue du business*/
  private String streetName;
  /**numéro dans la rue du business*/
  private String streetNumber;

/*____Constructeurs____*/

/** Methode constructeur pour les objets BusinessUnit
* @param id
*             Numero d'identification du BusinessUnit.
* @param client
*             Objet Client propriétaire du BusinessUnit.
* @param locality
*             Objet Locality designant la localité dans laquelle se situe ce businessUnit.
* @param streetName
*             nom de la rue dans laquelle se situe le bâtiment.
* @param streetNumber
*             numero du bâtiment dans la rue.
* @throws BusinessUnitException erreur envoyée en cas de données erronnées
* @since 1.0
*/
  public BusinessUnit(int id, Client client, Locality locality, String streetName, String streetNumber)throws BusinessUnitException{
    setIdBusinessUnit(id);
    setClient(client);
    setLocality(locality);
    setStreetName(streetName);
    setStreetNumber(streetNumber);
  }

  /*____GETTEURS____*/

  /** Getteur pour la variable idBusinessUnit
  * @return numéro d'identification du bâtiment
  * @since 1.0
  */
  public int getIdBusinessUnit(){
    return this.idBusinessUnit;
  }

  /** Getteur pour la variable client
  * @return l'objet client associé au bâtiment
  * @see Client
  * @since 1.0
  */
  public Client getClient(){
    return this.client;
  }

  /** Getteur pour la variable locality
  * @return l'objet Locality associé au bâtiment
  * @see Locality
  * @since 1.0
  */
  public Locality getLocality(){
    return this.locality;
  }

  /** Getteur pour la variable streetName
  * @return le nom de la rue dans laquelle se situe le bâtiment
  * @since 1.0
  */
  public String getStreetName(){
    return this.streetName;
  }

  /** Getteur pour la variable streetNumber
  * @return le numero de la rue dans laquelle se situe le bâtiment
  * @since 1.0
  */
  public String getStreetNumber(){
    return this.streetNumber;
  }

  /*____SETTEURS____*/

  /** modifie le numéro d'identification d'un business
  * @param id
  *           numéro d'identification du businessUnit
  * @since 1.0
  */
  private void setIdBusinessUnit(int id){
    this.idBusinessUnit = id;
  }

  /** Modifie le client lié à ce business
  * @param client
  *           client lié au business
  * @throws BusinessUnitException envoyée en cas de client null
  * @see Client
  * @since 1.0
  */
  private void setClient(Client client)throws BusinessUnitException{
    if(client == null){
      throw new BusinessUnitException("Le Client est invalide");
    }
    this.client = client;
    client.addBusinessUnit(this);
  }

  /** Modifie la localité lié à ce business
  * @param locality
  *           localité ou se situe le business.
  * @see Locality
  * @throws BusinessUnitException envoyée en cas de localité null.
  * @since 1.0
  */
  private void setLocality(Locality locality)throws BusinessUnitException{
    if(locality == null){
      throw new BusinessUnitException("La Localité est invalide");
    }
    this.locality = locality;
  }

  /** Modifie le nom de la rue liée à ce business.
  * @param streetName
  *           nom de la rue.
  * @throws BusinessUnitException envoyée en cas de nom null ou trop long(MAX_STREETNAME)
  * @since 1.0
  */
  private void setStreetName(String streetName)throws BusinessUnitException{
    if(streetName == null){
      throw new BusinessUnitException("Le nom de la rue est invalide");
    }else if(streetName.length() > MAX_STREETNAME){
      throw new BusinessUnitException("Le nom de la rue est trop long");
    }
    this.streetName = streetName;
  }

  /** Modifie le numéro dans la rue liée à ce business.
  * @param streetNumber
  *           numéro dans la rue
  * @throws BusinessUnitException envoyée en cas de numéro null ou trop long(MAX_LONG_STREET)
  * @since 1.0
  */
  private void setStreetNumber(String streetNumber)throws BusinessUnitException{
    if(locality == null){
      throw new BusinessUnitException("Le numero est invalide");
    }else if(streetNumber.length() > MAX_LONG_STREET){
      throw new BusinessUnitException("Le numero est trop long");
    }
    this.streetNumber = streetNumber;
  }

  /*____TO STRING____*/

  /** génère une chaine de caractère décrivant l'objet BusinessUnit
  *  sous la forme "Client: NOM_CLIENT - PHONE_CLIENT   Situé à: NOM_LOCALITE CODEPOSTAL_LOCALITE NUMERO_RUE NOM RUE
  * @see Client
  * @see Client#getName
  * @see Client#getPhoneNumber
  * @see Locality
  * @see Locality#getName
  * @see Locality#getPostCode
  * @see BusinessUnit#getStreetNumber
  * @see BusinessUnit#getStreetName
  * @return chaine de caractère décrivant le businessUnit

  * @since 1.2
  */
  public String toString(){
    return "Client:\t\t " + client.getName() + " - " + client.getPhoneNumber() + "\nSitué à:\t" + locality.getName() + " " + locality.getPostCode()  + " \n\t\t" + this.getStreetNumber() + " " + this.getStreetName() ;
  }
}
