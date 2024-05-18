/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hospitalis.Controleur;

import hospitalis.Interface.ScreenAdmin;
import hospitalis.Interface.ScreenAuthentification;
import hospitalis.Model.Utilisateurs;
import java.sql.Connection;


/**
 *
 * @author badra
 */
public class Authentification {
    
    //Attributs
    private ScreenAuthentification screenAuthentification;
    private Utilisateurs users;
    protected Connection connection;
    
    //Constructeur
    //Lors de la création de l'instance on obtient la connexion avec la base de données 
    public Authentification (Connection connection){
        this.screenAuthentification = new ScreenAuthentification();
        // Lors de la création de l'instance de l'utilisateur on envoyer la connection existant pour les methodes
        this.users = new Utilisateurs(connection);
        this.connection = connection;
    }
    
    //Methode
    public void afficherFenetreAuthentication(){
        screenAuthentification.setVisible(true);
    }
    //Essayer de stock
    //Cette methode nous permet de premet le meilleurs chemin pour chaque interface
    public void redirection() {
    // Obtenez le rôle de l'utilisateur
    String role = users.getRole();
    
    // Effectuez la redirection en fonction du rôle
    // Exemple : new chemin().setVisible(true); et envoyer la connection a la base de données
    switch (role) {
        case "admin":
            // Redirection pour l'administrateur
            AdminControleur adminControleur = new AdminControleur(connection);
            adminControleur.afficherScreenAdmin();
            break;
        case "medecin":
            // Redirection pour le médecin
            break;
        case "finance":
            // Redirection pour le service finance
            break;
        case "stocke":
            // Redirection pour le service stocke
            break;
        case "accueil":
            // Redirection pour le service accueil
            break;
        default:
            // Gérer le cas où le rôle n'est pas reconnu
            break;
    }
}
    public void Verification(){
        //Permet de verifier les informations
        
        
    }
    
    
    
    
}
