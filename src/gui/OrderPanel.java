package gui;

import controller.*;
import exceptions.*;
import composants.*;
import gui.neworderpanel.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.io.*;
import java.util.*;

public class OrderPanel extends JPanel{

  private Controller controller;

  private OrderAddForm orderAddForm;
  private BeerAddForm addBeerForm;
  private BeerTable table;

  private JButton validateButton;

  private Color colBackground;
  private Color colText;
  private Color colBis;

  public OrderPanel(Controller controller, Color colBackground, Color colText, Color colBis) {
    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    this.colText = colText;
    this.controller = controller;

    orderAddForm = new OrderAddForm(controller,colText);
    table = new BeerTable(controller);// doit être déclaré avant addBeer form
    addBeerForm = new BeerAddForm(controller,table);
    validateButton = new JButton("Sauvegarder La commande");

    this.add(orderAddForm);
    this.add(addBeerForm);
    this.add(table);
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
        JOptionPane.showMessageDialog (null, error.getMessage(),"ERREUR UTILISATEUR", JOptionPane.ERROR_MESSAGE);
      }catch(ProgramErrorException error){
        JOptionPane.showMessageDialog (null, error.getMessage(),"ERREUR INTERNE", JOptionPane.ERROR_MESSAGE);
      }
    }
  }

  /** Sauvegarde La commande en cours dans la base de donnée
  * @return numero de la commande sauvegardée
  * @throws ProgramErrorException erreur envoyée en cas de problème interne
  * @throws UserInputErrorException erreur causée par l'utilisateur
  */
  public int save()throws ProgramErrorException, UserInputErrorException {
    try{
      Order order = new Order();
      order.setClient(orderAddForm.getSelectedClient());
      order.setBusinessUnitId(orderAddForm.getSelectedBusiness());
      order.setOrderDate(orderAddForm.getSelectedDate());
      order.setTimeLimit(orderAddForm.getSelectedTimeLimit());
      order.setHasPriority(orderAddForm.getSelectedPriority());
      ArrayList<OrderLine> orderLines = addBeerForm.getOrderLines();
      for(int i=0;i<orderLines.size();i++){

        orderLines.get(i).setOrder(order);
      }
      return controller.saveOrder(order);

    }catch(OrderException error){
      throw new ProgramErrorException(error.getMessage());
    }catch(OrderLineException error){
      throw new ProgramErrorException(error.getMessage());
    }
    catch(UserInputErrorException e){
      throw new UserInputErrorException("Une valeur invalide a été entrée, la commande ne peut pas être sauvée");
    }
  }

  /** Recharge le contenu de la page
  */
  private void reload(){
    this.removeAll();

    orderAddForm = new OrderAddForm(controller,colText);
    table = new BeerTable(controller);
    addBeerForm = new BeerAddForm(controller,table);
    validateButton = new JButton("Sauvegarder La commande");

    this.add(orderAddForm);
    this.add(addBeerForm);
    this.add(table);
    this.add(validateButton);
    //setVisible(true);
    //this.repaint();
  }
}
