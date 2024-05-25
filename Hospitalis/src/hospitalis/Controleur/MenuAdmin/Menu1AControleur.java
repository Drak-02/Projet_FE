/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hospitalis.Controleur.MenuAdmin;

import hospitalis.Interface.componentAD.Menu1A;
import hospitalis.Model.Utilisateurs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class Menu1AControleur implements MouseListener ,ListSelectionListener {
    private Connection connection;
    private Menu1A menu1A;
    private Utilisateurs users;
    private static Menu1AControleur instanceM1;
    
    public Menu1AControleur(Connection connection, Menu1A menu1A) {
        this.connection = connection;
        this.menu1A = menu1A;
        System.out.println("Appe creation menuCont1");
        this.menu1A.btajouter.addMouseListener(this);
        this.menu1A.btsupprimer.addMouseListener(this);
        this.menu1A.btmodifier.addMouseListener(this);
        this.menu1A.jtables.getSelectionModel().addListSelectionListener(this);
        chargementDeUsers();
    }
    
    //Contrôle la creation des instances lors de l'arriver pour ne pas créer a chaque fois une autre instance ( surcharge).
    public static Menu1AControleur getInstance(Connection connection, Menu1A menu1A) {
        if (instanceM1 == null) {
            synchronized (Menu1AControleur.class) {
                if (instanceM1 == null) {
                    instanceM1 = new Menu1AControleur(connection,menu1A);
                    System.out.println("Appel a l'instance de comtr men");
                }
            }
        }
        return instanceM1;
    }


    public void setMenu(Menu1A menu1A) {
        this.menu1A = menu1A;
        if (this.menu1A == null) {
            System.out.println("Le menu est null");
        } else {
            System.out.println("Le menu n'est pas null");
            this.menu1A.btajouter.addMouseListener(this);
            this.menu1A.btsupprimer.addMouseListener(this);
            this.menu1A.btmodifier.addMouseListener(this);
        }
    }
   
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == menu1A.btajouter) {
            handleAddUser();
        } else if (e.getSource() == menu1A.btsupprimer) {
            handleDeleteUser();
        } else if (e.getSource() == menu1A.btmodifier) {
            try {
                handleModifyUser();
            } catch (ParseException ex) {
                Logger.getLogger(Menu1AControleur.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    //*******************************

    /**
     *
     * @param e
     * @throws ParseException
     */

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            int selectedRow = menu1A.jtables.getSelectedRow();
            if (selectedRow >= 0) {
                TableModel model = menu1A.jtables.getModel();
                menu1A.inputMatric.setText(model.getValueAt(selectedRow, 0).toString());
                menu1A.inputNom.setText(model.getValueAt(selectedRow, 1).toString());
                menu1A.inputPrenom.setText(model.getValueAt(selectedRow, 2).toString());

                try {
                    Date dateNaissance = new SimpleDateFormat("yyyy-MM-dd").parse(model.getValueAt(selectedRow, 3).toString());
                    menu1A.inputNaiss.setDate(dateNaissance);
                } catch (ParseException ex) {
                    Logger.getLogger(Menu1AControleur.class.getName()).log(Level.SEVERE, null, ex);
                }

                menu1A.inputPass.setText(model.getValueAt(selectedRow, 4).toString());
                menu1A.inputTel.setText(model.getValueAt(selectedRow, 5).toString());
                menu1A.inputSpe.setText(model.getValueAt(selectedRow, 6).toString());
                menu1A.inputMai.setText(model.getValueAt(selectedRow, 7).toString());
                menu1A.inputRole.setSelectedItem(model.getValueAt(selectedRow, 8).toString());

                String sexe = model.getValueAt(selectedRow, 9).toString();
                if ("Homme".equals(sexe)) {
                    menu1A.boutonHomme.setSelected(true);
                } else {
                    menu1A.boutonFemme.setSelected(true);
                }
            }
        }
    }

    //-----------------455----

    private void handleAddUser() {
    try {
        
        
        if (menu1A.inputMai.getText().isEmpty() || menu1A.inputNom.getText().isEmpty() || menu1A.inputPrenom.getText().isEmpty() || menu1A.inputPass.getText().isEmpty() ) {
                JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs requis.", "Erreur", JOptionPane.ERROR_MESSAGE);
                return; // Sortir de la méthode si les champs sont vides
        }
        String dateNaissanceStr = formatDate(menu1A.inputNaiss.getDate());
        users = new Utilisateurs(connection);
        users.setMatricule(menu1A.inputMatric.getText());
        users.setNom(menu1A.inputNom.getText());
        users.setPrenom(menu1A.inputPrenom.getText());
        users.setDateNaissance(dateNaissanceStr);
        users.setPassword(new String(menu1A.inputPass.getPassword()));
        users.setTelephone(Long.parseLong(menu1A.inputTel.getText())); // Utilisez long ici
        users.setSpecialite(menu1A.inputSpe.getText());
        users.setEmail(menu1A.inputMai.getText());
        users.setRole((String) menu1A.inputRole.getSelectedItem());
        users.setSexe(menu1A.boutonHomme.isSelected() ? "Homme" : "Femme");

        boolean success = users.ajouterCompte(connection);
        if (success) {
            JOptionPane.showMessageDialog(null, "Utilisateur ajouté avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);
            EffacerChamps();
            updateTable(); // Mise à jour de la table après ajout
        } else {
            JOptionPane.showMessageDialog(null, "Erreur lors de l'ajout de l'utilisateur.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erreur: " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private String formatDate(Date date) throws ParseException {
        if (date == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

    private void EffacerChamps() {
        menu1A.inputMatric.setText("");
        menu1A.inputNom.setText("");
        menu1A.inputPrenom.setText("");
        menu1A.inputPass.setText("");
        menu1A.inputTel.setText("");
        menu1A.inputSpe.setText("");
        menu1A.inputMai.setText("");
        menu1A.inputNaiss.setDate(null);
        //menu1A.buttonGroup1.clearSelection();
        menu1A.inputRole.setSelectedIndex(0);
    }
    public void afficherMenu() {
        if (menu1A.isVisible()) {
            menu1A.setVisible(false);
        } else {
            menu1A.setVisible(true);
        }
    }
    private void handleDeleteUser() {
        int selectedRow = menu1A.jtables.getSelectedRow();
        if (selectedRow >= 0) {
            String matricule = (String) menu1A.jtables.getValueAt(selectedRow, 0);
            int response = JOptionPane.showConfirmDialog(null, 
                "Êtes-vous sûr de vouloir supprimer l'utilisateur avec le matricule : " + matricule + " ?", 
                "Confirmation de suppression", 
                JOptionPane.YES_NO_OPTION, 
                JOptionPane.WARNING_MESSAGE);

            if (response == JOptionPane.YES_OPTION) {
                users = new Utilisateurs(connection);
                users.setMatricule(matricule);

                boolean success = users.supprimerCompte(connection);
                if (success) {
                    JOptionPane.showMessageDialog(null, "Utilisateur supprimé avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);
                    EffacerChamps();
                    updateTable(); // Mise à jour de la table après suppression
                } else {
                    JOptionPane.showMessageDialog(null, "Erreur lors de la suppression de l'utilisateur.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            } // Si la réponse est NO, ne faites rien
        } else {
            JOptionPane.showMessageDialog(null, "Veuillez sélectionner un utilisateur à supprimer.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleModifyUser() throws ParseException {
        int selectedRow = menu1A.jtables.getSelectedRow();
        if (selectedRow >= 0) {
            String matricule = (String) menu1A.jtables.getValueAt(selectedRow, 0);
            String dateNaissanceStr = formatDate(menu1A.inputNaiss.getDate()); 
            
            users = new Utilisateurs(connection);
            users.setMatricule(matricule);
            users.setNom(menu1A.inputNom.getText());
            users.setPrenom(menu1A.inputPrenom.getText());
            users.setDateNaissance(dateNaissanceStr);
            users.setPassword(new String(menu1A.inputPass.getPassword()));
            users.setSpecialite(menu1A.inputSpe.getText());
            users.setEmail(menu1A.inputMai.getText());
            users.setRole((String) menu1A.inputRole.getSelectedItem());
            users.setSexe(menu1A.boutonHomme.isSelected() ? "Homme" : "Femme");

            try {
                // Vérifie si le champ de téléphone n'est pas vide avant de le convertir en Long
                String telephoneStr = menu1A.inputTel.getText();
                if (!telephoneStr.isEmpty()) {
                    long telephone = Long.parseLong(telephoneStr);
                    users.setTelephone(telephone);
                } else {
                    // Si le champ est vide, affecte 0 au téléphone ou une autre valeur par défaut selon votre logique
                    users.setTelephone(0); // ou une autre valeur par défaut
                }

                // Récupération des données actuelles du tableau

                String currentMatricule = (String) menu1A.jtables.getValueAt(selectedRow, 0);
                String currentNom = (String) menu1A.jtables.getValueAt(selectedRow, 1);
                String currentPrenom = (String) menu1A.jtables.getValueAt(selectedRow, 2);
                String currentDateNaissanceStr = (String) menu1A.jtables.getValueAt(selectedRow, 3);
                String currentPassword = (String) menu1A.jtables.getValueAt(selectedRow, 4);
                String currentSpecialite = (String) menu1A.jtables.getValueAt(selectedRow, 6);
                String currentEmail = (String) menu1A.jtables.getValueAt(selectedRow, 7);
                String currentRole = (String) menu1A.jtables.getValueAt(selectedRow, 8);
                String currentSexe = (String) menu1A.jtables.getValueAt(selectedRow, 9);

                // Comparaison avec les valeurs actuelles et mise à jour si nécessaire
                if (!matricule.equals(currentMatricule)) {
                    users.setMatricule(matricule);
                }
                if (!menu1A.inputNom.getText().equals(currentNom)) {
                    users.setNom(menu1A.inputNom.getText());
                }
                if (!menu1A.inputPrenom.getText().equals(currentPrenom)) {
                    users.setPrenom(menu1A.inputPrenom.getText());
                }
                if (dateNaissanceStr != null && !dateNaissanceStr.equals(currentDateNaissanceStr)) {
                    users.setDateNaissance(dateNaissanceStr);
                }
                if (!new String(menu1A.inputPass.getPassword()).equals(currentPassword)) {
                    users.setPassword(new String(menu1A.inputPass.getPassword()));
                }
                // Répétez le processus pour les autres champs

                boolean success = users.modifierCompte(connection);
                if (success) {
                    JOptionPane.showMessageDialog(null, "Utilisateur modifié avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);
                    EffacerChamps();
                    updateTable(); // Mise à jour de la table après modification
                } else {
                    JOptionPane.showMessageDialog(null, "Erreur lors de la modification de l'utilisateur.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Erreur: Le numéro de téléphone doit être un nombre.", "Erreur", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erreur lors de la modification de l'utilisateur : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Veuillez sélectionner un utilisateur à modifier.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
        
    //Cette methodes mes permet de chercher les données et afficher
    private void chargementDeUsers() {
        List<Utilisateurs> userList = Utilisateurs.getAllUsers(connection);
        DefaultTableModel model = new DefaultTableModel(
            new Object[]{"Matricule", "Nom", "Prenom", "Date de Naissance", "Mot de Passe", "Telephone", "Specialite", "Email", "Role", "Sexe"}, 
            0
        );
        for (Utilisateurs user : userList) {
            model.addRow(new Object[]{
                user.getMatricule(),
                user.getNom(),
                user.getPrenom(),
                user.getDateNaissance(),
                user.getPassword(),
                user.getTelephone(),
                user.getSpecialite(),
                user.getEmail(),
                user.getRole(),
                user.getSexe()
            });
        }
        menu1A.jtables.setModel(model);
    }

    private void updateTable() {
        chargementDeUsers();
    }

    //----------------------------------
    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

}
