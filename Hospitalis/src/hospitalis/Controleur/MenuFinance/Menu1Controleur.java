/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hospitalis.Controleur.MenuFinance;

import hospitalis.Interface.componentFi.Menu1;
import hospitalis.Model.Facture;
import hospitalis.Model.ImprimerFacture;
import hospitalis.Model.Patient;
import hospitalis.Model.Traitement;
import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author pc
 */
public class Menu1Controleur implements MouseListener , ItemListener {
    private Connection connection;
    private final Menu1 menu1;
    private static Menu1Controleur instanceM1;
    private Facture facture;
    
    public Menu1Controleur(Connection connection, Menu1 menu1) {
        this.connection = connection;
        this.menu1 = menu1;
        
        menu1.liste_Traitement.setModel(new DefaultListModel<>());
        menu1.listeEffectuer1.setModel(new DefaultListModel<>());

        
        this.menu1.btajout.addMouseListener(this);
        this.menu1.type.addItemListener(this);
        this.menu1.listeEffectuer1.addMouseListener(this);
        this.menu1.liste_Traitement.addMouseListener(this);
        this.facture = new Facture(connection);
    }

      //------------------------------------------------------------------------------------------------------------------------------------------
  
    public static Menu1Controleur getInstance(Connection connection,Menu1 menu1){
        if (instanceM1 == null) {
            synchronized (Menu1Controleur.class) {
                if (instanceM1 == null) {
                    instanceM1 = new Menu1Controleur(connection,menu1);
                    //System.out.println("Appel a l'instance de comtr men");
                }
            }
        }
        return instanceM1;
        
    }
    
    
    public void afficherMenu() {
       if (menu1.isVisible()) {
            menu1.setVisible(false);
        } else {
            menu1.setVisible(true);
        }
       updateTable();
    }
    //------------------------------------------------------------------------------------------------------------------------------------------

