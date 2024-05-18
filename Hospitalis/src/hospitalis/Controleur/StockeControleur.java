/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hospitalis.Controleur;

import hospitalis.Interface.ScreenStock;
import java.sql.Connection;


/**
 *
 * @author badra
 */
public class StockeControleur {
    //Attributsp
    private Connection connection;
    private ScreenStock screenStock;
    //Constructeurs
    public StockeControleur(Connection connection){
        this.connection = connection;
        screenStock = new ScreenStock();
    }
    //Methode
    public void afficherStocke(){
        screenStock.setVisible(true);
    }
}
