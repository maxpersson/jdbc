/**
 * Created by Max on 12-10-2017.
 */

public class Transaction {


    public static void main(String[] args) {
        JDBCCon conni = new JDBCCon();

            // 1. Get a connection to database and set isolation level for transactions, also disabling autocommit
            conni.connect();

            // 3.2 update table
            conni.manipulateData("heheh", "2532342", 56);


            // 3.1 Execute SQL query

            conni.queryData();

            conni.disconnect();

    }
}