   private void EtablirFacture() {
        try {
            facture = new Facture(connection);

            String type = (String) menu1.type.getSelectedItem();
            if (!type.equals("Consultation") && !type.equals("Consultation Spécialisé")) {
                chargementDeTraitement(type);
            }

            DefaultListModel<String> modelEffectuer1 = (DefaultListModel<String>) menu1.listeEffectuer1.getModel();
            double montantTotal = 0;

            for (int i = 0; i < modelEffectuer1.size(); i++) {
                String traitementStr = modelEffectuer1.getElementAt(i);
                // Parsing the string to extract price
                double prix = Double.parseDouble(traitementStr.substring(traitementStr.lastIndexOf(":") + 1).trim());
                montantTotal += prix;
            }

            // Vérifier si les champs nécessaires sont vides
            if (menu1.matricule.getText().isEmpty() || menu1.namePatient.getText().isEmpty() || menu1.prenomPatient.getText().isEmpty() || menu1.identifiant.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs requis.", "Erreur", JOptionPane.ERROR_MESSAGE);
                return; // Sortir de la méthode si les champs sont vides
            }

            System.out.println("++++++++++");
            String detailsF = "Matricule Médecin Infos\n" + menu1.matricule.getText()+"\n"+
                    "Patient infos \nNom:" + menu1.namePatient.getText() + "\nPrénom:"+menu1.prenomPatient.getText()+"\nCNI:"+
                        menu1.identifiant.getText();

            String dateStr = formatDate(new Date());

            facture.setMontant(montantTotal);
            facture.setDetails(detailsF);
            facture.setDateFacture(dateStr);
            System.out.println("Date:" +dateStr);
            boolean success = facture.creeFacture();

            if (success) {
                JOptionPane.showMessageDialog(null, "Facture établie avec succès. Montant total: " + montantTotal + " DHS", "Succès", JOptionPane.INFORMATION_MESSAGE);
                EffacerChamps();
                //String detailsFacture = detailsFacture();
                //imprimerFacture(detailsFacture);
                updateTable(); // Mise à jour de la table après ajout
            } else {
                JOptionPane.showMessageDialog(null, "Erreur lors de l'établissement de la facture.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erreur: " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
   //-----------------------------------------
    public void imprimerFacture(String fact) {
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPrintable(new ImprimerFacture(fact));

        if (job.printDialog()) {
            try {
                job.print();
            } catch (PrinterException e) {
                JOptionPane.showMessageDialog(null, "Erreur lors de l'impression de la facture.", "Erreur d'impression", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
        //------------------------------------------------------------------------------------------------------------------------------------------
     private void EffacerChamps() {
        menu1.identifiant.setText("");
        menu1.namePatient.setText("");
        menu1.naissanceP.setDate(null);
        menu1.prenomPatient.setText("");
        //menu1A.buttonGroup1.clearSelection();
        menu1.type.setSelectedIndex(0);
        menu1.matricule.setText("");
    }
    private void updateTable() {
        chargementDeFacture();
        loadTraitementTypes();
    }
    //***************
    public Patient getPatient() {
        try {
            Patient patient = new Patient();
            
            String dateStr = formatDate(menu1.naissanceP.getDate());
            patient.setNom(menu1.namePatient.getText());
            patient.setPrenom(menu1.prenomPatient.getText());
            patient.setCni(menu1.identifiant.getText());
            patient.setDateNaissance(dateStr);
            
            return patient;
        } catch (ParseException ex) {
            Logger.getLogger(Menu1Controleur.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    } 
    
        // Exemple de méthode pour obtenir les détails de la facture
    private String detailsFacture() {
        StringBuilder sb = new StringBuilder();
        sb.append("Facture\n");
        sb.append("Date: ").append(new SimpleDateFormat("yyyy-MM-dd").format(new Date())).append("\n");
        sb.append("Médecin:");
        //sb.append(m)
        sb.append("Informations du Patient:\n");
        sb.append(getPatient().detailsFacturePatient()); // Assuming getPatient() returns a Patient object
        sb.append("Traitements:\n");

        ListModel<String> listModel = menu1.listeEffectuer1.getModel();
        if (listModel instanceof DefaultListModel) {
            DefaultListModel<String> model = (DefaultListModel<String>) listModel;
            for (int i = 0; i < model.size(); i++) {
                String traitementStr = model.getElementAt(i);

                String nomTraitement = traitementStr.substring(0, traitementStr.lastIndexOf(" Montant")).trim();
                String prixStr = traitementStr.substring(traitementStr.lastIndexOf(":") + 1).trim();
                double prix = Double.parseDouble(prixStr);
                sb.append(nomTraitement).append(" : ").append(prix).append(" DHS").append("\n");
            }
        } else {
            sb.append("Erreur: Le modèle de la liste n'est pas un DefaultListModel<String>.");
        }

        return sb.toString();
    }
    
    //------------------------------------------------------------------------------------------------------------------------------------------
    //FORMAT DE LA DATE
    private String formatDate(Date date) throws ParseException {
        if (date == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }
    private void chargementDeTraitement(String type) {
        List<Traitement> traitementList = facture.getAllTraitement(type);
            DefaultListModel<String> model = new DefaultListModel<>();

        for (Traitement traitement : traitementList) {
            String str = traitement.getNomTraitement() + " Montant : "+ traitement.getPrix();
            model.addElement(str);
        }
        menu1.liste_Traitement.setModel(model);
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

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) { // Vérifier si un élément est sélectionné dans le JComboBox
            String selectedType = (String) menu1.type.getSelectedItem();
            //if (!selectedType.equals("Consultation") && !selectedType.equals("Consultation Spécialisé")) {
                chargementDeTraitement(selectedType);
            //}
        }
    }
    /// Affichage après génération
    
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == menu1.btajout) {
            EtablirFacture();
        } else if (e.getSource() == menu1.liste_Traitement && e.getClickCount() == 2) {
            
            String selectedTraitementStr = menu1.liste_Traitement.getSelectedValue();
            if (selectedTraitementStr != null) {
                DefaultListModel<String> modelEffectuer1 = getModel(menu1.listeEffectuer1);
                modelEffectuer1.addElement(selectedTraitementStr);
            }
            
        } else if (e.getSource() == menu1.listeEffectuer1 && e.getClickCount() == 2) {
            String selectedTraitementStr = menu1.listeEffectuer1.getSelectedValue();
            if (selectedTraitementStr != null) {
                DefaultListModel<String> modelEffectuer1 = getModel(menu1.listeEffectuer1);
                modelEffectuer1.removeElement(selectedTraitementStr);
            }
        }
    }

    private DefaultListModel<String> getModel(JList<String> list) {
        ListModel<String> model = list.getModel();
        if (model instanceof DefaultListModel) {
            return (DefaultListModel<String>) model;
        } else {
            throw new IllegalArgumentException("Le modèle de la liste n'est pas un DefaultListModel.");
        }
    }
    
    private void chargementDeFacture() {
        List<Facture> factureList = Facture.getAllFacture(connection);
        DefaultTableModel model = new DefaultTableModel(
            new Object[]{"Numéro", "Détails", "Montant", "Date", "Status"}, 
            0
        );
        for (Facture facture : factureList) {
            model.addRow(new Object[]{
                facture.getIdFacture(),
                facture.getDetails(),
                facture.getMontant(),
                facture.getDateFacture(),
                facture.getStatus(),
            });
        }
        menu1.jTable1.setModel(model);
    }
    //Chercher les type de traitement etablir par l'administrateur
    private void loadTraitementTypes() {
        List<String> traiteTypes = Traitement.getAllTraitementTypes(connection);
        Set<String> uniqueServiceTypes = new HashSet<>(traiteTypes);

        // Efface la JComboBox avant d'ajouter de nouvelles valeurs pour éviter les doublons
        menu1.type.removeAllItems();

        // Ajouter les types de services uniques à la JComboBox
        for (String serviceType : uniqueServiceTypes) {
            menu1.type.addItem(serviceType);
        }
    }
}
    
    
