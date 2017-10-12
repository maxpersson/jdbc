import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Max on 12-10-2017.
 */
public class JDBCCon {

    private Connection myConn;

    public Connection connect() {
        try {
            myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "tds4nvw7");
            System.out.println("connection established");
        }catch (SQLException e){
            e.printStackTrace();
        }
        return myConn;
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

            myConn.commit();

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
    }

