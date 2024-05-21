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
import javax.swing.JOptionPane;

public class Menu1AControleur implements MouseListener {
    private Connection connection;
    private Menu1A menu1A;
    private Utilisateurs users;
    private static Menu1AControleur instanceM1;
    
    public Menu1AControleur(Connection connection, Menu1A menu1A) {
        this.connection = connection;
        this.menu1A = menu1A;
        System.out.println("Appel creation menuCont1");
        this.menu1A.btajouter.addMouseListener(this);
        this.menu1A.btsupprimer.addMouseListener(this);
        this.menu1A.btmodifier.addMouseListener(this);

    }

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
            handleModifyUser();
        }
    }

    private void handleAddUser() {
        try {
            String dateNaissanceStr = formatDate(menu1A.inputNaiss.getDate());
            users = new Utilisateurs(connection);
            users.setMatricule(menu1A.inputMatric.getText());
            users.setNom(menu1A.inputNom.getText());
            users.setPrenom(menu1A.inputPrenom.getText());
            users.setDateNaissance(dateNaissanceStr);
            users.setPassword(new String(menu1A.inputPass.getPassword()));
            users.setTelephone(Integer.parseInt(menu1A.inputTel.getText()));
            users.setSpecialite(menu1A.inputSpe.getText());
            users.setEmail(menu1A.inputMai.getText());
            users.setRole((String) menu1A.inputRole.getSelectedItem());
            users.setSexe(menu1A.boutonHomme.isSelected() ? "Homme" : "Femme");

            boolean success = users.ajouterCompte(connection);
            if (success) {
                JOptionPane.showMessageDialog(null, "Utilisateur ajouté avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);
                EffacerChamps();
            } else {
                JOptionPane.showMessageDialog(null, "Erreur lors de l'ajout de l'utilisateur.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erreur: " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private String formatDate(Date date) throws ParseException {
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
        // Logic to handle user deletion
    }

    private void handleModifyUser() {
        // Logic to handle user modification
    }
    
    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}
