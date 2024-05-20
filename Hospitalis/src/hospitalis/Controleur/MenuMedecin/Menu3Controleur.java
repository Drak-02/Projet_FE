/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hospitalis.Controleur.MenuMedecin;

import java.sql.Connection;
import hospitalis.Interface.componentMe.Menu3;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author pc
 */
public class Menu3Controleur implements MouseListener {
    private Connection connection;
    private Menu3 menu3;
    private static Menu3Controleur instanceM3;

    public Menu3Controleur(Connection connection, Menu3 menu3) {
        this.connection = connection;
        this.menu3 = menu3;

    }
    public void afficherMenu(){
        if (menu3.isVisible()) {
            menu3.setVisible(false);
        } else {
            menu3.setVisible(true);
        }
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
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
