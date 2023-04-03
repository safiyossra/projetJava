package ma.fstt.model;

import javafx.scene.text.Font;

public class commande {

    private long id_commande;
    private String Date_D;
    private String Date_f;
    private String klm;
    private String Client;

    public commande() {

    }

    public commande(long id_commande, String date_D, String date_f, String klm, String client) {
        this.id_commande = id_commande;
        Date_D = date_D;
        Date_f = date_f;
        this.klm = klm;
        Client = client;
    }

    public long getId_commande() {
        return id_commande;
    }

    public void setId_commande(long id_commande) {
        this.id_commande = id_commande;
    }

    public String getDate_D() {
        return Date_D;
    }

    public void setDate_D(String date_D) {
        Date_D = date_D;
    }

    public String getDate_f() {
        return Date_f;
    }

    public void setDate_f(String date_f) {
        Date_f = date_f;
    }

    public String getKlm() {
        return klm;
    }

    public void setKlm(String klm) {
        this.klm = klm;
    }

    public String getClient() {
        return Client;
    }

    public void setClient(String client) {
        Client = client;
    }

    @Override
    public String toString() {
        return "commande{" +
                "id_commande=" + id_commande +
                ", Date_D='" + Date_D + '\'' +
                ", Date_f='" + Date_f + '\'' +
                ", klm=" + klm +
                ", Client='" + Client + '\'' +
                '}';
    }
}

