/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hospitalis.Controleur.MenuAdmin;

import hospitalis.Interface.componentAD.Menu1AT;
import java.sql.Connection;

/**
 *
 * @author badra
 */
public class Menu1ATControleur {
    //
    private Connection connection;
    private Menu1AT menu;
    //
    public Menu1ATControleur(Connection connection){
        this.connection = connection;
    }
    public void setMenu(Menu1AT menu1A) {
        this.menu = menu1A;
    }
    public void afficherMenu(){// me permet de pas avoir de menu chaque fois
       if(menu.isVisible()){
            menu.setVisible(false);
        }else{
            menu.setVisible(true);
        }   
    }
}
