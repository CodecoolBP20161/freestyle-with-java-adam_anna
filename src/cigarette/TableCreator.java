package cigarette;

import java.sql.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;


public class tableCreator {
    public static void main( String args[] )
    {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/annakertesz",
                            "postgres", "bmpa88");
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String diarySql = "CREATE TABLE IF NOT EXISTS DIARY " +
                    "(TIMESTAMP      TIMESTAMP    NOT NULL, " +
                    " MOOD            INT     NOT NULL, " +
                    " CARVING        INT)";
            stmt.executeUpdate(diarySql);
            String cigaretteSql = "CREATE TABLE IF NOT EXISTS CIGARETTE " +
                    "(ID INT PRIMARY KEY     NOT NULL," +
                    " TIMESTAMP      TIMESTAMP    NOT NULL)";
            stmt.executeUpdate(cigaretteSql);
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        System.out.println("Table created successfully");
    }
}