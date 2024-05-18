package hospitalis.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Utilisateurs {
    
    // Attributs
    private String nom;
    private String prenom;
    private String role;
    private String password;
    private String dateNaissance;
    private int contact;
    private String matricule;
    private Connection connection; // Ajout de l'attribut de connexion
    private String idUtilisateur;

    public void setIdUtilisateur(String idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public void setContact(int contact) {
        this.contact = contact;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public String getIdUtilisateur() {
        return idUtilisateur;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getRole() {
        return role;
    }

    public String getPassword() {
        return password;
    }

    public String getDateNaissance() {
        return dateNaissance;
    }

    public int getContact() {
        return contact;
    }

    public String getMatricule() {
        return matricule;
    }

    public Connection getConnection() {
        return connection;
    }
    

    // Constructeur
    public Utilisateurs(Connection connection) {
        this.connection = connection;
        System.out.println("Appel a utilisateur");
    }
    
    // Méthode d'authentification
    public boolean seConnecter() {
        boolean isAthentification = false;
        String authSql = "SELECT role ,matricule, password FROM users WHERE matricule=? AND password=?";

        try (PreparedStatement prepare = connection.prepareStatement(authSql)) {
            prepare.setString(1, this.matricule);
            prepare.setString(2, this.password);

            try (ResultSet resultSet = prepare.executeQuery()) {
                if (resultSet.next()) {
                    isAthentification = true;
                    this.role = resultSet.getString("role");
                } else {
                    isAthentification = false;
                    System.out.println("Authentification échouée");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isAthentification;
    }
    
    //****************************************************** */
    /*
    Lors de la creation de l'instance utilisateur il faut fournir la conexion existants a la base de données
    ControleurBDD controleurBDD = new ControleurBDD(url, username, passworddb);
    Connection connection = controleurBDD.getConnection();
        ou bien par 
    Utilisateurs utilisateur = new Utilisateurs(connection);

    */
    
    // Autres méthodes de manipulation des utilisateurs
    
}
