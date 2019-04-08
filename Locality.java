/*
Programme de Gestion d'une brasserie
écrit par Antoine Lambert et Nathan Surquin
dans le cadre du cours de Programation avancée Orientée Objets
*/

public class Locality {
  /*Variables d'instances*/
  private static int counterid; // A VERIFIER
  private int idLocality;
  private String name;
  private String postCode;


  /*Methode constructeur pour les objets Client*/
  public Locality(int id, String name, String postCode){
    setIdLocality(id);
    setName(name);
    setPostCode(postCode);
  }
  public Locality(String name, String postCode){
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
    //verification bla bla bla
    this.idLocality = num;
  }
  public void setName(String name){
    //VERIFIER
    this.name= name;
  }
  public void setPostCode(String postCode){
    //verification
    this.postCode = postCode;
  }

  /* Utils */
  public String toString(){
    return getIdLocality() + " - " + getName() + " - " + getPostCode();
  }



}
