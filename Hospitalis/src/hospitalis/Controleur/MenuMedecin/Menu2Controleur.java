/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hospitalis.Controleur.MenuMedecin;

import hospitalis.Interface.componentMe.Menu2;
import hospitalis.Model.Calender;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;

/**
 *
 * @author pc
 */
public class Menu2Controleur implements MouseListener {
    private Connection connection;
    private Menu2 menu2;
    private static Menu2Controleur instanceM2;
    private Calender calendrier;
    
     public Menu2Controleur(Connection connection , Menu2 menu2){
        this.connection = connection;
        this.menu2 =menu2;
        
        this.menu2.btmodi.addMouseListener(this);
        this.menu2.btajout.addMouseListener(this);
    }
    public void setMenu(Menu2 menu2) {
        this.menu2 = menu2;
        if (this.menu2 == null) {
            System.out.println("Le menu est null");
        } else {
            System.out.println("Le menu n'est pas null");
            this.menu2.btajout.addMouseListener(this);
           
            this.menu2.btmodi.addMouseListener(this);
        }
    }
    public static Menu2Controleur getInstance(Connection connection,Menu2 menu2){
        if (instanceM2 == null) {
            synchronized (Menu2Controleur.class) {
                if (instanceM2 == null) {
                    instanceM2 = new Menu2Controleur(connection,menu2);
                    //System.out.println("Appel a l'instance de comtr men");
                }
            }
        }
        return instanceM2;
        
    }
    public void afficherMenu(){
        if (menu2.isVisible()) {
            menu2.setVisible(false);
        } else {
            menu2.setVisible(true);
        }
    }
    

    @Override
    public void mouseClicked(MouseEvent e) {
          if (e.getSource() == menu2.btajout) {
            handleAddCalendrier();
        
        } else if (e.getSource() == menu2.btmodi) {
            handleModifyCalendrier();
        }
    
    }
    public void handleAddCalendrier(){
        
    }
  
    public void handleModifyCalendrier(){
        
    
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
