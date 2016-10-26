package cigarette;

import java.sql.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;


public class TableCreator {
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
                    "(TIMESTAMP      TIMESTAMP    NOT NULL)";
            stmt.executeUpdate(cigaretteSql);

//            String sql = "INSERT INTO DIARY " +
//                    "VALUES ('2016-10-20 10:45:20', 50, 20)";
//            stmt.executeUpdate(sql);
//
//            sql = "INSERT INTO DIARY " +
//                    "VALUES ('2016-10-20 23:45:20', 75, 50)";
//            stmt.executeUpdate(sql);
//
//            sql = "INSERT INTO DIARY " +
//                    "VALUES ('2016-10-21 06:45:20', 20, 90)";
//            stmt.executeUpdate(sql);
//
//            sql = "INSERT INTO DIARY " +
//                    "VALUES ('2016-10-21 17:45:20', 20, 70)";
//            stmt.executeUpdate(sql);
//
//            sql = "INSERT INTO DIARY " +
//                    "VALUES ('2016-10-22 10:45:20', 50, 20)";
//            stmt.executeUpdate(sql);
//
//            sql = "INSERT INTO DIARY " +
//                    "VALUES ('2016-10-23 06:45:20', 40, 30)";
//            stmt.executeUpdate(sql);
//
//            sql = "INSERT INTO DIARY " +
//                    "VALUES ('2016-10-23 12:45:20', 60, 10)";
//            stmt.executeUpdate(sql);
//
//            sql = "INSERT INTO DIARY " +
//                    "VALUES ('2016-10-23 21:45:20', 65, 12)";
//            stmt.executeUpdate(sql);
//
//            sql = "INSERT INTO DIARY " +
//                    "VALUES ('2016-10-24 14:45:20', 70, 7)";
//            stmt.executeUpdate(sql);
//
//            sql = "INSERT INTO DIARY " +
//                    "VALUES ('2016-10-24 23:45:20', 30, 50)";
//            stmt.executeUpdate(sql);
//
//            sql = "INSERT INTO DIARY " +
//                    "VALUES ('2016-10-25 14:45:20', 50, 20)";
//            stmt.executeUpdate(sql);
//
//            sql = "INSERT INTO CIGARETTE " +
//                    "VALUES ('2016-10-20 14:45:20')";
//            stmt.executeUpdate(sql);
//
//            sql = "INSERT INTO CIGARETTE " +
//                    "VALUES ('2016-10-20 23:45:20')";
//            stmt.executeUpdate(sql);
//
//
//            sql = "INSERT INTO CIGARETTE " +
//                    "VALUES ('2016-10-21 08:45:20')";
//            stmt.executeUpdate(sql);
//
//
//            sql = "INSERT INTO CIGARETTE " +
//                    "VALUES ('2016-10-21 14:45:20')";
//            stmt.executeUpdate(sql);
//
//
//            sql = "INSERT INTO CIGARETTE " +
//                    "VALUES ('2016-10-21 18:45:20')";
//            stmt.executeUpdate(sql);
//
//
//            sql = "INSERT INTO CIGARETTE " +
//                    "VALUES ('2016-10-21 23:45:20')";
//            stmt.executeUpdate(sql);
//
//
//            sql = "INSERT INTO CIGARETTE " +
//                    "VALUES ('2016-10-22 13:45:20')";
//            stmt.executeUpdate(sql);
//
//
//            sql = "INSERT INTO CIGARETTE " +
//                    "VALUES ('2016-10-22 23:45:20')";
//            stmt.executeUpdate(sql);
//
//
//            sql = "INSERT INTO CIGARETTE " +
//                    "VALUES ('2016-10-23 11:45:20')";
//            stmt.executeUpdate(sql);
//
//
//            sql = "INSERT INTO CIGARETTE " +
//                    "VALUES ('2016-10-24 18:45:20')";
//            stmt.executeUpdate(sql);
//
//
//            sql = "INSERT INTO CIGARETTE " +
//                    "VALUES ('2016-10-25 14:45:20')";
//            stmt.executeUpdate(sql);




            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        System.out.println("Table created successfully");
    }
}