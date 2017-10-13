import java.sql.*;

/**
 * Created by Max on 12-10-2017.
 */
public class JDBCCon {

    private Connection myConn;

    public void connect() {
        try {
            myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "tds4nvw7");
            myConn.setTransactionIsolation(myConn.TRANSACTION_READ_COMMITTED);
            System.out.println("connection established");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void disconnect(){
        try {
            myConn.close();
            System.out.println("connection closed");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void manipulateData( String name, String type, int launched){
        PreparedStatement insertData = null;

        try{
            myConn.setAutoCommit(false);
            String SQLInsert = "INSERT INTO ships (name, class, launched) VALUES(?,?,?)";
            insertData = myConn.prepareStatement(SQLInsert);

            insertData.setString(1, name);
            insertData.setString(2, type);
            insertData.setInt(3, launched);

            insertData.executeUpdate();



        } catch (SQLException e) {
            if (myConn != null) {
                try {

                    myConn.rollback();

                    System.out.println("Rolled back.");
                } catch (SQLException exrb) {
                    exrb.printStackTrace();
                }
            }
        } finally {
            try {
                if (insertData != null ) {
                    insertData.close();
                }
                myConn.setAutoCommit(true);
            } catch (SQLException excs) {
                excs.printStackTrace();
            }
        }
        }
    public void queryData(){
        ResultSet myRs = null;
        Statement myStmt = null;

        try {
            // 3.1 Execute SQL query
            myStmt = myConn.createStatement();
            myRs = myStmt.executeQuery("SELECT * FROM ships");
            System.out.println(myRs);
            // 4. Process the result set
            while (myRs.next()) {
                System.out.println(myRs.getString("name") + ", " + myRs.getString("class"));
            }
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }
    }

