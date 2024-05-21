package hospitalis.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class Utilisateurs {
    
    // Attributs
    private String nom;
    private String prenom;
    private String role;
    private String password;
    private String dateNaissance;
    private String matricule;
    private Connection connection; // Ajout de l'attribut de connexion
    private String idUtilisateur;
    private long telephone;
    private String email;
    private String sexe;
    private String specialite;

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setTelephone(long telephone) {
        this.telephone = telephone;
    }

    public void setEmail(String mail) {
        this.email = mail;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public long getTelephone() {
        return telephone;
    }

    public String getEmail() {
        return email;
    }

    public String getSexe() {
        return sexe;
    }
    

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
        String authSql = "SELECT role ,matricule, password FROM utilisateurs WHERE matricule=? AND password=?";

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
    public boolean ajouterCompte(Connection connection) {
        System.out.println("Appel a jouter utilisateur");
        boolean matriExiste = false;
        String verifiSql = "SELECT COUNT(*) FROM utilisateurs WHERE matricule = ?";

        // Vérifier si le matricule existe déjà
        try (PreparedStatement verifi = connection.prepareStatement(verifiSql)) {
            verifi.setString(1, matricule);
            try (ResultSet resultSet = verifi.executeQuery()) {
                if (resultSet.next()) {
                    matriExiste = resultSet.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (matriExiste) {
            
            System.out.println("Le matricule existe déjà dans la base de données.");
            return false;
        } else {
            // Insérer le nouvel utilisateur si le matricule n'existe pas
            String insertQuery = "INSERT INTO utilisateurs (matricule, nom, prenom, dateNaissance, password, telephone, specialite, email, role, sexe) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                preparedStatement.setString(1, matricule);
                preparedStatement.setString(2, nom);
                preparedStatement.setString(3, prenom);
                preparedStatement.setString(4, dateNaissance);
                preparedStatement.setString(5, password);
                preparedStatement.setLong(6, telephone);
                preparedStatement.setString(7, specialite);
                preparedStatement.setString(8, email);
                preparedStatement.setString(9, role);
                preparedStatement.setString(10, sexe);

                preparedStatement.executeUpdate();
                getAllUsers(connection);

                System.out.println("Utilisateur ajouté avec succès");
            } catch (SQLException e) {
                e.printStackTrace();
                }
            }
        return true;
    }
    //Récuperation des données de la base de
    public static List<Utilisateurs> getAllUsers(Connection connection) {
        List<Utilisateurs> userList = new ArrayList<>();
        String query = "SELECT matricule, nom, prenom, dateNaissance,password, telephone, email, specialite, role, sexe FROM utilisateurs";

        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Utilisateurs user = new Utilisateurs(connection);
                user.setMatricule(resultSet.getString("matricule"));
                user.setNom(resultSet.getString("nom"));
                user.setPrenom(resultSet.getString("prenom"));
                user.setDateNaissance(resultSet.getString("dateNaissance"));
                user.setPassword(resultSet.getString("password"));
                user.setTelephone(resultSet.getLong("telephone"));
                user.setEmail(resultSet.getString("email"));
                user.setSpecialite(resultSet.getString("specialite"));
                user.setRole(resultSet.getString("role"));
                user.setSexe(resultSet.getString("sexe"));
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userList;
    }
    public boolean modifierCompte(Connection connection) {
        try {
            String query = "UPDATE utilisateurs SET nom = ?, prenom = ?, dateNaissance = ?, password = ?, telephone = ?, specialite = ?, email = ?, role = ?, sexe = ? WHERE matricule = ?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, this.nom);
            pstmt.setString(2, this.prenom);
            pstmt.setString(3, this.dateNaissance);
            pstmt.setString(4, this.password);
            pstmt.setLong(5, this.telephone);
            pstmt.setString(6, this.specialite);
            pstmt.setString(7, this.email);
            pstmt.setString(8, this.role);
            pstmt.setString(9, this.sexe);
            pstmt.setString(10, this.matricule);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean supprimerCompte(Connection connection) {
        try {
            String query = "DELETE FROM utilisateurs WHERE matricule = ?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, matricule);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
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
    
