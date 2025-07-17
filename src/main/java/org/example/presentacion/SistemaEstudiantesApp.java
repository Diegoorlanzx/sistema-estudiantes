package org.example.presentacion;

import org.example.datos.EstudianteDao;
import org.example.dominio.Estudiante;

import java.util.Scanner;

public class SistemaEstudiantesApp {
    public static void main(String[] args) {
        var salir = false;
        var consola = new Scanner(System.in);

        var estudianteDao= new EstudianteDao();
        while (!salir){
            try {
                mostrarMenu();
                salir = ejecutarOpciones(consola,estudianteDao);
            }catch (Exception e){
                System.out.println("Ocurrio un error al ejecutar operacion " + e);
            }
            System.out.println();
        }
    }

    private static void  mostrarMenu(){
        System.out.println("""
                *** Sistema estudiantes 
                1 Listar estudiantes
                2 Buscar estudiane
                3 Agregar estudiante
                4 Modificar estudiante
                5 Eliminar estudiante
                6 salir
                Elige una opcion
                """);
    }
    private  static boolean ejecutarOpciones(Scanner consola ,EstudianteDao estudianteDao){

        var opcion = Integer.parseInt(consola.nextLine());
        var   salir = false;
        switch (opcion){
            case 1 -> {
                System.out.println("Listado de estudiantes ...");
                var estudiantes = estudianteDao.listar();
                estudiantes.forEach(System.out::println);
            }
            case 2 -> {
                System.out.println("Intruduce el id_estudiante a buscar");
                var idestudiante = Integer.parseInt(consola.nextLine());
                var estudiante = new Estudiante(idestudiante);
                var encontrado = estudianteDao.buscarEstudiantePorId(estudiante);
                if(encontrado)
                    System.out.println("estudiante encontrado " + estudiante);
                else
                    System.out.println("no se encontro estudiante " + estudiante.getIdEstudiante());
            }
            case 3 -> {


                System.out.println("Agregar estudiante : ");
                System.out.println("Nombre: ");
                var nombre = consola.nextLine();
                System.out.println("Apellido: ");
                var apellido = consola.nextLine();
                System.out.println("Telefono: ");
                var telefono = consola.nextLine();
                System.out.println("email: ");
                var email = consola.nextLine();

                var nuevoEstudiante = new Estudiante(nombre,apellido,telefono,email);

                var agregado =estudianteDao.agregarEstudiante(nuevoEstudiante);
                if (agregado)
                    System.out.println("estudiante agregardo " + nuevoEstudiante);
                else
                    System.out.println(" no se agrego el estudiante " + nuevoEstudiante);
            }
            case 4 -> {
                System.out.println("Modificar  estudiante: ");
                System.out.println("Id Estuante: ");
                var idEstudiante = Integer.parseInt(consola.nextLine());
                System.out.println("Nombre: ");
                var nombre = consola.nextLine();
                System.out.println("Apellido: ");
                var apellido = consola.nextLine();
                System.out.println("Telefono: ");
                var telefono = consola.nextLine();
                System.out.println("email: ");
                var email = consola.nextLine();
                var estudiante = new Estudiante(idEstudiante, nombre, apellido,telefono,email);
                var modificado = estudianteDao.modificar(estudiante);
                if(modificado)
                    System.out.println("estudiante modificado " + estudiante);
                else
                    System.out.println("No se pudo modificar estudiante " + estudiante);
            }
            case 5 -> {
                System.out.println("Eliminar Estudiante: ");
                System.out.println("Id Estudiante: ");
                var idEstudiante = Integer.parseInt((consola.nextLine()));

                var estudianteEliminar = new Estudiante(idEstudiante);
                var eliminado = estudianteDao.eliminar(estudianteEliminar);
                if (eliminado)
                    System.out.println("se elimino el estudiante " + estudianteEliminar);
                else
                    System.out.println("no se elimino al estudiante " + estudianteEliminar);
            }
            case 6 -> {
                System.out.println("hasta pronto");
                salir = true;
            }
            default -> System.out.println("Opcion no valida");
        }
        return salir;

    }
}