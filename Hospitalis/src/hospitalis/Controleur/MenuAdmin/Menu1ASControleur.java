/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hospitalis.Controleur.MenuAdmin;

import hospitalis.Interface.componentAD.Menu1AS;
import java.sql.Connection;
/**
 *
 * @author badra
 */
public class Menu1ASControleur {
     
    private Connection connection;
    private Menu1AS menu;
    //
    public Menu1ASControleur(Connection connection){
        this.connection = connection;
    }
    
    public void setMenu(Menu1AS menu1A) {
        this.menu= menu1A;
    }
    public void afficherMenu(){
       if(menu.isVisible()){
            menu.setVisible(false);
        }else{
            menu.setVisible(true);
        }
    }
    
}
