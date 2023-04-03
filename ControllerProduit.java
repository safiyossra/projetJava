package ma.fstt.trackingl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ma.fstt.model.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class ControllerProduit implements Initializable {


        @FXML
        private TableColumn<Produit, String> col_dis;

        @FXML
        private TableColumn<Produit, Long> col_id;

        @FXML
        private TableColumn<Produit, String> col_nom;

        @FXML
        private TableColumn<Produit,Float> col_pu;

        @FXML
        private TextField discription ;

        @FXML
        private TableView<Produit> mytable;

        @FXML
        private TextField Nom;

        @FXML
        private Button ok;

        @FXML
        private Button oui;

        @FXML
        private TextField prix_unitaire;

        @FXML
        private Button yes;
    private Produit produit;

    @FXML
        void onSaveButtonClick() {
            try {

                ProduitDAO produitDAO = new ProduitDAO();

                Produit pro = new Produit(0L, Nom.getText() ,(float) Float.parseFloat(prix_unitaire.getText()) ,discription.getText());


                produitDAO.save(pro);


                UpdateTable();




            } catch (SQLException e) {
                throw new RuntimeException(e);
            }


        }
    public void UpdateTable() {
        col_id.setCellValueFactory(new PropertyValueFactory<Produit, Long>("id_produit"));
        col_nom.setCellValueFactory(new PropertyValueFactory<Produit, String>("Nom"));
        col_pu.setCellValueFactory(new PropertyValueFactory<Produit, Float>("prix_unitaire"));
        col_dis.setCellValueFactory(new PropertyValueFactory<Produit, String>("description"));


        mytable.setItems(this.getDataProduits());
    }
    public static ObservableList<Produit> getDataProduits(){

       ProduitDAO produitDAO = null;

        ObservableList<Produit> listfx = FXCollections.observableArrayList();

        try {
            produitDAO = new ProduitDAO();

            for (Produit ettemp : produitDAO.getAll())
                listfx.add(ettemp);

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listfx ;
    }

public void setproduit(Produit produit) {
        this.produit = produit;
        Nom.setText(produit.getNom());
        prix_unitaire.setText(String.valueOf(Float.valueOf(produit.getPrix_unitaire())));
        discription.setText(produit.getDescription());

    }

    @FXML
 protected  void onUpdateButtonClick() {
        Produit selectedProduit = mytable.getSelectionModel().getSelectedItem();
        if (selectedProduit != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("produit.fxml"));
                Parent root = loader.load();
                ControllerProduit ProduitFormController = loader.getController();
                ProduitFormController.setproduit(selectedProduit);
                Stage stage = new Stage();
                stage.setTitle("Modifier le produit");
                stage.setScene(new Scene(root));
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.showAndWait();
                mytable.refresh();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aucun produit sélectionné");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez sélectionner un produit à mettre à jour.");
            alert.showAndWait();

        }

    }


    @FXML
 protected void ondeleteButtonClick() {
            try {
                // Récupérer le produit sélectionné dans la table
                Produit selectedProduit = mytable.getSelectionModel().getSelectedItem();

                if (selectedProduit != null) {
                    // Confirmer la suppression avec l'utilisateur
                    Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
                    confirmationAlert.setTitle("Confirmation de suppression");
                    confirmationAlert.setHeaderText(null);
                    confirmationAlert.setContentText("Êtes-vous sûr de vouloir supprimer le produit sélectionné ?");

                    Optional<ButtonType> result = confirmationAlert.showAndWait();
                    if (((Optional<?>) result).get() == ButtonType.OK){
                        // Supprimer le produit de la base de données
                       ProduitDAO produitDAO = new ProduitDAO();

                        // Mettre à jour la table des produits
                        mytable.getItems().remove(selectedProduit);
                    }
                } else {
                    // Aucun produit sélectionné, afficher un message d'erreur
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                    errorAlert.setTitle("Erreur de suppression");
                    errorAlert.setHeaderText(null);
                    errorAlert.setContentText("Veuillez sélectionner un produit à supprimer.");
                    errorAlert.showAndWait();
                }


            } catch (SQLException e) {
                throw new RuntimeException(e);
            }


        }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        UpdateTable() ;
    }
}


