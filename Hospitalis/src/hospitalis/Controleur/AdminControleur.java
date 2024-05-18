/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hospitalis.Controleur;

import hospitalis.Controleur.MenuAdmin.Menu1AControleur;
import hospitalis.Controleur.MenuAdmin.Menu1ASControleur;
import hospitalis.Controleur.MenuAdmin.Menu1ATControleur;
import hospitalis.Interface.ScreenAdmin;
import hospitalis.Interface.componentAD.Menu1A;
import hospitalis.Interface.componentAD.Menu1AS;
import hospitalis.Interface.componentAD.Menu1AT;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;


/**
 *
 * @author badra
 */
public  class AdminControleur implements MouseListener {
    //Attributs
    private final ScreenAdmin screenAdmin;
    private final Connection connection;
    
    private Menu1A menu1A;    
    private Menu1AS menu1AS;
    private Menu1AT menu1AT;
    
    
    //Constructeur
    public AdminControleur(Connection connection){
        this.connection = connection;
        screenAdmin = new ScreenAdmin();
        this.screenAdmin.menu1.addMouseListener(this);
        this.screenAdmin.menu2.addMouseListener(this);
        this.screenAdmin.menu3.addMouseListener(this);
        //
        

    }
    
    //Methode

    public void afficherScreenAdmin() {
        screenAdmin.setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == screenAdmin.menu1){
            menu1A = Menu1A.getInstance(); // Utilisation de l'instance singleton
            screenAdmin.getCenterPanel().removeAll();
            //Appel le contrôleur de menu1
            Menu1AControleur menu1AControleur = new Menu1AControleur(connection);
            menu1AControleur.setMenu(menu1A);
            screenAdmin.getCenterPanel().add(menu1A);
            menu1AControleur.afficherMenu1();

        }else if(e.getSource() == screenAdmin.menu2){
            menu1AS = Menu1AS.getInstance(); // Utilisation de l'instance singleton
            screenAdmin.getCenterPanel().removeAll();

            Menu1ASControleur menu1ASControleur = new Menu1ASControleur(connection);
            menu1ASControleur.setMenu(menu1AS);
            screenAdmin.getCenterPanel().add(menu1AS);
            menu1ASControleur.afficherMenu();
        }else if(e.getSource() == screenAdmin.menu3){
            menu1AT = Menu1AT.getInstance(); // Utilisation de l'instance singleton
            screenAdmin.getCenterPanel().removeAll();

            Menu1ATControleur menu1ASControleur = new Menu1ATControleur(connection);
            menu1ASControleur.setMenu(menu1AT);
            screenAdmin.getCenterPanel().add(menu1AT);
            menu1ASControleur.afficherMenu();    }
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
