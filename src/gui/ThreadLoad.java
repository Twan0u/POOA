package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ThreadLoad extends Thread{

JLabel label;
boolean load;

  public ThreadLoad(String path){
    label = new JLabel("Chargement");
    load = true;
  }

  public void run(){
    while(load){
      try{
        label.setForeground(Color.BLACK);
        Thread.sleep(100);
        
        label.setForeground(Color.WHITE);
        Thread.sleep(100);
      }catch(Exception e){
      }
    }
  }

  public JLabel getLabel(){
    return this.label;
  }


}
