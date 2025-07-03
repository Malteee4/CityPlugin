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
            con.prepareStatement("CREATE TABLE IF NOT EXISTS tbl_players(PLAYER_ID varchar(40), MONEY int, JOB varchar(20), RANK varchar(20))").execute();
            //con.prepareStatement(CREATE TABLE IF NOT EXISTS tbl_jobs(PLAYER_ID varchar(40), LUMBERJACK_EXP int, FISHER_EXP int, HUNTER_EXP int, BUILDER_EXP int, MINER_EXP int, TRADER_EXP int)).execute();
            con.prepareStatement("CREATE TABLE IF NOT EXISTS tbl_player_stats(PLAYER_ID varchar(40), BLOCK_BREAK int, BLOCK_PLACE int, ENTITY_KILL int, PLAYER_KILL int, DEATHS int, DISTANCE int)").execute();
            con.prepareStatement("CREATE TABLE IF NOT EXISTS tbl_areas(AREA_ID varchar(20), TYPE varchar(20), LOC1 varchar(30), LOC2 varchar(30))");
            //con.prepareStatement(CREATE TABLE IF NOT EXISTS tbl_plots()).execute();
            //con.prepareStatement(CREATE TABLE IF NOT EXISTS tbl_gs()).execute();
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
