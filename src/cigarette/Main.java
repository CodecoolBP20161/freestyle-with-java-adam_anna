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
import javafx.scene.shape.Line;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.shape.Line;
import javafx.stage.*;
import javafx.scene.layout.HBox;
import java.util.List;
import java.util.Arrays;
import java.util.Objects;
import javafx.scene.chart.*;
import java.lang.*;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.ParseException;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.Node;
import java.util.ArrayList;
import javax.xml.stream.XMLEventWriter;
import javax.xml.transform.Result;

import static thorwin.math.Math.polyfit;
import static thorwin.math.Math.polynomial;

/**
 * Created by annakertesz on 10/24/16.
 */
public class Main extends Application{

    Stage window;
//    List<Double> xs = new ArrayList<>(Arrays.asList());
//    List<Double> ys = new ArrayList<>(Arrays.asList());


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
            hbox.setStyle("-fx-background-color: #99908f;");

            Button buttonFeeling = new Button("How are you feeling?");
            buttonFeeling.setPrefSize(450, 40);
            buttonFeeling.setOnAction(event -> InputWindow.display());

            Button buttonChart = new Button("Make your data alive");
            buttonChart.setPrefSize(450, 40);
            buttonChart.setOnAction(event -> {
                try {
                    refresh(hBox2, layout);
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
        carving.setName("Carving");

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
                carving.getData().add(new XYChart.Data(epoch, fullSet.getDouble(3)));}
            while (cigarSet.next()) {
                Long cigoTime = new java.text.SimpleDateFormat("yyyy-mm-dd HH:mm:ss").parse(cigarSet.getString(1)).getTime() / 1000 - firstDay;
                cigarettes.getData().add(new XYChart.Data(cigoTime, 105));
            }
//                xs.add(fullSet.getDouble(2));
//                ys.add(fullSet.getDouble(3));
//            while(cigarSet.next()) {
//                Long smokeTime = new java.text.SimpleDateFormat("yyyy-mm-dd HH:mm:ss").parse(cigarSet.getString(1)).getTime() / 1000 - firstDay;
//
//                Data<Number, Number> verticalMarker = new Data<>(smokeTime, 0);
//                lineChart.addVerticalValueMarker(verticalMarker);
//                mood.getData().add(new XYChart.Data(epoch, fullSet.getDouble(2)));
//                carving.getData().add(new XYChart.Data(epoch, fullSet.getDouble(3)));}
            }

            // calculate the polynomial coefficients and calculate trend points
//            double[] xpoints = xs.toArray(new Double[xs.size()]);
//            double[] ypoints = xs.toArray(new Double[xs.size()]);
//            double[] coefficients = polyfit(xpoints, ypoints, 2);
//
//            for (double x = 0; x <= 5.0; x += 0.05) {
//                double y = polynomial(x, coefficients);
//                mood.getData().add(new XYChart.Data<>(x,y));
//            }
//        }
//




            lineChart.getData().addAll(cigarettes, mood, carving);
            lineChart.setPrefSize(980, 500);
            hbox.getChildren().addAll(lineChart);


            return hbox;

        }

}



//    public void addVerticalValueMarker(Data<Number, Number> marker) {
//        Objects.requireNonNull(marker, "the marker must not be null");
////        if (verticalMarkers.contains(marker)) return;
//        Line line = new Line();
//        marker.setNode(line );
//        hbox.getChildren().add(line);
//        verticalMarkers.add(marker);
//        }




