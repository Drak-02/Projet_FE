package hospitalis.Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import hospitalis.Controleur.ControleurBDD;

public abstract class Utilisateurs {
    // Attributs
    protected String idUtilisateur;
    protected String nom;
    protected String prenom;
    protected String role;
    protected String password;
    protected String dateNaissance;
    protected int contact;
    protected String matricule; 


        String url = "jdbc:mysql://localhost:3306/mabase";
        String username = "root";
        String passworddb = "";
    

    //Constructor
    public Utilisateurs(){
        
    }
    public Utilisateurs(String nom, String password){
        this.nom  = nom;
        this.password = password;
    }

    
    //Method*
    //S'authentifier
    public boolean SeConnecter(String nom, String password){
        boolean isAthentification = false;
        @SuppressWarnings("unused")
        String authSql = "SELECT role ,matricule, password FROM users WHERE matricule=? AND password=?";
        ControleurBDD connection = new ControleurBDD(url, username, passworddb);

        try  {
            PreparedStatement prepare = connection.getConnection().prepareStatement(authSql);

            prepare.setString(1,this.matricule);
            prepare.setString(2, this.password);

            try (ResultSet resultSet = prepare.executeQuery()) {
                if(resultSet.next()){
                    isAthentification = true;
                    this.role = resultSet.getString("role"); //Permet de récuperer le rôle et de le stocke sur role

                }else{
                    isAthentification = false;
                    System.out.println("Authentification échouée");
                }
                
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();

            }
            prepare.clearParameters();
        } catch (Exception e) {
            // TODO: handle exception
        }
        if(isAthentification)
            return true;//SI l'authentification est réussi return true pour aller chercher le roler de l'utilisateur
        return false;
    }
    //****************************************************** */
    // Ajouter utilisateur par Administrateur

}
