package ma.fstt.trackingl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class contro {



    @FXML
    private Button commande;

    @FXML
    private Button ligne;

    @FXML
    private Button livreur;

    @FXML
    private Button produit;

    @FXML
    public void ButtonClickLivreur () throws IOException {
        // to hide dashboard
        livreur.getScene().getWindow().hide();
        // to show livreur intrface
        Parent root= FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        Stage stage= new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    public void ButtonClickCommande () throws IOException {
        // to hide dashboard
        commande.getScene().getWindow().hide();
        // to show livreur intrface
        Parent root= FXMLLoader.load(getClass().getResource("hello-viecommande.fxml"));
        Stage stage= new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    public void ButtonClickProduit () throws IOException {
        // to hide dashboard
        produit.getScene().getWindow().hide();
        // to show livreur intrface
        Parent root= FXMLLoader.load(getClass().getResource("produit.fxml"));
        Stage stage= new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setScene(scene);
        stage.show();

    }
    @FXML
    public void ButtonClickLigne() throws IOException {
        // to hide dashboard
       ligne.getScene().getWindow().hide();
        // to show livreur intrface
        Parent root= FXMLLoader.load(getClass().getResource("hello_ligneCommande.fxml"));
        Stage stage= new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setScene(scene);
        stage.show();

    }
}
