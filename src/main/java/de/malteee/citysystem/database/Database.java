package de.malteee.citysystem.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private Connection con = null;

    public Database connect(String filename) {
        try {
            Class.forName("org.sqlite.JDBC");
            String connectionUrl ="jdbc:sqlite:" + filename;
            con = DriverManager.getConnection(connectionUrl);
            System.out.println("Sqlite connected!");
            con.prepareStatement("CREATE TABLE IF NOT EXISTS tbl_properties(CODE varchar(20), VALUE varchar(60))").execute();
            //con.prepareStatement("CREATE TABLE IF NOT EXISTS tbl_players()").execute();
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }return this;
    }
    public void disconnect() {
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getCon() {
        return con;
    }
}
