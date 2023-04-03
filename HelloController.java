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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private TextField nom;


    @FXML
    private TextField tele;


    @FXML
    private TableView<Livreur> mytable;


    @FXML
    private TableColumn<Livreur, Long> col_id;

    @FXML
    private TableColumn<Livreur, String> col_nom;

    @FXML
    private TableColumn<Livreur, String> col_tele;


    private Livreur livreur;

    @FXML
    protected void onSaveButtonClick() {

        // accees a la bdd

        try {
            LivreurDAO livreurDAO = new LivreurDAO();

            Livreur liv = new Livreur(0L, nom.getText(), tele.getText());


            livreurDAO.save(liv);


            UpdateTable();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public void setlivreur(Livreur livreur) {
        this.livreur = livreur;
        nom.setText(livreur.getNom());

        tele.setText(livreur.getTelephone());

    }

    @FXML
 void onUpdateButtonClick()throws SQLException   {


            Livreur livreurSelected = mytable.getSelectionModel().getSelectedItem();
            if (livreurSelected != null) {
                livreurSelected.setNom(nom.getText());
                livreurSelected.setTelephone(tele.getText());
                LivreurDAO livreurDAO = new LivreurDAO();
                livreurDAO.update(livreurSelected);
                mytable.refresh();
            }
        }

    @FXML
    protected void onRowClick() {
        Livreur liv = mytable.getSelectionModel().getSelectedItem();
        nom.setText( liv.getNom() );
        tele.setText( liv.getTelephone() );

    }
    @FXML
    void ondeleteButtonClick() {
        try {
            // Récupérer le livreur sélectionné dans la table
            Livreur selectedLivreur = mytable.getSelectionModel().getSelectedItem();

            if (selectedLivreur != null) {
                // Confirmer la suppression avec l'utilisateur
                Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
                confirmationAlert.setTitle("Confirmation de suppression");
                confirmationAlert.setHeaderText(null);
                confirmationAlert.setContentText("Êtes-vous sûr de vouloir supprimer le livreur sélectionné ?");

                Optional<ButtonType> result = confirmationAlert.showAndWait();
                if (((Optional<?>) result).get() == ButtonType.OK) {
                    // Supprimer le livreur de la base de données
                    LivreurDAO livreurDAO = new LivreurDAO();

                    // Mettre à jour la table des livreurs
                    mytable.getItems().remove(selectedLivreur);
                }
            } else {
                // Aucun livreur sélectionné, afficher un message d'erreur
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setTitle("Erreur de suppression");
                errorAlert.setHeaderText(null);
                errorAlert.setContentText("Veuillez sélectionner un livreur à supprimer.");
                errorAlert.showAndWait();
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void UpdateTable() {
        col_id.setCellValueFactory(new PropertyValueFactory<Livreur, Long>("id_livreur"));
        col_nom.setCellValueFactory(new PropertyValueFactory<Livreur, String>("nom"));
        col_tele.setCellValueFactory(new PropertyValueFactory<Livreur, String>("telephone"));


        mytable.setItems(this.getDataLivreurs());

    }

    public static ObservableList<Livreur> getDataLivreurs() {

        LivreurDAO livreurDAO = null;

        ObservableList<Livreur> listfx = FXCollections.observableArrayList();

        try {
            livreurDAO = new LivreurDAO();

            for (Livreur ettemp : livreurDAO.getAll())
                listfx.add(ettemp);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listfx;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UpdateTable();

    }


}
