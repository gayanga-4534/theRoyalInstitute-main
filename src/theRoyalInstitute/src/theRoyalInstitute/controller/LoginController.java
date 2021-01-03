package theRoyalInstitute.controller;

import theRoyalInstitute.alertBox.AlberBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import theRoyalInstitute.preferances.Preferances;

import java.io.IOException;

public class LoginController {

    public TextField nameField;
    public TextField passField;


    public void loginAction(ActionEvent actionEvent) throws IOException {
        if(nameField.getText().equals(Preferances.getUserName()) & passField.getText().equals(Preferances.getPassword())){
            Stage stage=(Stage) nameField.getScene().getWindow();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/theRoyalInstitute/view/dashboard.fxml"))));
            stage.setTitle("Main menu");
        }
        else{
            AlberBox.showWarningA("Invalid Input please try again");
        }
    }
}
