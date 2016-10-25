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
        HBox hbox = addHBox();
        HBox hbox2 = addHBox2();

        layout.setBottom(hbox);
        layout.setCenter(hbox2);

        Scene scene = new Scene(layout, 1000, 600);
        window.setScene(scene);
        window.show();
    }



    private HBox addHBox() {
            HBox hbox = new HBox();
            hbox.setSpacing(10);
            hbox.setStyle("-fx-background-color: #99908f;");

            Button buttonFeeling = new Button("How are you feeling?");
            buttonFeeling.setPrefSize(450, 40);
            buttonFeeling.setOnAction(event -> InputWindow.display());

            Button buttonChart = new Button("Make your data alive");
            buttonChart.setPrefSize(450, 40);
//            buttonFeeling.setOnAction(event -> {ChartCreator chartcreator = new ChartCreator();});

            Button buttonSmoke= new Button("I smoked");
            buttonSmoke.setPrefSize(80, 40);

            hbox.getChildren().addAll(buttonFeeling, buttonSmoke, buttonChart);

            return hbox;

    }

    private HBox addHBox2() {
        HBox hbox = new HBox();
        hbox.setStyle("-fx-background-color: #fff2f0;");

        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final LineChart<String, Number> lineChart = new LineChart<String, Number>(xAxis, yAxis);

        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Portfolio 1");
        series1.getData().add(new XYChart.Data("11:52:19", 23));
        series1.getData().add(new XYChart.Data("12:42:19", 1));
        series1.getData().add(new XYChart.Data("21:32:19", 50));
        lineChart.setPrefSize(980,500);
        lineChart.getData().addAll(series1);
        hbox.getChildren().addAll(lineChart);


        return hbox;

    }

}
