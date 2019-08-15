package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ThreadLoad implements Runnable{

private int [] etat; //1 == chargement et 0 == chargé
private JLabel label;
JFrame frame;

  public ThreadLoad(int[] etat){
    this.etat = etat;
    label = new JLabel("Chargement");
    frame = new JFrame("My JFrame Example");
    frame.setLayout(new BorderLayout());
    frame.add(label);
    frame.setPreferredSize(new Dimension(400, 200));
    frame.pack();
    frame.setVisible(true);
  }

  public void run(){
    double loadTime= 0;
    while(etat[0]==1){
      try{
        Thread.sleep(100);
        loadTime++;
      }catch(InterruptedException ignore){}
    }
    System.out.println("Chargement du Programme en :"  + Double.toString(loadTime/10) + " Secondes");//Not accurate and not necessary
    frame.dispose();
  }

  public JLabel getLabel(){
    return this.label;
  }
}
