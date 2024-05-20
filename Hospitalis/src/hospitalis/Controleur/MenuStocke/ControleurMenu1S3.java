/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hospitalis.Controleur.MenuStocke;

import hospitalis.Interface.componentSt.Menu1S3;
import java.sql.Connection;

/**
 *
 * @author badra
 */
public class ControleurMenu1S3 {
    //
    private Connection connection;
    private Menu1S3 menu;
    private static ControleurMenu1S3 instance;
    //
    public ControleurMenu1S3(Connection connection, Menu1S3 menu) {
        this.connection = connection;
        this.menu = menu;
        System.out.println("Appe creation menuStocke1 ");
        /*
        this.menu.btajouter.addMouseListener(this);
        this.menu1A.btsupprimer.addMouseListener(this);
        this.menu1A.btmodifier.addMouseListener(this);
        this.menu1A.jtables.getSelectionModel().addListSelectionListener(this);
        chargementDeUsers();
        */
    }
    //
    //Contrôle la creation des instances lors de l'arriver pour ne pas créer a chaque fois une autre instance ( surcharge).
    public static ControleurMenu1S3 getInstance(Connection connection, Menu1S3 menu) {
        if (instance == null) {
            synchronized (ControleurMenu1S3.class) {
                if (instance == null) {
                    instance = new ControleurMenu1S3(connection,menu);
                    System.out.println("Appel a l'instance de comtr men");
                }
            }
        }
        return instance;
    }
    ///
    public void afficherMenu() {
        if (menu.isVisible()) {
            menu.setVisible(false);
        } else {
            menu.setVisible(true);
        }
    }
    //**************************************************************************
   
}
