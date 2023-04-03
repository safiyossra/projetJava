package ma.fstt.trackingl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ma.fstt.model.*;
import ma.fstt.model.commande;
import ma.fstt.model.CommandeDAO;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
public class hellocontrollercommande implements Initializable{




        @FXML
        private TextField NomClient;

        @FXML
        private TableColumn<commande, String> colon_dd;

        @FXML
        private TableColumn<commande, String> colon_df;

        @FXML
        private TableColumn<commande, Long> colon_id;

        @FXML
        private TableColumn<commande, String> colon_klm;

        @FXML
        private TableColumn<commande, String> colon_nomclient;

        @FXML
        private TextField dateD;

        @FXML
        private TextField dateF;

        @FXML
        private TextField klm;

        @FXML
        private TableView<commande> tabl;



    @FXML
       protected void onSaveButtonClick()  {

            try {
               CommandeDAO commandeDAO = new CommandeDAO();

                commande com = new commande(0L, dateD.getText() , dateF.getText(),klm.getText(),NomClient.getText());


                commandeDAO.save(com);


                UpdateTable();




            } catch (SQLException e) {
                throw new RuntimeException(e);
            }


        }

    @FXML
    protected  void onUpdateButtonClick() throws SQLException {

       commande commandeSelected = tabl.getSelectionModel().getSelectedItem();
        if (commandeSelected!= null) {
           commandeSelected.setDate_D(dateD.getText());
            commandeSelected.setDate_f(dateF.getText());
            commandeSelected.setKlm(klm.getText());
            commandeSelected.setDate_f(NomClient.getText());
          CommandeDAO commandeDAO= new CommandeDAO();
            commandeDAO.update(commandeSelected);
            tabl.refresh();
        }
    }
    @FXML
    protected void onRowClick() {
       commande com = tabl.getSelectionModel().getSelectedItem();
       dateD.setText( com.getDate_f() );
        dateF.setText( com.getDate_f() );
        klm.setText( com.getKlm());
        NomClient.setText( com.getClient() );

    }
    @FXML
    protected void ondeleteButtonClick() {
        try {
            // Récupérer la commande sélectionné dans la table
            commande selectedcommande = tabl.getSelectionModel().getSelectedItem();

            if (selectedcommande != null) {
                // Confirmer la suppression avec l'utilisateur
                Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
                confirmationAlert.setTitle("Confirmation de suppression");
                confirmationAlert.setHeaderText(null);
                confirmationAlert.setContentText("Êtes-vous sûr de vouloir supprimer la commande sélectionné ?");

                Optional<ButtonType> result = confirmationAlert.showAndWait();
                if (((Optional<?>) result).get() == ButtonType.OK){
                    // Supprimer la commande de la base de données
                    ProduitDAO produitDAO = new ProduitDAO();

                    // Mettre à jour la table des produits
                    tabl.getItems().remove(selectedcommande);
                }
            } else {
                // Aucun commande sélectionné, afficher un message d'erreur
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setTitle("Erreur de suppression");
                errorAlert.setHeaderText(null);
                errorAlert.setContentText("Veuillez sélectionner une commande à supprimer.");
                errorAlert.showAndWait();
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
    public void UpdateTable(){
        colon_id.setCellValueFactory(new PropertyValueFactory<commande,Long>("id_commande"));
        colon_dd.setCellValueFactory(new PropertyValueFactory<commande,String>("Date_D"));
        colon_df.setCellValueFactory(new PropertyValueFactory<commande,String>("Date_f"));
        colon_klm.setCellValueFactory(new PropertyValueFactory<commande,String>("klm"));
        colon_nomclient.setCellValueFactory(new PropertyValueFactory<commande,String>("Client"));




        tabl.setItems(this.getDatacommande());

    }
    public static ObservableList<commande> getDatacommande(){

        CommandeDAO commandeDAO = null;

        ObservableList<commande> listf = FXCollections.observableArrayList();

        try {
            commandeDAO= new CommandeDAO();

            for (commande ettemp : commandeDAO.getAll())
                listf.add(ettemp);

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listf ;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        UpdateTable();
    }
}

