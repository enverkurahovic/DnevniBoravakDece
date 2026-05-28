/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    public static Connection getConnection() {

        try {
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/produzeni_boravak",
                    "root",
                    ""
            );

            return con;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}