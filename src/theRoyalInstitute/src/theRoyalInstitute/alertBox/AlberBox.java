package theRoyalInstitute.alertBox;

import javafx.scene.control.Alert;

public class AlberBox {
    public static Alert alert=new Alert(Alert.AlertType.INFORMATION);

    public static void showInformationA(String text){
        alert.setHeaderText(text);
        alert.showAndWait();
    }

    public static void showWarningA(String text){
        alert.setAlertType(Alert.AlertType.WARNING);
        alert.setHeaderText(text);
        alert.showAndWait();
    }

}
