/*
Programme de Gestion d'une brasserie
écrit par Antoine Lambert et Nathan Surquin
dans le cadre du cours de Programation avancée Orientée Objets
*/

import javax.swing.*;

public class Interface extends JFrame{
  JPanel pannel = new JPanel();

  JButton b;

  /* TO TEST ONLY*/
  public static void main(String[] args){
    new Interface("1","2");
  }

  /* Creation d'une interface basique */
  public Interface(String titre, String bouton){
    super(titre);
    setSize(400,300);
    setResizable(true);

    b = new JButton(bouton);

    pannel.add(b);
    add(pannel);
    setVisible(true);
    System.out.println("Fin Interface");
  }
}
