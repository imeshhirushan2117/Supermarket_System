package bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

    private static DbConnection dbConnection = null;
    private Connection connection;

    private DbConnection () throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Supermarket","root","1234");
    }
    public static DbConnection getInstance() throws SQLException, ClassNotFoundException {
        if(dbConnection == null) dbConnection = new DbConnection();
        return dbConnection ;
    }
    public Connection getConnection(){return connection;}

}
