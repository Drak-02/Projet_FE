package hospitalis.Controleur.MenuAccueil;

import hospitalis.Interface.componentAc.Menu1;
import hospitalis.Model.RDV;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author pc
 */
public class Menu1Controleur implements MouseListener {
    private Connection connection;
    private Menu1 menu1;
    private RDV rdv;
    private static Menu1Controleur instanceM1;

    public Menu1Controleur(Connection connection, Menu1 menu1) {
        this.connection = connection;
        this.menu1 = menu1;
        this.menu1.btmodi.addMouseListener(this);
        this.menu1.btajout.addMouseListener(this);
        this.menu1.btsupp.addMouseListener(this);
        fetchDataToTable();
    }

    public static Menu1Controleur getInstance(Connection connection, Menu1 menu1) {
        if (instanceM1 == null) {
            synchronized (Menu1Controleur.class) {
                if (instanceM1 == null) {
                    instanceM1 = new Menu1Controleur(connection, menu1);
                }
            }
        }
        return instanceM1;
    }

    public void setMenu(Menu1 menu1) {
        this.menu1 = menu1;
        if (this.menu1 == null) {
            System.out.println("Le menu est null");
        } else {
            System.out.println("Le menu n'est pas null");
            this.menu1.btajout.addMouseListener(this);
            this.menu1.btsupp.addMouseListener(this);
            this.menu1.btmodi.addMouseListener(this);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == menu1.btajout) {
            handleAddRDV();
        } else if (e.getSource() == menu1.btsupp) {
            handleDeleteRDV();
        } else if (e.getSource() == menu1.btmodi) {
            handleModifyRDV();
        }
    }

    private void handleAddRDV() {
        try {
            String dateRDV = formatDate(menu1.inputdaterdv.getDate());
            String matriculeMed = menu1.inputmatrimed.getText();
            String heure = menu1.inputheure.getText();
            String detailsRDV = menu1.inputdetrdv.getText();
            String typeRDV = (String) menu1.inputtyperdv.getSelectedItem();
            String statut = menu1.inputstatut.getText();

            String sql = "INSERT INTO rendez_vous (date_rdv, heure, details_rdv, matricule_med, type_rdv, statut) VALUES (?, ?, ?, ?, ?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, dateRDV);
                preparedStatement.setString(2, heure);
                preparedStatement.setString(3, detailsRDV);
                preparedStatement.setString(4, matriculeMed);
                preparedStatement.setString(5, typeRDV);
                preparedStatement.setString(6, statut);

                int rowsInserted = preparedStatement.executeUpdate();
                if (rowsInserted > 0) {
                    JOptionPane.showMessageDialog(menu1, "Rendez-vous ajouté avec succès!");
                    fetchDataToTable();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(menu1, "Erreur lors de l'ajout du rendez-vous.");
            }
        } catch (ParseException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(menu1, "Erreur de format de date.");
        }
    }

    public void handleModifyRDV() {
        int selectedRow = menu1.getTable().getSelectedRow();
        if (selectedRow >= 0) {
            try {
                int id = Integer.parseInt(menu1.getTable().getValueAt(selectedRow, 0).toString());
                String dateRDV = formatDate(menu1.inputdaterdv.getDate());
                String matriculeMed = menu1.inputmatrimed.getText();
                String heure = menu1.inputheure.getText();
                String detailsRDV = menu1.inputdetrdv.getText();
                String typeRDV = (String) menu1.inputtyperdv.getSelectedItem();
                String statut = menu1.inputstatut.getText();

                String sql = "UPDATE rendez_vous SET date_rdv = ?, heure = ?, details_rdv = ?, matricule_med = ?, type_rdv = ?, statut = ? WHERE id = ?";

                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    preparedStatement.setString(1, dateRDV);
                    preparedStatement.setString(2, heure);
                    preparedStatement.setString(3, detailsRDV);
                    preparedStatement.setString(4, matriculeMed);
                    preparedStatement.setString(5, typeRDV);
                    preparedStatement.setString(6, statut);
                    preparedStatement.setInt(7, id);

                    int rowsUpdated = preparedStatement.executeUpdate();
                    if (rowsUpdated > 0) {
                        JOptionPane.showMessageDialog(menu1, "Rendez-vous modifié avec succès!");
                        fetchDataToTable();
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(menu1, "Erreur lors de la modification du rendez-vous.");
                }
            } catch (ParseException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(menu1, "Erreur de format de date.");
            }
        } else {
            JOptionPane.showMessageDialog(menu1, "Veuillez sélectionner un rendez-vous à modifier.");
        }
    }

    public void handleDeleteRDV() {
        int selectedRow = menu1.getTable().getSelectedRow();
        if (selectedRow >= 0) {
            int id = Integer.parseInt(menu1.getTable().getValueAt(selectedRow, 0).toString());
            String sql = "DELETE FROM rendez_vous WHERE id = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, id);

                int rowsDeleted = preparedStatement.executeUpdate();
                if (rowsDeleted > 0) {
                    JOptionPane.showMessageDialog(menu1, "Rendez-vous supprimé avec succès!");
                    fetchDataToTable();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(menu1, "Erreur lors de la suppression du rendez-vous.");
            }
        } else {
            JOptionPane.showMessageDialog(menu1, "Veuillez sélectionner un rendez-vous à supprimer.");
        }
    }

    private String formatDate(java.util.Date date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

    public void afficherMenu() {
        if (menu1.isVisible()) {
            menu1.setVisible(false);
        } else {
            menu1.setVisible(true);
        }
    }

    public void fetchDataToTable() {
        String sql = "SELECT * FROM rendez_vous";
        try (Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {
            
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(menu1, "Erreur lors de la récupération des données.");
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
