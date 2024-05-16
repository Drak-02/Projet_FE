/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package hospitalis;

import hospitalis.Controleur.ControleurBDD;

/**
 *
 * @author badra
 */
public class Hospitalis {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //Vérification de la connection a la Base de données
        String url = "jdbc:mysql://localhost:3306/mabase";
        String username = "root";
        String password = "";        
        ControleurBDD BDD = new ControleurBDD(url,username,password);
    }
    
}
