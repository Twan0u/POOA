package gui;

import controller.*;
import exceptions.*;

import javax.swing.*;
import java.awt.event.*;

import java.awt.*;

public class MenuPanel extends JPanel {

  private JLabel logo,commande, stock, livraison, comptabilite;

  public MenuPanel(Color colBackground){

    ImageIcon icon = new ImageIcon("ressources/logo.png");
    logo = new JLabel(icon);

    ImageIcon commandeIcon = new ImageIcon("ressources/plus.png");
    commande = new JLabel(commandeIcon);

    ImageIcon stockIcon = new ImageIcon("ressources/stock.png");
    stock = new JLabel(stockIcon);

    ImageIcon livraisonIcon = new ImageIcon("ressources/livraison.png");
    livraison = new JLabel(livraisonIcon);

    ImageIcon comptabiliteIcon = new ImageIcon("ressources/compta.png");
    comptabilite = new JLabel(comptabiliteIcon);


    this.setLayout(new GridLayout(5,1));
    this.add(logo);
    this.add(commande);
    this.add(stock);
    this.add(livraison);
    this.add(comptabilite);
    this.setBackground(colBackground);
  }
}
