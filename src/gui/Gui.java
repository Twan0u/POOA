package gui;

import controller.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Gui extends JFrame{


  private Container frameContainer,  mainPanel;
  private JPanel menuPanel;

  private JLabel logo, commande, modifier, recherche,rechercheClient, stock, livraison, prepare;

  private Color colText = new Color(29, 34, 40);
  private Color colBis = new Color(250,229,150);
  private Color colBackground = new Color(224,255,255);
  private Color colSidePanel = new Color(63,176,172);

  private String path;

  public Gui(String path) {
    super("BrassiGestion");
    this.path = path;

    setSize(1080,720);
    setResizable(true);
    menuPanel = new JPanel();
    menuPanel.setPreferredSize(new Dimension(125, 100));
    menuPanel.setBackground(colSidePanel);

    loadMenuPanel();

    ThreadLoad threadX = new ThreadLoad(path);
    Thread thread = new Thread(threadX);
    thread.start();

    mainPanel = new OrderPanel(colBackground, colText, colBis);

    frameContainer = this.getContentPane();
    frameContainer.setLayout(new BorderLayout());
    frameContainer.add(menuPanel, BorderLayout.WEST );
    frameContainer.add(mainPanel, BorderLayout.CENTER );

    setVisible(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  public void loadMenuPanel(){

        ImageIcon icon = new ImageIcon(path + "logo.png");
        logo = new JLabel(icon);

        ImageIcon commandeIcon = new ImageIcon(path + "commande.png");
        commande = new JLabel(commandeIcon);

        ImageIcon modifierIcon = new ImageIcon(path + "edit.png");
        modifier = new JLabel(modifierIcon);

        ImageIcon rechercheIcon = new ImageIcon(path + "search.png");
        recherche = new JLabel(rechercheIcon);

        ImageIcon rechercheClientIcon = new ImageIcon(path + "user.png");
        rechercheClient = new JLabel(rechercheClientIcon);

        ImageIcon prepareIcon = new ImageIcon(path + "Preparation.png");
        prepare = new JLabel(prepareIcon);

        ImageIcon stockIcon = new ImageIcon(path + "stock.png");
        stock = new JLabel(stockIcon);

        ImageIcon livraisonIcon = new ImageIcon(path + "livraison.png");
        livraison = new JLabel(livraisonIcon);

        menuPanel.setLayout(new GridLayout(8,1));
        menuPanel.add(logo);
        menuPanel.add(commande);
        menuPanel.add(modifier);
        menuPanel.add(recherche);
        menuPanel.add(rechercheClient);
        menuPanel.add(prepare);
        menuPanel.add(stock);
        menuPanel.add(livraison);


        commande.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
              changeMainPanel(new OrderPanel(colBackground, colText, colBis));
            }
        });

        modifier.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
               changeMainPanel(new ModifyPanel());
            }
        });

        recherche.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
              changeMainPanel(new SearchPanel());
               //JOptionPane.showMessageDialog(null,"L'onglet recherche n'est pas encore disponible");
            }
        });

        rechercheClient.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
              changeMainPanel(new SearchOrderByClientPanel());
            }
        });


        prepare.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
              changeMainPanel(new PreparePanel(colBackground, colText));
            }
        });

        stock.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
              changeMainPanel(new StockPanel(colBackground, colText));
            }
        });

        livraison.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
              changeMainPanel(new DeliveryPanel());
               //JOptionPane.showMessageDialog(null,"L'onglet Livraison n'est pas encore disponible");
            }
        });


  }
  public void changeMainPanel(Container newPanel){
    if(JOptionPane.showConfirmDialog (null, "êtes-vous sur de vouloir quitter? ","Warning",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
       frameContainer.removeAll();
       frameContainer.add(menuPanel, BorderLayout.WEST );
       frameContainer.add(newPanel, BorderLayout.CENTER );
       frameContainer.revalidate();
   }
  }

}
