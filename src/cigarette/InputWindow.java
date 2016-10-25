package cigarette;

import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;


public class InputWindow {

    public static void display () {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Register mood");
        window.setMinWidth(400);

        Label label = new Label();
        label.setText("Enter how you feel");

        //Create two buttons
        Label labelMood = new Label();
        labelMood.setText("mood");

        Slider inputMood = new Slider(0, 100, 5);

        Label labelCarving = new Label();
        labelCarving.setText("carving:");

        Slider inputCarving = new Slider(0, 100, 5);

        Button submitButton = new Button("Submit");

        submitButton.setOnAction(event -> {
            double mood = inputMood.getValue();
            double carving = inputCarving.getValue();
            double[] list = {mood, carving};
            InsertData dataline = new InsertData(list);
            dataline.insert();
            window.close();
        });

        VBox layout = new VBox();
        layout.getChildren().addAll(label, labelMood, inputMood, labelCarving, inputCarving, submitButton);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();


    }
}