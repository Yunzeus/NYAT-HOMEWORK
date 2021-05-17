package com.smartCooler;

import sun.security.krb5.internal.crypto.KeyUsage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PersonRepositoryPostgreSql implements IPersonRepository{

    private Connection connect() {

        Connection conn = null;

        try {
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/smartCooler",
                    "postgres", "projepasswordsi");
            if (conn != null)
                System.out.println("Veritabanına bağlandı!");
            else
                System.out.println("Bağlantı girişimi başarısız!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public User search(int id) {
        System.out.println("Kullanıcı searchnıyor...");
        User user = null;

        String sql = "SELECT *  FROM \"Users\" WHERE \"id\"=" + id;

        Connection conn = this.connect();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            //***** CONNECT FINISH *****
            conn.close();

            String name;
            String password;
            int age;

            while (rs.next()) {
                name = rs.getString("name");
                password = rs.getString("password");
                age = rs.getInt("age");

                user = new User.Builder(name, password)
                        .setId(id)
                        .setAge(age)
                        .build();
            }

            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }

    public boolean userVerify(User user) {
        System.out.println("Kullanıcı doğrulanıyor...");

        boolean result = false;

        String sql = "SELECT * FROM \"Users\" WHERE name = \'" + user.getName() +
                "\' AND password = \'" + user.getPassword() + "\'" ;

        Connection conn = this.connect();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            //***** Bağlantı sonlandırma *****
            conn.close();

            result = rs.next();

            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public List<User> allUsers() {

        System.out.println("Kullanıcılar getiriyor...");
        List<User> users = new ArrayList<User>();
        String sql = "SELECT *  FROM \"Users\"";

        Connection conn = this.connect();

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            //***** Bağlantı sonlandırma *****
            conn.close();

            int id;
            String name;
            String password;
            int age;

            while (rs.next()) {
                id = rs.getInt("id");
                name = rs.getString("name");
                password = rs.getString("password");
                age = rs.getInt("age");

                users.add(new User.Builder(name, password).setId(id).setAge(age).build());
            }

            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }


    public void save(User user) {
        String sql = "INSERT INTO  \"Users\" (\"name\",\"password\",\"age\") " +
                "VALUES(\'" + user.getName() + "\'," + user.getPassword() + "," + user.getAge() + ")";

        Connection conn = this.connect();

        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            //***** Bağlantı sonlandırma *****
            conn.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void delete(int id) {
        System.out.println("User deleteiniyor...");

        String sql = "DELETE FROM \"Users\" WHERE \"id\"=" + id;

        Connection conn = this.connect();
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            //***** Bağlantı sonlandırma *****
            conn.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void change(User user) {

        String sql = "UPDATE \"Users\" SET " +
                "\"name\"=" + user.getName() +
                "\"birimFiyati\"=" + user.getPassword() +
                ",\"stokMiktari\"=" + user.getAge() +
                " WHERE \"id\"=" + user.getId();

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
}