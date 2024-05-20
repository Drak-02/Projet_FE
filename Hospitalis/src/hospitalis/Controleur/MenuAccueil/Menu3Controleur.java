/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hospitalis.Controleur.MenuAccueil;

import hospitalis.Interface.componentAc.Menu3;
import hospitalis.Model.Chambre;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
/**
 *
 * @author pc
 */
public class Menu3Controleur implements MouseListener {
    private Connection connection;
    private Menu3 menu3;
    private Chambre chambre;
    private static Menu3Controleur instanceM3;
    
    public Menu3Controleur(Connection connection, Menu3 menu3) {
        this.connection = connection;
        this.menu3 = menu3;
        System.out.println("Appel creation menuCont1");
        this.menu3.btajout.addMouseListener(this);
        this.menu3.btsupp.addMouseListener(this);
        this.menu3.btmodi.addMouseListener(this);

    }

    public static Menu3Controleur getInstance(Connection connection, Menu3 menu3) {
        if (instanceM3 == null) {
            synchronized (Menu3Controleur.class) {
                if (instanceM3 == null) {
                    instanceM3 = new Menu3Controleur(connection,menu3);
                    System.out.println("Appel a l'instance de comtr men");
                }
            }
        }
        return instanceM3;
    }

    public void setMenu(Menu3 menu3) {
        this.menu3 = menu3;
        if (this.menu3 == null) {
            System.out.println("Le menu est null");
        } else {
            System.out.println("Le menu n'est pas null");
            this.menu3.btajout.addMouseListener(this);
            this.menu3.btsupp.addMouseListener(this);
            this.menu3.btmodi.addMouseListener(this);
        }
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
        if (e.getSource() == menu3.btajout) {
            handleAddChambre();
        } else if (e.getSource() == menu3.btsupp) {
            handleDeleteChambre();
        } else if (e.getSource() == menu3.btmodi) {
            handleModifyChambre();
        }
    }
    // A implementer
    public void handleAddChambre(){ }
    public void handleDeleteChambre(){  }
    public void handleModifyChambre(){ }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) { }
    
}
