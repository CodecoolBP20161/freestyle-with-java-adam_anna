package cigarette;

import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.layout.HBox;
import javafx.stage.*;

import java.lang.*;
import java.sql.*;
import java.text.ParseException;

/**
 * Created by annakertesz on 10/24/16.
 */
public class Main extends Application {

    Stage window;


    public static void main(String args[]) {
        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle("Cigarette diary");
        BorderPane layout = new BorderPane();
        HBox hBox2 = addHBox2();
        HBox hbox = addHBox(hBox2, layout);

        layout.setBottom(hbox);
        layout.setCenter(hBox2);

        Scene scene = new Scene(layout, 1000, 600);
        scene.getStylesheets().add("style.css");
        window.setScene(scene);
        window.show();
    }


    private HBox addHBox(HBox hBox2, BorderPane layout) {
        HBox hbox = new HBox();
        hbox.setSpacing(10);

        Button buttonFeeling = new Button("How are you?");
        buttonFeeling.setPrefSize(400, 40);
        buttonFeeling.setOnAction(event -> InputWindow.display());

        Button buttonChart = new Button("Make your data alive");
        buttonChart.setPrefSize(400, 40);
        buttonChart.setOnAction(event -> {
            try {
                refresh(hBox2, layout);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });

        Button buttonSmoke = new Button("I smoked");
        buttonSmoke.getStyleClass().add("button-smoke");
        buttonSmoke.setPrefSize(180, 40);
        buttonSmoke.setOnAction(event -> {
            double[] list = {};
            InsertData dataline = new InsertData(list);
            dataline.insert();
        });

        hbox.getChildren().addAll(buttonFeeling, buttonSmoke, buttonChart);

        return hbox;

    }

    public void refresh(HBox box, BorderPane layout) throws SQLException, ParseException {

        HBox hbox = box;
        layout.getChildren().remove(hbox);
        HBox hBox2 = addHBox2();
        layout.setCenter(hBox2);

    }


    public HBox addHBox2() throws SQLException, ParseException {

        HBox hbox = new HBox();

        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        final LineChart<Number, Number> lineChart = new LineChart<Number, Number>(xAxis, yAxis);


        XYChart.Series mood = new XYChart.Series();
        mood.setName("Mood");

        XYChart.Series carving = new XYChart.Series();
        carving.setName("Craving");

        XYChart.Series cigarettes = new XYChart.Series();
        cigarettes.setName("Cigarettes");



        ChartPopulator chartPopulator = new ChartPopulator();
        ResultSet firstSet = chartPopulator.firstSet();
        ResultSet fullSet = chartPopulator.fullSet();
        ResultSet cigarSet = chartPopulator.cigarSet();


        while (firstSet.next()) {
            Long firstDay = new java.text.SimpleDateFormat("yyyy-mm-dd HH:mm:ss").parse(firstSet.getString(1)).getTime() / 1000;
            while (fullSet.next()) {
                Long epoch = new java.text.SimpleDateFormat("yyyy-mm-dd HH:mm:ss").parse(fullSet.getString(1)).getTime() / 1000 - firstDay;
                mood.getData().add(new XYChart.Data(epoch, fullSet.getDouble(2)));
                carving.getData().add(new XYChart.Data(epoch, fullSet.getDouble(3)));
            }
            while (cigarSet.next()) {
                Long cigoTime = new java.text.SimpleDateFormat("yyyy-mm-dd HH:mm:ss").parse(cigarSet.getString(1)).getTime() / 1000 - firstDay;
                cigarettes.getData().add(new XYChart.Data(cigoTime, 105));
            }
        }

        lineChart.getData().addAll(cigarettes, mood, carving);
        lineChart.setPrefSize(980, 500);
        hbox.getChildren().addAll(lineChart);

        return hbox;

    }
}



