/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hospitalis.Controleur.MenuMedecin;

import hospitalis.Interface.componentMe.Menu5;
import hospitalis.Model.Chambre;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;

/**
 *
 * @author pc
 */
public class Menu5Controleur implements MouseListener {
    private static Menu5Controleur instanceM5;
    private Connection connection;
    private Menu5 menu5;
    private Chambre chambre;
    
    
    
    public Menu5Controleur(Connection connection , Menu5 menu5){
        this.connection = connection ;
        this.menu5 = menu5;
        this.menu5.btajout.addMouseListener(this);
        this.menu5.btsupp.addMouseListener(this);
    }
    public static Menu5Controleur getInstance(Connection connection,Menu5 menu5){
        if (instanceM5 == null) {
            synchronized (Menu1Controleur.class) {
                if (instanceM5 == null) {
                    instanceM5 = new Menu5Controleur(connection,menu5);
                    //System.out.println("Appel a l'instance de comtr men");
                }
            }
        }
        return instanceM5;
        
    }
    public void afficherMenu(){
        if (menu5.isVisible()) {
            menu5.setVisible(false);
        } else {
            menu5.setVisible(true);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
          if (e.getSource() == menu5.btajout) {
            handleAddChambre();
        } else if (e.getSource() == menu5.btsupp) {
            handleDeleteChambre();
        } else if (e.getSource() == menu5.btmodi) {
            handleModifyChambre();
        }
    
    }
    public void handleAddChambre(){
        
    }
    public void handleDeleteChambre(){
        
    }
    public void handleModifyChambre(){
        
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
