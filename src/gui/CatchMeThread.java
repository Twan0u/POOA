package gui;

import javax.swing.*;

import java.awt.*;

public class CatchMeThread extends Thread{

    JFrame frame;
    int height;
    int width;
    JLabel label;
    Color[] couleurs={Color.red,Color.green,Color.blue,Color.pink,Color.orange,Color.cyan,Color.black,Color.yellow};

    public CatchMeThread(){
<<<<<<< HEAD
      frame = new JFrame();
      frame.pack();
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      //frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
      height = screenSize.height;
      width = screenSize.width;
      frame.setSize(width/10, height/10);
      label = new JLabel("Félicitation");
      frame.add(label);
      frame.setVisible(true);
=======
        frame = new JFrame();
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        height = screenSize.height;
        width = screenSize.width;
        frame.setSize(width/7, height/12);
        label = new JLabel("Félicitation vous êtes le 999 999ème visiteur!");
        frame.add(label);
        frame.setVisible(true);
>>>>>>> 1d9ab7bfa8f801e30373c3ed9eb25e742f42d303
    }

    public void run(){
        while (true){
<<<<<<< HEAD
          label.setForeground(couleurs[Math.toIntExact(Math.round(Math.random() * 6))]);
          frame.setBackground(couleurs[Math.toIntExact(Math.round(Math.random() * 6))]);
          frame.setLocation(Math.toIntExact(Math.round(Math.random() * this.width)),Math.toIntExact(Math.round(Math.random() * this.height)));
          frame.setVisible(true);
          try {
              sleep(500);
          } catch (InterruptedException error) {
              JOptionPane.showMessageDialog (null, "Le thread n'est pas fatigué et ne veut pas aller dormir","ERREUR", JOptionPane.ERROR_MESSAGE);
          }
=======
            label.setForeground(couleurs[Math.toIntExact(Math.round(Math.random() * 6))]);
            frame.setBackground(couleurs[Math.toIntExact(Math.round(Math.random() * 6))]);
            frame.setLocation(Math.toIntExact(Math.round(Math.random() * this.width)),Math.toIntExact(Math.round(Math.random() * this.height)));
            frame.setVisible(true);
            try {
                sleep(2000);
            } catch (InterruptedException error) {
                JOptionPane.showMessageDialog (null, "Le thread n'est pas fatigué et ne veut pas aller dormir","ERREUR", JOptionPane.ERROR_MESSAGE);
            }
>>>>>>> 1d9ab7bfa8f801e30373c3ed9eb25e742f42d303
        }
    }
}