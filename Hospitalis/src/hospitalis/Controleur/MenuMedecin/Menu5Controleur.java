/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hospitalis.Controleur.MenuMedecin;

import hospitalis.Interface.componentMe.Menu5;
import hospitalis.Model.Chambre;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author pc
 */
public class Menu5Controleur implements MouseListener {
    private static Menu5Controleur instanceM5;
    private Connection connection;
    private Menu5 menu5;
    private Chambre chambre;
    
    
    
    public Menu5Controleur(Connection connection , Menu5 menu5){
        this.connection = connection ;
        this.menu5 = menu5;
        this.menu5.btajout.addMouseListener(this);
        this.menu5.btsupp.addMouseListener(this);
        fetchDataToTable();
    }
    public static Menu5Controleur getInstance(Connection connection,Menu5 menu5){
        if (instanceM5 == null) {
            synchronized (Menu1Controleur.class) {
                if (instanceM5 == null) {
                    instanceM5 = new Menu5Controleur(connection,menu5);
                    //System.out.println("Appel a l'instance de comtr men");
                }
            }
        }
        return instanceM5;
        
    }
    public void afficherMenu(){
        if (menu5.isVisible()) {
            menu5.setVisible(false);
        } else {
            menu5.setVisible(true);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
          if (e.getSource() == menu5.btajout) {
            handleAddChambre();
        } else if (e.getSource() == menu5.btsupp) {
            handleDeleteChambre();
        } else if (e.getSource() == menu5.btmodi) {
            handleModifyChambre();
        }
    
    }
     public void handleAddChambre() {
        if ( menu5.inputNumero.getText().isEmpty() || menu5.inputdispochambre.getText().isEmpty()  || menu5.inputtypechambre.getText().isEmpty() ) {
    JOptionPane.showMessageDialog(menu5, "Veuillez remplir tous les champs", "Champs vides", JOptionPane.ERROR_MESSAGE);
}
         else{
                // Récupérer les données de la nouvelle chambre depuis l'interface utilisateur
                String numero = menu5.inputNumero.getText(); // Supposons que le champ s'appelle inputNumero dans l'interface
                String disponibilite = menu5.inputdispochambre.getText(); // Supposons que le champ s'appelle inputDisponibilite dans l'interface
                String type = menu5.inputtypechambre.getText(); // Supposons que le champ s'appelle inputType dans l'interface

                // Insérer la nouvelle chambre dans la base de données
                String sql = "INSERT INTO chambre (numero, type, disponibilite) VALUES (?, ?, ?)";
                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    preparedStatement.setString(1, numero);
                    preparedStatement.setString(2, type);
                    preparedStatement.setString(3, disponibilite);

                    int rowsInserted = preparedStatement.executeUpdate();
                    if (rowsInserted > 0) {
                        JOptionPane.showMessageDialog(menu5, "Chambre ajoutée avec succès!");
                        fetchDataToTable(); // Mettre à jour le tableau après l'ajout
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(menu5, "Erreur lors de l'ajout de la chambre.");
                }
          }
    }
    
    public void handleDeleteChambre() {
        // Récupérer le numéro de la chambre sélectionnée
        int selectedRow = menu5.tablechambre.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(menu5, "Veuillez sélectionner une chambre à supprimer.");
            return;
        }
        
        Object[] options = {"Oui", "Non"};
        int response = JOptionPane.showOptionDialog(
            menu5,
            "Voulez-vous vraiment supprimer cette chambre ?",
            "Confirmation de suppression",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            options[0]
         );
        String numeroChambre = menu5.tablechambre.getValueAt(selectedRow, 0).toString(); // Supposons que la première colonne contient le numéro de la chambre
        if(response == JOptionPane.YES_OPTION){
        // Supprimer la chambre de la base de données
        String sql = "DELETE FROM chambre WHERE numero = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, numeroChambre);

            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted > 0) {
                JOptionPane.showMessageDialog(menu5, "Chambre supprimée avec succès!");
                fetchDataToTable(); // Mettre à jour le tableau après la suppression
            } else {
                JOptionPane.showMessageDialog(menu5, "Aucune chambre trouvée avec ce numéro.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(menu5, "Erreur lors de la suppression de la chambre.");
        }
    }
    }
    public void handleModifyChambre() {
        // Récupérer les nouvelles valeurs de disponibilité et de type depuis l'interface utilisateur
        String nouvelleDisponibilite = menu5.inputdispochambre.getText(); // Supposons que le champ s'appelle inputDisponibilite dans l'interface
        String nouveauType = menu5.inputtypechambre.getText(); // Supposons que le champ s'appelle inputType dans l'interface
        
        // Récupérer le numéro de la chambre sélectionnée
        int selectedRow = menu5.tablechambre.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(menu5, "Veuillez sélectionner une chambre à modifier.");
            return;
        }
        String numeroChambre = menu5.tablechambre.getValueAt(selectedRow, 0).toString(); // Supposons que la première colonne contient le numéro de la chambre
        
        // Mettre à jour la chambre dans la base de données
        String sql = "UPDATE chambre SET type = ?, disponibite = ? WHERE numero = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, nouveauType);
            preparedStatement.setString(2, nouvelleDisponibilite);
            preparedStatement.setString(3, numeroChambre);

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(menu5, "Chambre modifiée avec succès!");
                fetchDataToTable(); // Mettre à jour le tableau après la modification
            } else {
                JOptionPane.showMessageDialog(menu5, "Aucune chambre trouvée avec ce numéro.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(menu5, "Erreur lors de la modification de la chambre.");
        }
    }
    public void fetchDataToTable() {
        DefaultTableModel model = (DefaultTableModel) menu5.tablechambre.getModel();
        model.setRowCount(0); // Effacer le tableau avant de le remplir à nouveau
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM chambre");
            while (resultSet.next()) {
                String numero = resultSet.getString("numero");
                String type = resultSet.getString("type");
                String disponibilite = resultSet.getString("disponibilite");
                model.addRow(new Object[]{numero, disponibilite, type});
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(menu5, "Erreur lors de la récupération des données.");
        }
    }


    @Override
    public void mousePressed(MouseEvent e) {
 }

    @Override
    public void mouseReleased(MouseEvent e) {
  }

    @Override
    public void mouseEntered(MouseEvent e) {
  }

    @Override
    public void mouseExited(MouseEvent e) {
  }
    
}
