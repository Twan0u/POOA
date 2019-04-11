package composants;
/*
Programme de Gestion d'une brasserie
écrit par Antoine Lambert et Nathan Surquin
dans le cadre du cours de Programation avancée Orientée Objets
*/
import composants.exceptions.LocalityException;

public class Locality {
  /*Variables d'instances*/
  private static int counterid; // A VERIFIER
  private int idLocality;
  private String name;
  private String postCode;


  /*Methode constructeur pour les objets Client*/
  public Locality(int id, String name, String postCode)throws LocalityException{
    setIdLocality(id);
    setName(name);
    setPostCode(postCode);
  }
  public Locality(String name, String postCode)throws LocalityException{
    // id automatique
    setName(name);
    setPostCode(postCode);
  }
  //Getteurs et setteurs des varaibles qui doivent être modifiées

  /* GETTEURS */
  public int getIdLocality(){
    return this.idLocality;
  }
  public String getName(){
    return this.name;
  }
  public String getPostCode(){
    return this.postCode;
  }

  /* SETTEURS */
  private void setIdLocality(int num){
    //verification ??
    this.idLocality = num;
  }

  public void setName(String name)throws LocalityException{
    //max caractères
    if (name.length() == 0 || name.length() > 64 || name == null){
      throw new LocalityException("Nom de Localité Invalide");
    }
    this.name= name;
  }

  public void setPostCode(String postCode)throws LocalityException{
    try {
      int temp = Integer.parseInt(postCode);
    }catch (NumberFormatException err){
      throw new LocalityException("Code Postal de Localité Invalide");
    }
    this.postCode = postCode;
  }

  /* Utils */
  public String toString(){
    return getIdLocality() + " - " + getName() + " - " + getPostCode();
  }



}
