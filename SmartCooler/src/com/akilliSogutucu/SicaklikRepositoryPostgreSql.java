package com.smartCooler;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TemperatureRepositoryPostgreSql implements ITemperatureRepository {

    @Override
    public void update(int temperature) {
        this.save(temperature);
    }

    private Connection connect() {

        Connection conn = null;

        try {
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/smartCooler",
                    "postgres", "projepasswordsi");
            if (conn == null)
                System.out.println("Sıcaklik server bağlantı girişimi başarısız!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    private void save(int temperature) {
        String sql = "INSERT INTO  \"Temperature\" (\"temperature\",\"saat\") " +
                "VALUES(\'" + temperature + "\',\'" + new Timestamp(System.currentTimeMillis()) + "\')";

        Connection conn = this.connect();

        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            //***** CONNECT FINISH *****
            conn.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public int endTemperatureBring() {
        String sql = "SELECT \"temperature\" FROM \"Temperature\" ORDER BY \"saat\" DESC LIMIT 1";
        int temperature = 9999;

        Connection conn = this.connect();

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            //***** CONNECT FINISH *****
            conn.close();

            while (rs.next()) {
                temperature = rs.getInt("temperature");
            }

            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return temperature;
    }
}