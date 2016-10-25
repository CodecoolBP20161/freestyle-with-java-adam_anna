package cigarette;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.scene.layout.HBox;
import javafx.scene.chart.*;
import java.lang.*;
import javafx.scene.Node;

/**
 * Created by annakertesz on 10/24/16.
 */
public class Main extends Application{

    Stage window;


    public static void main(String args[]) {
        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle("Cigarette diary");
        BorderPane layout = new BorderPane();
        StackPane layout2 = new StackPane();
        HBox hbox = addHBox();
        HBox hbox2 = addHBox2();

        layout.setBottom(hbox);
        layout.setBottom(hbox2);

        Scene scene = new Scene(layout, 600, 600);
        window.setScene(scene);
        window.show();
    }



    private HBox addHBox() {
            HBox hbox = new HBox();
            hbox.setSpacing(10);
            hbox.setStyle("-fx-background-color: #99908f;");

            Button buttonFeeling = new Button("How are you feeling?");
            buttonFeeling.setPrefSize(250, 40);
            buttonFeeling.setOnAction(event -> InputWindow.display());

            Button buttonChart = new Button("Make your data alive");
            buttonChart.setPrefSize(250, 40);
//            buttonFeeling.setOnAction(event -> {ChartCreator chartcreator = new ChartCreator();});

            Button buttonSmoke= new Button("I smoked");
            buttonSmoke.setPrefSize(80, 40);

            hbox.getChildren().addAll(buttonFeeling, buttonSmoke, buttonChart);

            return hbox;

    }

}
