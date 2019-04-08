import javax.swing.*;

public class Interface extends JFrame{
  JPanel pannel = new JPanel();

  JButton b;

  public static void main(String[] args){
    new Interface("1");
  }
  public Interface(String bouton){
    super("Chargement");
    setSize(400,300);
    setResizable(true);

    b = new JButton(bouton);

    pannel.add(b);
    add(pannel);
    setVisible(true);
    System.out.println("Fin Interface");
  }
}
