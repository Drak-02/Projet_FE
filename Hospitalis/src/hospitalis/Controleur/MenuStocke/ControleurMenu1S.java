/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hospitalis.Controleur.MenuStocke;

import hospitalis.Interface.componentSt.Menu1S;
import hospitalis.Model.Stock;
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
/**
 *
 * @author badra
 */
public class ControleurMenu1S implements MouseListener, ListSelectionListener {
   //
    private Connection connection;
    private Menu1S menu1;
    private Stock stock;
    private static ControleurMenu1S instance;
    //
    public ControleurMenu1S(Connection connection, Menu1S menu) {
        this.connection = connection;
        this.menu1 = menu;
        
        System.out.println("Appe creation menuCont1");
        
        this.menu1.btadd.addMouseListener(this);
        
        // this.menu.btsupprimer.addMouseListener(this);
        //this.menu.btmodifier.addMouseListener(this);
        this.menu1.jtables.getSelectionModel().addListSelectionListener(this);
        
    }
    //
    //Contrôle la creation des instances lors de l'arriver pour ne pas créer a chaque fois une autre instance ( surcharge).
    public static ControleurMenu1S getInstance(Connection connection, Menu1S menu) {
        
        if (instance == null) {
            synchronized (ControleurMenu1S.class) {
                if (instance == null) {
                    instance = new ControleurMenu1S(connection,menu);
                    System.out.println("Appel a l'instance de comtr me4444n");
                }
            }
        }
        return instance;
    }
    ///
    public void afficherMenu() {
        if (menu1.isVisible()) {
            menu1.setVisible(false);
        } else {
            menu1.setVisible(true);
        }
        updateTable();
    }
    //**************************************************************************

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("CLicked stock1");
        if(e.getSource() == menu1.btadd){        
            handleAddStock();
            System.out.println("CLicked stock2");
        }
    }
    
    //////////
    private void handleAddStock() {
        try {
            String dateStr = formatDate(menu1.sdate.getDate());
            stock = new Stock(connection);
            stock.setNom(menu1.ipArt.getText());
            stock.setType((String) menu1.jtype.getSelectedItem());
            stock.setQuantite(Long.parseLong(menu1.ipQuant.getText()));
            stock.setDate(dateStr);
            
            if (menu1.ipArt.getText().isEmpty() || Long.parseLong(menu1.ipQuant.getText())< 0 || menu1.sdate == null ) {
                JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs requis.", "Erreur", JOptionPane.ERROR_MESSAGE);
                return; // Sortir de la méthode si les champs sont vides
            }

            boolean success = stock.ajouterStocke(connection);
            if (success) {
                JOptionPane.showMessageDialog(null, "Stock ajouté avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);
                EffacerChamps();
                updateTable(); // Mise à jour de la table après ajout
            } else {
                JOptionPane.showMessageDialog(null, "Erreur lors de l'ajout de Stock.", "Erreur", JOptionPane.ERROR_MESSAGE);
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
        menu1.ipArt.setText("");
        menu1.ipQuant.setText("");
        menu1.sdate.setDate(null);
        //menu1A.buttonGroup1.clearSelection();
        menu1.jtype.setSelectedIndex(0);
    }
    //Cette methodes mes permet de chercher les données et afficher
    private void chargementDeStocke() {
        List<Stock> stockList = Stock.getAllStock(connection);
        DefaultTableModel model = new DefaultTableModel(
            new Object[]{"Article", "Type", "Quantité", "Date"}, 
            0
        );
        for (Stock stock : stockList) {
            model.addRow(new Object[]{
                stock.getNom(),
                stock.getType(),
                stock.getQuantite(),
                stock.getDate(),
            });
        }
        menu1.jtables.setModel(model);
    }
    

    private void updateTable() {
        chargementDeStocke();
    }
    //
    @Override
    public void valueChanged(ListSelectionEvent e) {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = menu1.jtables.getSelectedRow();
                if (selectedRow >= 0) {
                    TableModel model = menu1.jtables.getModel();
                    menu1.ipArt.setText(model.getValueAt(selectedRow, 0).toString());
                    menu1.jtype.setSelectedItem(model.getValueAt(selectedRow, 1).toString());
                    menu1.ipQuant.setText(model.getValueAt(selectedRow, 2).toString());

                    try {
                        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(model.getValueAt(selectedRow, 3).toString());
                        menu1.sdate.setDate(date);
                    } catch (ParseException ex) {
                        Logger.getLogger(ControleurMenu1S.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
    }
    ///-------------------------------------------------------------------------
       /*
        private void handleModifyStock() throws ParseException {
        int selectedRow = menu1.jtables.getSelectedRow();
        if (selectedRow >= 0) {
            int codeService = (int) menu1.jtables.getValueAt(selectedRow, 0);
            
            stock = new Stock(connection);
            stock.setNom();
            stock.setNomService(menu.NomService.getText());
            stock.setDesService(menu.jdes.getText());

            try {
                // Récupération des données actuelles du tableau

                int currentCodeService = (int) menu.jtables.getValueAt(selectedRow, 0);
                String currentNom = (String) menu.jtables.getValueAt(selectedRow, 1);
                String currentDes = (String) menu.jtables.getValueAt(selectedRow, 2);

                // Comparaison avec les valeurs actuelles et mise à jour si nécessaire
                if (codeService != currentCodeService) {
                    service.setCodeService(codeService);
                }
                if (!menu.NomService.getText().equals(currentNom)) {
                    service.setNomService(menu.NomService.getText());
                }
                if (!menu.jdes.getText().equals(currentDes)) {
                    service.setDesService(menu.jdes.getText());
                }
                // Répétez le processus pour les autres champs

                boolean success = service.modifierService(connection);
                if (success) {
                    JOptionPane.showMessageDialog(null, "Service modifié avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);
                    updateTable(); // Mise à jour de la table après modification
                } else {
                    JOptionPane.showMessageDialog(null, "Erreur lors de la modification de Service", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erreur lors de la modification de l'utilisateur : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Veuillez sélectionner un Service à modifier.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
    */
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
