import client.guiClass;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException{
        Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/cab302","root","root");


        guiClass s = new guiClass();

        connection.close();
        s.runGUI();
    }
}