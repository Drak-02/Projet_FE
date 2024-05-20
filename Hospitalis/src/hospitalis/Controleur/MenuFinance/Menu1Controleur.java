/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hospitalis.Controleur.MenuFinance;

import hospitalis.Interface.componentFi.Menu1;
import hospitalis.Model.Facture;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;

/**
 *
 * @author pc
 */
public class Menu1Controleur implements MouseListener {
    private Connection connection;
    private Menu1 menu1;
    private static Menu1Controleur instanceM1;
    private Facture facture;
    
    public Menu1Controleur(Connection connection , Menu1 menu1){
        this.connection = connection ;
        this.menu1 = menu1;
        this.menu1.btimpri.addMouseListener(this);
        this.menu1.btajout.addMouseListener(this);
        this.menu1.btsupp.addMouseListener(this);
    }
    public static Menu1Controleur getInstance(Connection connection,Menu1 menu1){
        if (instanceM1 == null) {
            synchronized (Menu1Controleur.class) {
                if (instanceM1 == null) {
                    instanceM1 = new Menu1Controleur(connection,menu1);
                    //System.out.println("Appel a l'instance de comtr men");
                }
            }
        }
        return instanceM1;
        
    }
    public void setMenu(Menu1 menu1) {
        this.menu1 = menu1;
        if (this.menu1 == null) {
            System.out.println("Le menu est null");
        } else {
            System.out.println("Le menu n'est pas null");
            this.menu1.btajout.addMouseListener(this);
            this.menu1.btsupp.addMouseListener(this);
            this.menu1.btimpri.addMouseListener(this);
        }
    }
   public void afficherMenu() {
       if (menu1.isVisible()) {
            menu1.setVisible(false);
        } else {
            menu1.setVisible(true);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
         if (e.getSource() == menu1.btajout) {
            handleAddFacture();
        } else if (e.getSource() == menu1.btsupp) {
            handleDeleteFacture();
        } else if (e.getSource() == menu1.btimpri) {
            handleImprimerFacture();
        }
   }
    public void handleAddFacture(){
        
    }
    public void handleDeleteFacture(){
        
    }
    public void handleImprimerFacture(){
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
}

    @Override
    public void mouseReleased(MouseEvent e) {
  }

    @Override
    public void mouseEntered(MouseEvent e) {
  }

    @Override
    public void mouseExited(MouseEvent e) {
  }
    
}
