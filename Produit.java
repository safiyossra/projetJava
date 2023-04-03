package ma.fstt.model;

import javafx.scene.text.Font;

public class Produit {
    private long id_produit;
    private String Nom;
    private float prix_unitaire;
    private String description;

    public Produit() {

    }

    public long getId_produit() {
        return id_produit;
    }

    public void setId_produit(long id_produit) {
        this.id_produit = id_produit;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public float getPrix_unitaire() {
        return prix_unitaire;
    }

    public void setPrix_unitaire(float prix_unitaire) {
        this.prix_unitaire = prix_unitaire;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Produit{" +
                "id_produit=" + id_produit +
                ", Nom='" + Nom + '\'' +
                ", prix_unitaire=" + prix_unitaire +
                ", description='" + description + '\'' +
                '}';
    }

    public Produit(long id_produit, String nom,float prix_unitaire, String description) {
        this.id_produit = id_produit;
        Nom = nom;
        this.prix_unitaire = prix_unitaire;
        this.description = description;
    }
}
