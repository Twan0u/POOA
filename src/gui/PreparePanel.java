package gui;

import controller.*;
import exceptions.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PreparePanel extends Container{

  private JPanel base;
  private JScrollPane sp;

  private JTable table;

  public PreparePanel(Color colBackground,Color colText) {
    this.setBackground(colBackground);
    this.setLayout(new GridLayout(1,2));

    base = new JPanel();
    base.add(new JLabel("Onglet PREPARATION DE COMMANDE"));

    this.add(base);
  }
}
