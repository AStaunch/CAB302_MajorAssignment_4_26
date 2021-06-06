package JDBC;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class DBConnection {
    private static Connection instance = null;

    // Edit these strings to adjust the port, databasename, user and user password
    private String port = "3306";
    private String databasename = "cab302";
    private String user= "root";
    private String userp = "fishcake";

    private DBConnection() {
        Properties props = new Properties();
        FileInputStream in = null;

        try {
            instance = DriverManager.getConnection("jdbc:mariadb://localhost:"+port+"/"+databasename,user,userp);

        } catch (SQLException var7) {
            System.err.println(var7);
        }
    }

    public static Connection getInstance() {
        if (instance == null) {
            new DBConnection();
        }

        return instance;
    }
}

