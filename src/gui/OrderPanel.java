package gui;

import controller.*;
import exceptions.*;
import composants.*;
import gui.neworderpanel.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class OrderPanel extends Container{

  private Controller controller;

  private OrderAddForm orderAddForm;
  private Container addBeerForm;
  private JButton validateButton;

  private Color colBackground;
  private Color colText;
  private Color colBis;

  public OrderPanel(Controller controller, Color colBackground, Color colText, Color colBis) {
    this.setLayout(new FlowLayout());
    this.colText = colText;

    validateButton = new JButton("Sauvegarder La commande");

    try{
      this.controller = new Controller();
    }catch(Exception e){
      JOptionPane.showMessageDialog(null, e.getMessage(),"FATAL_ERROR", JOptionPane.ERROR_MESSAGE);
      System.exit(1);
    }
    orderAddForm = new OrderAddForm(controller,colText);
    this.add(orderAddForm);
    //zaddBeerForm = new BeerAdd(controller);
    //this.add(addBeerForm);
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
  try{
    Order order = new Order();
    order.setClient(orderAddForm.getSelectedClient());
    order.setBusinessUnitId(orderAddForm.getSelectedBusiness());
    order.setOrderDate(orderAddForm.getSelectedDate());
    order.setTimeLimit(orderAddForm.getSelectedTimeLimit());
    order.setHasPriority(orderAddForm.getSelectedPriority());
    return controller.saveOrder(order);
  }catch(OrderException error){
    throw new ProgramErrorException(error.getMessage());
  }
}

private void reload(){
  this.removeAll();
  this.add(new OrderAddForm(controller,colText));
  this.add(new BeerAdd(controller));
  this.add(validateButton);
  this.repaint();
}



}
