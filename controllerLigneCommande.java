package ma.fstt.trackingl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ma.fstt.model.*;
import ma.fstt.model.Lignecommande;
import ma.fstt.model.LignecommandeDAO;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class controllerLigneCommande  implements Initializable {

        @FXML
        private TableColumn<Lignecommande, Long> col_id;

        @FXML
        private TableColumn<Livreur, Long> col_idc;

        @FXML
        private TableColumn<Lignecommande, Long> col_idp;

        @FXML
        private TableColumn<Lignecommande, Long> col_qu;

        @FXML
        private TextField idc;

        @FXML
        private TextField idp;

        @FXML
        private TableView<Lignecommande> mytable;

        @FXML
        private Button ok;

        @FXML
        private TextField quan;
        private Lignecommande lcommande;

        @FXML
        void onSaveButtonClick() {
            // accees a la bdd

            try {

              LignecommandeDAO lignecommandeDAO = new LignecommandeDAO();

                int idlc= Integer.parseInt(col_idc.getText());
                int idp= Integer.parseInt(col_idp.getText());
                int idq= Integer.parseInt(col_qu.getText());

               Lignecommande lc= new Lignecommande(0L,(long) idlc, (long)idp,(long)idq);


                lignecommandeDAO.save(lc);


                UpdateTable();




            } catch (SQLException e) {
                throw new RuntimeException(e);
            }


        }
    @FXML
    void ondeleteButtonClick() {
        try {
            // Récupérer le livreur sélectionné dans la table
            Lignecommande selectedLignecommande = mytable.getSelectionModel().getSelectedItem();

            if (selectedLignecommande != null) {
                // Confirmer la suppression avec l'utilisateur
                Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
                confirmationAlert.setTitle("Confirmation de suppression");
                confirmationAlert.setHeaderText(null);
                confirmationAlert.setContentText("Êtes-vous sûr de vouloir supprimer la ligne de commande sélectionné ?");

                Optional<ButtonType> result = confirmationAlert.showAndWait();
                if (((Optional<?>) result).get() == ButtonType.OK){
                    // Supprimer la ligne de commande de la base de données
                    LignecommandeDAO lignecommandeDAO = new LignecommandeDAO();

                    // Mettre à jour la table des lignes des commandes
                    mytable.getItems().remove(selectedLignecommande);
                }
            } else {
                // Aucun ligne de commande sélectionné, afficher un message d'erreur
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setTitle("Erreur de suppression");
                errorAlert.setHeaderText(null);
                errorAlert.setContentText("Veuillez sélectionner un ligne de commande à supprimer.");
                errorAlert.showAndWait();
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public void UpdateTable(){
        col_id.setCellValueFactory(new PropertyValueFactory<Lignecommande,Long>("id"));
        col_idc.setCellValueFactory(new PropertyValueFactory<Livreur,Long>("id_commande"));
        col_idp.setCellValueFactory(new PropertyValueFactory<Lignecommande,Long>("id_produit"));
        col_qu.setCellValueFactory(new PropertyValueFactory<Lignecommande,Long>("id_produit"));



        mytable.setItems(this.getDataLivreurs());

    }

    public static ObservableList<Lignecommande> getDataLivreurs(){

       LignecommandeDAO lignecommandeDAO = null;

        ObservableList<Lignecommande> listfx = FXCollections.observableArrayList();

        try {
            lignecommandeDAO = new LignecommandeDAO();

            for (Lignecommande lc : lignecommandeDAO.getAll())
                listfx.add(lc);

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listfx ;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        UpdateTable();
    }
public void setLcommande(Lignecommande lcommande) {
    this.lcommande =lcommande;

}
    public void onUpdateButtonClick(ActionEvent actionEvent) {

        Lignecommande selectedLcommande = mytable.getSelectionModel().getSelectedItem();
        if (selectedLcommande != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("hello_ligneCommande.fxml"));
                Parent root = loader.load();
                controllerLigneCommande lcommandeFormController = loader.getController();
                lcommandeFormController.setLcommande(selectedLcommande);
                Stage stage = new Stage();
                stage.setTitle("Modifier ligne commande");
                stage.setScene(new Scene(root));
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.showAndWait();
                mytable.refresh();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aucun livreur sélectionné");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez sélectionner un livreur à mettre à jour.");
            alert.showAndWait();

        }
    }


}




