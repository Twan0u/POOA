/*
Programme de Gestion d'une brasserie
écrit par Antoine Lambert et Nathan Surquin
dans le cadre du cours de Programation avancée Orientée Objets
*/

import javax.swing.*;
import composants.BusinessUnit;

public class Interface extends JFrame{
  JPanel pannel = new JPanel();

  JLabel l;
  JLabel l2;
  JComboBox cb;
  JComboBox bubusiness;

  /* TO TEST ONLY*/
  public static void main(String[] args){
    //new Interface("1","2");
  }

  /* Creation d'une interface basique */
  public Interface(){
    super("Outil de Création de Commandes");
    setSize(400,300);
    setResizable(true);

    l= new JLabel("Client : ");
    cb = new JComboBox(BrassiGestion.getClients());

    l2= new JLabel("\n Business : ");

    bubusiness = new JComboBox(BrassiGestion.getBusinessOfClient(0));



    pannel.add(l);
    pannel.add(cb);

    pannel.add(l2);
    pannel.add(bubusiness);


    add(pannel);
    setVisible(true);
    System.out.println("Fin Interface");
  }
}
