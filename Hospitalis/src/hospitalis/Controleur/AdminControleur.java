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

public class AdminControleur implements MouseListener {
    // Attributs
    private final ScreenAdmin screenAdmin;
    private final Connection connection;
    
    private Menu1A menu1A;    
    private Menu1AS menu1AS;
    private Menu1AT menu1AT;
    
    private Menu1AControleur menu1AControleur;
    

    // Constructeur
    public AdminControleur(Connection connection) {
        this.connection = connection;
        screenAdmin = new ScreenAdmin();
        this.screenAdmin.menu1.addMouseListener(this);
        this.screenAdmin.menu2.addMouseListener(this);
        this.screenAdmin.menu3.addMouseListener(this);
        
        
        this.menu1A = Menu1A.getInstance();
        this.menu1AS = Menu1AS.getInstance();
        this.menu1AT = Menu1AT.getInstance();
        
        this.menu1AControleur = Menu1AControleur.getInstance(connection, menu1A);
    }
    // Méthode
    public void afficherScreenAdmin() {
        screenAdmin.setVisible(true);
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        screenAdmin.getCenterPanel().removeAll();

        if (e.getSource() == screenAdmin.menu1) {
            showMenu1A();
        } else if (e.getSource() == screenAdmin.menu2) {
            showMenu1AS();
        } else if (e.getSource() == screenAdmin.menu3) {
            showMenu1AT();
        }
        screenAdmin.getCenterPanel().revalidate();
        screenAdmin.getCenterPanel().repaint();
    }
    //**************
    private void showMenu1A() {
        System.out.println("Appel a getInstance");
        //Menu1A menu1A = Menu1A.getInstance(); // Using the singleton instance
        screenAdmin.getCenterPanel().add(menu1A);
        menu1AControleur.afficherMenu();
    }
    private void showMenu1AS() {
        Menu1ASControleur menu1ASControleur = new Menu1ASControleur(connection);
        menu1ASControleur.setMenu(menu1AS);
        screenAdmin.getCenterPanel().add(menu1AS);
        menu1ASControleur.afficherMenu();
    }

    private void showMenu1AT() {
        Menu1AT menu1AT = Menu1AT.getInstance(); // Using the singleton instance
        Menu1ATControleur menu1ATControleur = new Menu1ATControleur(connection);
        menu1ATControleur.setMenu(menu1AT);
        screenAdmin.getCenterPanel().add(menu1AT);
        menu1ATControleur.afficherMenu();
    }
    //**************

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}
