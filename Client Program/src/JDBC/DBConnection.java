package JDBC;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnection {
    private static Connection instance = null;

    private final String port = "3300";
    private final String databasename = "cab302";
    private final String user= "root";
    private final String userp = "root";

    private DBConnection() {
        try {
            instance = DriverManager.getConnection("jdbc:mariadb://localhost:"+port+"/"+databasename,user,userp);

        } catch (SQLException var7) {
        }
    }

    public static Connection getInstance() {
        if (instance == null) {
            new DBConnection();
        }

        return instance;
    }
}

