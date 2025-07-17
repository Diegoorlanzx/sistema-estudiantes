package org.example.datos;


import org.example.dominio.Estudiante;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static org.example.conexion.Conexion.getConexion;


public class EstudianteDao {
    public List<Estudiante> listar(){
        List<Estudiante> estudiantes = new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs;
        Connection con = getConexion();
        String sql = "SELECT * FROM estudiante ORDER BY id_estudiante";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                var estudiante = new Estudiante();
                estudiante.setIdEstudiante(rs.getInt("id_estudiante"));
                estudiante.setNombre(rs.getString("nombre"));
                estudiante.setApellido(rs.getString("apellido"));
                estudiante.setTelefono(rs.getString("telefono"));
                estudiante.setEmail(rs.getString("email"));
                estudiantes.add(estudiante);
            }
        } catch (Exception e){
            System.out.println("Ocurrio un error al seleccionar datos : " + e.getMessage());
        }
        finally {
            try {
                con.close();
            } catch (Exception e){
                System.out.println("Ocurrio un error al cerrar coneccion : " + e.getMessage());
            }
        }
        return estudiantes;
    }
    public boolean buscarEstudiantePorId(Estudiante estudiante){
        PreparedStatement ps;
        ResultSet rs;
        Connection con = getConexion();
        String sql = "SELECT * FROM estudiante  WHERE id_estudiante  = ?";
        try {
            ps  = con.prepareStatement(sql);
            ps.setInt(1,estudiante.getIdEstudiante());
            rs = ps.executeQuery();
            if(rs.next()){
                estudiante.setNombre(rs.getString("nombre"));
                estudiante.setApellido(rs.getString("apellido"));
                estudiante.setTelefono(rs.getString("telefono"));
                estudiante.setEmail(rs.getString("email"));
                return true;
            }
        } catch (Exception e ){
            System.out.println("Ocurrio un error al buscar estudiante : " + e.getMessage());
        }
        finally {
            try {
                con.close();
            }catch (Exception e){
                System.out.println("Ocurrio un error al cerrar coneccion : " + e.getMessage());
            }
        }
        return false;

    }

    public  boolean agregarEstudiante(Estudiante estudiante){
        PreparedStatement ps;
        Connection con = getConexion();
        String sql = "INSERT INTO estudiante(nombre, apellido, telefono, email)" +
                "VALUES(?,?,?,?)";

        try {
           ps  = con.prepareStatement(sql);
           ps.setString(1,estudiante.getNombre());
           ps.setString(2,estudiante.getApellido());
           ps.setString(3,estudiante.getTelefono());
           ps.setString(4,estudiante.getEmail());
           ps.execute();
            return true;
        }catch (Exception e){
            System.out.println("error al agregar un estudiante" + e.getMessage());
        }
        finally {
            try {
                con.close();
            }catch (Exception e){
                System.out.println("Ocurrio un error al cerrar coneccion : " + e.getMessage());
            }
            return false;
        }

    }
    public  boolean modificar(Estudiante estudiante){
        PreparedStatement ps;
        Connection con = getConexion();
        String sql = "UPDATE estudiante SET nombre=?, apellido=?, telefono=?, email=?" +
                "WHERE id_estudiante = ?";

        try {
            ps  = con.prepareStatement(sql);
            ps.setString(1,estudiante.getNombre());
            ps.setString(2,estudiante.getApellido());
            ps.setString(3,estudiante.getTelefono());
            ps.setString(4,estudiante.getEmail());
            ps.setInt(5,estudiante.getIdEstudiante());
            ps.execute();

            return true;
        }catch (Exception e){
            System.out.println("error al agregar un modificar" + e.getMessage());
        }
        finally {
            try {
                con.close();
            }catch (Exception e){
                System.out.println("Ocurrio un error al cerrar coneccion : " + e.getMessage());
            }

        }
        return false;
    }

    public  boolean eliminar(Estudiante estudiante){
        PreparedStatement ps;
        Connection con = getConexion();
        String sql = "DELETE FROM estudiante  WHERE id_estudiante  = ?";
        try {
            ps  = con.prepareStatement(sql);

            ps.setInt(1,estudiante.getIdEstudiante());
            ps.execute();

            return true;
        }catch (Exception e){
            System.out.println("error al agregar un modificar" + e.getMessage());
        }
        finally {
            try {
                con.close();
            }catch (Exception e){
                System.out.println("Ocurrio un error al cerrar coneccion : " + e.getMessage());
            }

        }
        return false;
    }




    public static void main(String[] args) {
        var estudianteDao = new EstudianteDao();
        /*var nuevoEstudiante = new Estudiante("Carlos", "lara",
                "3057618598","carl@gamil.com");

        var agregado =estudianteDao.agregarEstudiante(nuevoEstudiante);
        if (agregado)
            System.out.println("estudiante agregardo " + nuevoEstudiante);
        else
            System.out.println(" no se agrego el estudiante " + nuevoEstudiante);*/

       /* var estudianteModificar =  new  Estudiante(1,"Steban","Parada",
                "515151","steban@gmail.com");
        var modificado = estudianteDao.modificar(estudianteModificar);
        if(modificado)
            System.out.println("estudiante modificado " + estudianteModificar);
        else
            System.out.println("No se pudo modificar estudiante " + estudianteModificar);*/

        var estudianteEliminar = new Estudiante(4);
        var eliminado = estudianteDao.eliminar(estudianteEliminar);
        if (eliminado)
            System.out.println("se elimino el estudiante " + estudianteEliminar);
        else
            System.out.println("no se elimino al estudiante " + estudianteEliminar);


        System.out.println("listado estudiantes ");
        List<Estudiante> estudiantes = estudianteDao.listar();
        estudiantes.forEach(System.out::println);

        var estudiante1 = new Estudiante();
        System.out.println("estudiante antes de la busqueda " + estudiante1);
        var encontrado = estudianteDao.buscarEstudiantePorId(estudiante1);
        if(encontrado)
            System.out.println("estudiante encontrado " + estudiante1);
        else
            System.out.println("no se encontro estudiante " + estudiante1.getIdEstudiante());


    }



}


