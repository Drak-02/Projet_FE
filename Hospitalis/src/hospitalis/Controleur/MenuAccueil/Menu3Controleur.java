package hospitalis.Controleur.MenuAccueil;

import hospitalis.Interface.componentAc.Menu3;
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
public class Menu3Controleur implements MouseListener {
    private Connection connection;
    private Menu3 menu3;
    private Chambre chambre;
    private static Menu3Controleur instanceM3;
    
    public Menu3Controleur(Connection connection, Menu3 menu3) {
        this.connection = connection;
        this.menu3 = menu3;
        //System.out.println("Appel création menuCont1");
        this.menu3.btajout.addMouseListener(this);
        this.menu3.btsupp.addMouseListener(this);
        this.menu3.btmodi.addMouseListener(this);
        fetchDataToTable();
    }

    public static Menu3Controleur getInstance(Connection connection, Menu3 menu3) {
        if (instanceM3 == null) {
            synchronized (Menu3Controleur.class) {
                if (instanceM3 == null) {
                    instanceM3 = new Menu3Controleur(connection,menu3);
                    System.out.println("Appel à l'instance du contrôleur de menu");
                }
            }
        }
        return instanceM3;
    }

    public void setMenu(Menu3 menu3) {
        this.menu3 = menu3;
        if (this.menu3 == null) {
            System.out.println("Le menu est null");
        } else {
            System.out.println("Le menu n'est pas null");
            this.menu3.btajout.addMouseListener(this);
            this.menu3.btsupp.addMouseListener(this);
            this.menu3.btmodi.addMouseListener(this);
        }
    }
    
    public void afficherMenu() {
        if (menu3.isVisible()) {
            menu3.setVisible(false);
        } else {
            menu3.setVisible(true);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == menu3.btajout) {
            handleAddChambre();
        } else if (e.getSource() == menu3.btsupp) {
            handleDeleteChambre();
        } else if (e.getSource() == menu3.btmodi) {
            handleModifyChambre();
        }
    }

    public void handleAddChambre() {
         if ( menu3.inputNumero.getText().isEmpty() ||  menu3.inputdispochambre.getText().isEmpty() || menu3.inputtypechambre.getText().isEmpty()) {
    JOptionPane.showMessageDialog(menu3, "Veuillez remplir tous les champs", "Champs vides", JOptionPane.ERROR_MESSAGE);
}
         else{
            // Récupérer les données de la nouvelle chambre depuis l'interface utilisateur
            String numero = menu3.inputNumero.getText(); // Supposons que le champ s'appelle inputNumero dans l'interface
            String disponibilite = menu3.inputdispochambre.getText(); // Supposons que le champ s'appelle inputDisponibilite dans l'interface
            String type = menu3.inputtypechambre.getText(); // Supposons que le champ s'appelle inputType dans l'interface      
            // Insérer la nouvelle chambre dans la base de données
            String sql = "INSERT INTO chambre (numero, disponibilite, type) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, numero);
                preparedStatement.setString(2, disponibilite);
                preparedStatement.setString(3, type);

                int rowsInserted = preparedStatement.executeUpdate();
                if (rowsInserted > 0) {
                    JOptionPane.showMessageDialog(menu3, "Chambre ajoutée avec succès!");
                    fetchDataToTable(); // Mettre à jour le tableau après l'ajout
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(menu3, "Erreur lors de l'ajout de la chambre.");
            }
        }
    }
    
    public void handleDeleteChambre() {
        // Récupérer le numéro de la chambre sélectionnée
        int selectedRow = menu3.tablechambre.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(menu3, "Veuillez sélectionner une chambre à supprimer.");
            return;
        }
         Object[] options = {"Oui", "Non"};
        int response = JOptionPane.showOptionDialog(
            menu3,
            "Voulez-vous vraiment supprimer cette chambre ?",
            "Confirmation de suppression",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            options[0]
         );
        if(response == JOptionPane.YES_OPTION){
        String numeroChambre = menu3.tablechambre.getValueAt(selectedRow, 0).toString(); // Supposons que la première colonne contient le numéro de la chambre
        
        // Supprimer la chambre de la base de données
        String sql = "DELETE FROM chambre WHERE numero = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, numeroChambre);

            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted > 0) {
                JOptionPane.showMessageDialog(menu3, "Chambre supprimée avec succès!");
                fetchDataToTable(); // Mettre à jour le tableau après la suppression
            } else {
                JOptionPane.showMessageDialog(menu3, "Aucune chambre trouvée avec ce numéro.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(menu3, "Erreur lors de la suppression de la chambre.");
        }
    }
    }
    public void handleModifyChambre() {
        // Récupérer les nouvelles valeurs de disponibilité et de type depuis l'interface utilisateur
        String nouvelleDisponibilite = menu3.inputdispochambre.getText(); // Supposons que le champ s'appelle inputDisponibilite dans l'interface
        String nouveauType = menu3.inputtypechambre.getText(); // Supposons que le champ s'appelle inputType dans l'interface
        
        // Récupérer le numéro de la chambre sélectionnée
        int selectedRow = menu3.tablechambre.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(menu3, "Veuillez sélectionner une chambre à modifier.");
            return;
        }
        String numeroChambre = menu3.tablechambre.getValueAt(selectedRow, 0).toString(); // Supposons que la première colonne contient le numéro de la chambre
        
        // Mettre à jour la chambre dans la base de données
        String sql = "UPDATE chambre SET disponibilite = ?, type = ? WHERE numero = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, nouvelleDisponibilite);
            preparedStatement.setString(2, nouveauType);
            preparedStatement.setString(3, numeroChambre);

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(menu3, "Chambre modifiée avec succès!");
                fetchDataToTable(); // Mettre à jour le tableau après la modification
            } else {
                JOptionPane.showMessageDialog(menu3, "Aucune chambre trouvée avec ce numéro.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(menu3, "Erreur lors de la modification de la chambre.");
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) { }

    public void fetchDataToTable() {
        DefaultTableModel model = (DefaultTableModel) menu3.tablechambre.getModel();
        model.setRowCount(0); // Effacer le tableau avant de le remplir à nouveau
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM chambre");
            while (resultSet.next()) {
                String numero = resultSet.getString("numero");
                String disponibilite = resultSet.getString("disponibilite");
                String type = resultSet.getString("type");
                
                model.addRow(new Object[]{numero, disponibilite, type});
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(menu3, "Erreur lors de la récupération des données.");
        }
    }
}
