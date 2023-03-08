package org.example;
import java.sql.*;
/**
 * Step 1. import the package.
 * Step 2. a) load and register the driver.
 *         b) Establish the connection
 * Step 3. Create Statement.
 * Step 4. Execute Query.
 * Step 5. Process Result.
 * Step 6. close connection.
 */
public class App 
{
    public static void main( String[] args ) throws Exception {
        String url = "jdbc:mysql://localhost:3306/PlayGround";
        String uname = "root";
        String upwd ="";
        Class.forName("java.sql.Driver");//load the driver
        Connection connection = DriverManager.getConnection(url,uname,upwd);//establish the connection
        process(connection);
        processDDL(connection);
        connection.close();
    }
    private static void processDDL(Connection connection)throws Exception {
        String query = "INSERT INTO PlayGround.Persons VALUES(?, 'Arya', 'Shruti', 'ParkSt', 'Kolkata')";
        PreparedStatement statement = connection.prepareStatement(query); //Create Statement
        statement.setInt(1,5);
        int count = statement.executeUpdate(); // execute query
        System.out.println(count +" row/s affected");
        statement.close();
    }
    private static void process(Connection connection)throws Exception {
        String query = "SELECT * FROM Persons";
        Statement statement = connection.createStatement(); //Create Statement
        ResultSet resultSet = statement.executeQuery(query); // execute query
        while(resultSet.next()) {
            String name = resultSet.getString("FirstName") + " " + resultSet.getString("LastName");
            System.out.println("Hello " + name);
        }
        statement.close();
    }
}
