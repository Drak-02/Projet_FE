/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hospitalis.Controleur.MenuMedecin;

import hospitalis.Interface.componentMe.Menu4;
import hospitalis.Model.Patient;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
/**
 *
 * @author pc
 */
public class Menu4Controleur implements MouseListener{
    
    private Patient patient;
    private  Connection connection;
    private Menu4 menu4;
    private static Menu4Controleur instanceM4;
    
    public Menu4Controleur(Connection connection , Menu4 menu4){
        this.connection = connection ;
        this.menu4 = menu4;
        this.menu4.btmodi.addMouseListener(this);
        this.menu4.btajout.addMouseListener(this);
        this.menu4.btsupp.addMouseListener(this);
    }
    public static Menu4Controleur getInstance(Connection connection,Menu4 menu4){
        if (instanceM4 == null) {
            synchronized (Menu1Controleur.class) {
                if (instanceM4 == null) {
                    instanceM4 = new Menu4Controleur(connection,menu4);
                    //System.out.println("Appel a l'instance de comtr men");
                }
            }
        }
        return instanceM4;
        
    }
    
     public void afficherMenu(){
        if (menu4.isVisible()) {
            menu4.setVisible(false);
        } else {
            menu4.setVisible(true);
        }
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == menu4.btajout) {
            handleAddPatient();
        } else if (e.getSource() == menu4.btsupp) {
            handleDeletePatient();
        } else if (e.getSource() == menu4.btmodi) {
            handleModifyPatient();
        }
    
    }
    public void handleAddPatient(){
        
    }
    public void handleDeletePatient(){
        
    }
    public void handleModifyPatient(){
        
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
