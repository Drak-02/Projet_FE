/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hospitalis.Controleur.MenuAdmin;

import hospitalis.Interface.componentAD.Menu1A;
import java.sql.Connection;
/**
 *
 * @author badra
 */
public class Menu1AControleur {
    //
    private Connection connection;
    private Menu1A menu1A;
    //
    public Menu1AControleur(Connection connection){
        this.connection = connection;
    }
    //

    public void setMenu(Menu1A menu1A) {
        this.menu1A = menu1A;
    }
    public void afficherMenu1(){
        if(menu1A.isVisible()){
            menu1A.setVisible(false);
        }else{
            menu1A.setVisible(true);
        }
        
    }
  
}
