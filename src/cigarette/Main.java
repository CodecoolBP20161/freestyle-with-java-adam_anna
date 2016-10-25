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

    private HBox addHBox2() throws SQLException, ParseException {
        Connection c = null;
        Statement stmt = null;
        ObservableList data = FXCollections.observableArrayList();

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/annakertesz", "postgres", "bmpa88");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Opened database successfully");
        HBox hbox = new HBox();
        hbox.setStyle("-fx-background-color: #fff2f0;");

        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        final LineChart<Number, Number> lineChart = new LineChart<Number, Number>(xAxis, yAxis);

        PieChart pieChart = new PieChart();

        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Portfolio 1");


        String SQL = "SELECT * FROM DIARY";
        ResultSet rs = c.createStatement().executeQuery(SQL);
        while(rs.next()) {
            Long epoch = new java.text.SimpleDateFormat("yyyy-mm-dd HH:mm:ss").parse(rs.getString(1)).getTime() / 1000;
            series1.getData().add(new XYChart.Data(epoch, rs.getDouble(2)));
        }
//        series1.getData().add(new XYChart.Data("11:52:19", 23));
//        series1.getData().add(new XYChart.Data("12:42:19", 1));
//        series1.getData().add(new XYChart.Data("21:32:19", 50));
        lineChart.setPrefSize(980,500);
        lineChart.getData().addAll(series1);

        hbox.getChildren().addAll(lineChart);


        return hbox;

    }

}
