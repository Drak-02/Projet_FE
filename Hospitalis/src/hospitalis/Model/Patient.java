/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hospitalis.Model;

/**
 *
 * @author badra
 */
public class Patient {
    private int id_patient; // ID du patient
    private String nom; // Nom du patient
    private String prenom; // Prénom du patient
    private String dateNaissance; // Date de naissance du patient
    private String etat_civil; // État civil du patient
    private long telephone; // Numéro de téléphone du patient
    private String sexe; // Sexe du patient

    // Constructeur
    public Patient(int id_patient, String nom, String prenom, String dateNaissance, String etat_civil, long telephone, String sexe) {
        this.id_patient = id_patient;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.etat_civil = etat_civil;
        this.telephone = telephone;
        this.sexe = sexe;
    }

    // Getters et setters
    public int getId_patient() {
        return id_patient;
    }

    public void setId_patient(int id_patient) {
        this.id_patient = id_patient;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getEtat_civil() {
        return etat_civil;
    }

    public void setEtat_civil(String etat_civil) {
        this.etat_civil = etat_civil;
    }

    public long getTelephone() {
        return telephone;
    }

    public void setTelephone(long telephone) {
        this.telephone = telephone;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    // Méthode pour afficher les détails du patient
}

