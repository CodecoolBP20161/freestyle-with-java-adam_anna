package cigarette;
import javafx.collections.*;
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
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.ParseException;

import javafx.scene.Node;

import javax.xml.transform.Result;

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
            buttonChart.setOnAction(event -> {
                try {
                    this.addHBox2();
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            });

            Button buttonSmoke= new Button("I smoked");
            buttonSmoke.setPrefSize(80, 40);
            buttonSmoke.setOnAction(event -> {
                double[] list = {};
                InsertData dataline = new InsertData(list);
                dataline.insert();
            });

            hbox.getChildren().addAll(buttonFeeling, buttonSmoke, buttonChart);

            return hbox;

    }

    private HBox addHBox2() throws SQLException, ParseException {
        HBox hbox = new HBox();

        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        final LineChart<Number, Number> lineChart = new LineChart<Number, Number>(xAxis, yAxis);


        XYChart.Series mood = new XYChart.Series();
        mood.setName("Mood");


        XYChart.Series carving = new XYChart.Series();
        mood.setName("Carving");


        ChartPopulator chartPopulator = new ChartPopulator();
        ResultSet firstSet = chartPopulator.firstSet();
        ResultSet fullSet = chartPopulator.fullSet();


        while(firstSet.next()) {
            Long firstDay = new java.text.SimpleDateFormat("yyyy-mm-dd HH:mm:ss").parse(firstSet.getString(1)).getTime() / 1000;
            while(fullSet.next()) {
                Long epoch = new java.text.SimpleDateFormat("yyyy-mm-dd HH:mm:ss").parse(fullSet.getString(1)).getTime() / 1000 - firstDay;
                mood.getData().add(new XYChart.Data(epoch, fullSet.getDouble(2)));
                carving.getData().add(new XYChart.Data(epoch, fullSet.getDouble(3)));
            }
        }

        lineChart.getData().addAll(mood, carving);
        lineChart.setPrefSize(980,500);
        hbox.getChildren().addAll(lineChart);


        return hbox;

    }

}
