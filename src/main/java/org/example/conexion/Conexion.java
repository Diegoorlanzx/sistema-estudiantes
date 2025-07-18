package org.example.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    public static   Connection getConexion(){
        Connection conexion = null;
        var baseDatos = "estudiantes_db";
        var url = "jdbc:mysql://localhost:3306/" + baseDatos;
        var usuario = "root"; // ← Reemplaza con System.getenv("DB_USER") para producción
        var password = "tu_clave"; //
         try{


         Class.forName("com.mysql.cj.jdbc.Driver");
             conexion = DriverManager.getConnection(url, usuario, password);
         } catch (ClassNotFoundException | SQLException e){
             System.out.println("ocurrio un error en la conexion :" + e.getMessage());

         }
        return conexion;
    }
    

    public static void main(String[] args) {
        var conexion = Conexion.getConexion();
        if (conexion != null)
            System.out.println("Conexion exitosa : " + conexion);
        else
            System.out.println("Error al conectarse");
    }

}
