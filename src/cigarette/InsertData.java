package cigarette;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Date;


public class InsertData {


    String sqlLine;

    Date dt = new Date();
    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String timeStamp = sdf.format(dt);

    public InsertData(double[] values) {

        if (values.length == 2) {
            sqlLine = "INSERT INTO DIARY VALUES (' " + timeStamp + "', " + values[0] + ", " + values[1] + ")";
        } else {
            sqlLine = "INSERT INTO CIGARETTE VALUES ('" + timeStamp + "')";
        }
    }


    public void insert() {
        Connection c = null;
        Statement stmt = null;

        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/annakertesz",
                            "postgres", "bmpa88");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = this.sqlLine;
            stmt.executeUpdate(sql);

            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Records created successfully");
    }
}