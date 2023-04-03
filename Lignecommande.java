package ma.fstt.model;

public class Lignecommande {
    private long id;
    private long id_commande;
    private long id_produit;
    private long quantite;
    public Lignecommande() {

    }
    public Lignecommande(long id, long id_commande, long id_produit, long quantite) {
        this.id = id;
        this.id_commande = id_commande;
        this.id_produit = id_produit;
        this.quantite = quantite;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId_commande() {
        return id_commande;
    }

    public void setId_commande(long id_commande) {
        this.id_commande = id_commande;
    }

    public long getId_produit() {
        return id_produit;
    }

    public void setId_produit(long id_produit) {
        this.id_produit = id_produit;
    }

    public long getQuantite() {
        return quantite;
    }

    public void setQuantite(long quantite) {
        this.quantite = quantite;
    }

    @Override
    public String toString() {
        return "Lignecommande{" +
                "id=" + id +
                ", id_commande=" + id_commande +
                ", id_produit=" + id_produit +
                ", quantite=" + quantite +
                '}';
    }


}

