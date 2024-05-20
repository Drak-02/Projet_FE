package hospitalis.Controleur;

import hospitalis.Controleur.MenuFinance.Menu1Controleur;
import hospitalis.Controleur.MenuFinance.Menu2Controleur;
import hospitalis.Controleur.MenuFinance.Menu3Controleur;
import hospitalis.Interface.ScreenFinance;
import hospitalis.Interface.componentFi.Menu1;
import hospitalis.Interface.componentFi.Menu2;
import hospitalis.Interface.componentFi.Menu3;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;

/**
 *
 * @author pc
 */
public class FinanceControleur implements MouseListener {
    private ScreenFinance screenFinance;
    private Connection connection;

    private Menu1 menu1;
    private Menu2 menu2;
    private Menu3 menu3;

    private Menu1Controleur menu1Controleur;
    private Menu2Controleur menu2Controleur;
    private Menu3Controleur menu3Controleur;
    
    public FinanceControleur(Connection connection) {
        this.connection = connection;
        screenFinance = new ScreenFinance();
        screenFinance.menu1.addMouseListener(this);
        screenFinance.menu2.addMouseListener(this);
        screenFinance.menu3.addMouseListener(this);

        menu1 = Menu1.getInstance();
        menu2 = Menu2.getInstance();
        menu3 = Menu3.getInstance();

        menu1Controleur = new Menu1Controleur(connection, menu1);
        menu2Controleur = new Menu2Controleur(connection, menu2);
        menu3Controleur = new Menu3Controleur(connection, menu3);
        
        // Simuler un événement de clic sur le menu1
        mouseClicked(new MouseEvent(screenFinance.menu1, MouseEvent.MOUSE_CLICKED, System.currentTimeMillis(), 0, 0, 0, 1, false));
    }

    public void afficherScreenFinance() {
        screenFinance.setVisible(true);
    }

    private void showMenu1() {
        screenFinance.getCenterPanel().removeAll();
        screenFinance.getCenterPanel().add(menu1);
        screenFinance.getCenterPanel().revalidate();
        screenFinance.getCenterPanel().repaint();
        menu1Controleur.afficherMenu();
        
    }

    private void showMenu2() {
        screenFinance.getCenterPanel().removeAll();
        screenFinance.getCenterPanel().add(menu2);
        screenFinance.getCenterPanel().revalidate();
        screenFinance.getCenterPanel().repaint();
        menu2Controleur.afficherMenu();
    }

    private void showMenu3() {
        screenFinance.getCenterPanel().removeAll();
        screenFinance.getCenterPanel().add(menu3);
        screenFinance.getCenterPanel().revalidate();
        screenFinance.getCenterPanel().repaint();
        menu3Controleur.afficherMenu();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == screenFinance.menu1) {
            showMenu1();
        } else if (e.getSource() == screenFinance.menu2) {
            showMenu2();
        } else if (e.getSource() == screenFinance.menu3) {
            showMenu3();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}
