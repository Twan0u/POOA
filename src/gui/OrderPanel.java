package gui;

import controller.*;
import exceptions.*;
import gui.neworderpanel.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class OrderPanel extends Container{

  private ControllerNewOrder controller;

  private OrderAddForm orderAddForm;
  private Container addBeerForm;
  private JButton validateButton;

  private Color colBackground;
  private Color colText;
  private Color colBis;

  public OrderPanel(Color colBackground, Color colText, Color colBis) {
    this.setLayout(new FlowLayout());
    this.colText = colText;
    //this.colBackground = colBackground;
    //this.colBis = colBis;
    //this.setBackground(colBackground);

    validateButton = new JButton("Sauvegarder La commande");
    //validateButton.setBackground(colBis);
    try{
      this.controller = new ControllerNewOrder();
    }catch(Exception e){
      JOptionPane.showMessageDialog(null, e.getMessage(),"FATAL_ERROR", JOptionPane.ERROR_MESSAGE);
      System.exit(1);
    }
    orderAddForm = new OrderAddForm(controller,colText);
    this.add(orderAddForm);
    this.add(new BeerAdd(controller));
    this.add(validateButton);

    ButtonSaveListener listenerSave = new ButtonSaveListener();
    validateButton.addActionListener(listenerSave);
  }


  private class ButtonSaveListener implements ActionListener{
    public void actionPerformed( ActionEvent event) {
      try{
        JOptionPane.showMessageDialog (null, "Votre Commande a été ajoutée avec succes. Votre Numéro de Commande est le :" + save(),"Succes de L'ajout", JOptionPane.INFORMATION_MESSAGE);
        reload();
      }catch(UserInputErrorException error){
        JOptionPane.showMessageDialog (null, error.getMessage(),"ERREUR", JOptionPane.ERROR_MESSAGE);
      }catch(ProgramErrorException error){
        JOptionPane.showMessageDialog (null, error.getMessage(),"ERREUR", JOptionPane.ERROR_MESSAGE);
      }
    }
  }

public int save()throws ProgramErrorException, UserInputErrorException{
  int idNewOrder  = controller.saveOrder(orderAddForm.getSelectedBusiness(),orderAddForm.getSelectedDate(),orderAddForm.getSelectedTimeLimit(),orderAddForm.getSelectedPriority());
  return idNewOrder;
}

private void reload(){
  this.removeAll();
    this.repaint();
  try{
    this.controller = new ControllerNewOrder();
  }catch(Exception e){
    JOptionPane.showMessageDialog(null, e.getMessage(),"FATAL_ERROR", JOptionPane.ERROR_MESSAGE);
    System.exit(1);
  }
  this.add(new OrderAddForm(controller,colText));
  this.add(new BeerAdd(controller));
  this.add(validateButton);
  this.repaint();
}

  private void load(){
    try{
      this.controller = new ControllerNewOrder();
    }catch(Exception e){
      JOptionPane.showMessageDialog(null, e.getMessage(),"FATAL_ERROR", JOptionPane.ERROR_MESSAGE);
      System.exit(1);
    }


  }


}
