
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.*;
import javafx.stage.Stage;
import sun.util.calendar.BaseCalendar;
import java.sql.*;
import java.io.*;
import java.lang.*;
import javafx.util.converter.TimeStringConverter;
import javafx.util.StringConverter;
/**
 * Created by annakertesz on 10/25/16.
 */
public class ChartCreator extends Application {


    Stage stage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        stage.setTitle("Line Chart Sample");

        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final LineChart<String, Number> lineChart = new LineChart<String, Number>(xAxis, yAxis);

        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Portfolio 1");
        series1.getData().add(new XYChart.Data("11:52:19", 23));
        series1.getData().add(new XYChart.Data("12:42:19", 1));
        series1.getData().add(new XYChart.Data("21:32:19", 50));
        Scene scene  = new Scene(lineChart,800,600);
        lineChart.getData().addAll(series1);
        stage.setScene(scene);
        stage.show();

    }
}