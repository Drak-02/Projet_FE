/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hospitalis.Model;

import hospitalis.Controleur.MenuStocke.StockObserable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author badra
 */
public class Stock  extends StockObserable {
    //Attributs
    private String nom;
    private String type;
    private long quantite;
    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    private Connection connection;

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setQuantite(long quantite) {
        this.quantite = quantite;
    }

    public String getNom() {
        return nom;
    }

    public String getType() {
        return type;
    }

    public long getQuantite() {
        return quantite;
    }
    
    
    //Constructor
    public Stock(Connection connection){
        this.connection = connection;
    }
    //Methode
    //Ajoute de nouvelle stocke
    public boolean ajouterStocke (Connection connection) {
        // Insérer le nouvel aJOUTE si le matricule n'existe pas
            String insertQuery = "INSERT INTO Stock (article, type, quantite, date_entre) VALUES (?, ?, ?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                preparedStatement.setString(1, this.nom);
                preparedStatement.setString(2, this.type);
                preparedStatement.setLong(3, this.quantite);
                preparedStatement.setString(4, this.date);
                preparedStatement.executeUpdate();
                
                getAllStock(connection);
                System.out.println("Stocke ajouté avec succès");
                notifyObservers();
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
                }
        return true;
    }
    //La liste de stocke
    public static List<Stock> getAllStock(Connection connection) {
        List<Stock> stockeList = new ArrayList<>();
        String query = "SELECT article, type, quantite, date_entre FROM  Stock";

        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Stock stock = new Stock(connection);
                stock.setNom(resultSet.getString("article"));
                stock.setType(resultSet.getString("type"));
                stock.setQuantite(resultSet.getLong("quantite"));
                stock.setDate(resultSet.getString("date_entre"));
               
                stockeList.add(stock);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return stockeList;
    }
    public static List<Stock> getNiveau(Connection connection) {
        List<Stock> stockeList = new ArrayList<>();
        String query = "SELECT article, type, SUM(quantite) AS total_quantite, MAX(date_entre) AS last_date_entre " +
                       "FROM Stock " +
                       "GROUP BY article, type " +
                       "ORDER BY total_quantite";

        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Stock stock = new Stock(connection);
                stock.setNom(resultSet.getString("article"));
                stock.setType(resultSet.getString("type"));
                stock.setQuantite(resultSet.getLong("total_quantite"));
                stock.setDate(resultSet.getString("last_date_entre"));

                stockeList.add(stock);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return stockeList;
    }
    
   
}

 