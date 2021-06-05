package JDBC;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {
    private static Connection instance = null;

    private DBConnection() {
        Properties props = new Properties();
        FileInputStream in = null;

        try {
            instance = DriverManager.getConnection("jdbc:mariadb://localhost:3306/cab302","root","root");

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

