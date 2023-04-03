package ma.fstt.trackingl;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;



public class helloapplicationCommande extends Application {

        @Override
        public void start(Stage stage) throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-viecommande.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 500, 500);
            stage.setTitle("Hello!");
            stage.setScene(scene);
            stage.show();
        }

        public static void main(String[] args) {
            launch();
        }
    }

