/**
 * Created by Max on 12-10-2017.
 */
import java.sql.*;



public class Transaction {


    public static void main(String[] args) throws SQLException {
        JDBCCon conni = new JDBCCon();
        ResultSet myRs = null;
        Statement myStmt = null;
        Connection con = null;
        try {
            // 1. Get a connection to database and set isolation level for transactions, also disabling autocommit
            con = conni.connect();

            // 2. Create a statement

            // 3.2 update table
            conni.manipulateData("rtyr", "rtyr", 87);

            // 3.1 Execute SQL query
            myStmt = con.createStatement();
            myRs = myStmt.executeQuery("select * from ships");
            // 4. Process the result set
            while (myRs.next()) {
                System.out.println(myRs.getString("name") + ", " + myRs.getString("class"));
            }

            conni.disconnect();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }
}
