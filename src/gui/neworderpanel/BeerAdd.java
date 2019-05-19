package gui.neworderpanel;

import controller.*;
import exceptions.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BeerAdd extends Container{

Controller controller;

private Container left,right;

  public BeerAdd(Controller controller){
    this.controller = controller;

    this.setLayout(new BorderLayout());

    BeerTable beerTable = new BeerTable(controller);

    this.add(new BeerAddForm(controller,beerTable), BorderLayout.WEST);
    this.add(beerTable, BorderLayout.EAST);

  }
}
