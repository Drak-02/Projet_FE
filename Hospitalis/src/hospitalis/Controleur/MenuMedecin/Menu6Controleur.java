/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hospitalis.Controleur.MenuMedecin;

import hospitalis.Interface.componentMe.Menu6;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
/**
 *
 * @author pc
 */
public class Menu6Controleur implements MouseListener {
    private Connection connection;
    private static Menu6Controleur instanceM6;
    private Menu6 menu6;
    // ajout consultation private Consultation consultation;
    
    public Menu6Controleur(Connection connection , Menu6 menu6){
        this.connection = connection ;
        this.menu6 = menu6;
        this.menu6.btajout.addMouseListener(this);
        this.menu6.btsupp.addMouseListener(this);
    }
    public static Menu6Controleur getInstance(Connection connection,Menu6 menu6){
        if (instanceM6 == null) {
            synchronized (Menu1Controleur.class) {
                if (instanceM6 == null) {
                    instanceM6 = new Menu6Controleur(connection,menu6);
                    //System.out.println("Appel a l'instance de comtr men");
                }
            }
        }
        return instanceM6;
        
    }
    public void afficherMenu(){
        if (menu6.isVisible()) {
            menu6.setVisible(false);
        } else {
            menu6.setVisible(true);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
         if (e.getSource() == menu6.btajout) {
            handleAddConsultation();
        } else if (e.getSource() == menu6.btsupp) {
            handleDeleteConsultation();       
            }
    }
    public void handleAddConsultation(){
        
    }
    public void handleDeleteConsultation(){
        
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
