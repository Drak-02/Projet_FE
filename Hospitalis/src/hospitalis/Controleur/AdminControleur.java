/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hospitalis.Controleur;

import hospitalis.Interface.ScreenAdmin;
import hospitalis.Model.UserAdmin;
import java.sql.Connection;


/**
 *
 * @author badra
 */
public class AdminControleur {
    //Attributs
    private ScreenAdmin screenAdmin;
    private UserAdmin userAdmin;
    private Connection connection;
    
    //Constructeur
    public AdminControleur(Connection connection){
        this.connection = connection;
        screenAdmin = new ScreenAdmin();
    }
    public AdminControleur(){
        
    }
    
    //Methode

    public void afficherScreenAdmin() {
        screenAdmin.setVisible(true);
    }
    
    
}
