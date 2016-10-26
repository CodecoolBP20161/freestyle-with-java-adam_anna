package cigarette;


import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.*;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import sun.util.calendar.BaseCalendar;
import java.sql.*;
import java.io.*;
import java.lang.*;
import java.text.ParseException;

import javafx.util.converter.TimeStringConverter;
import javafx.util.StringConverter;
/**
 * Created by annakertesz on 10/25/16.
 */
public class ChartPopulator {
    Connection c = null;
    Statement stmt = null;
    ObservableList data = FXCollections.observableArrayList();
    String SQL = "SELECT * FROM DIARY";

    public ChartPopulator() {
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

    }

    public ResultSet fullSet() throws SQLException {
        ResultSet result = c.createStatement().executeQuery(SQL);
        return result;
    }

    public ResultSet firstSet() throws SQLException {
        PreparedStatement statement = c.prepareStatement(SQL);
        statement.setMaxRows(1);
        ResultSet firstRs = statement.executeQuery();
        return firstRs;
    }

    //
    public ResultSet cigarSet() throws SQLException {
        String cSQL = "SELECT * FROM CIGARETTE";
        ResultSet result = c.createStatement().executeQuery(cSQL);
        return result;
    }
}

