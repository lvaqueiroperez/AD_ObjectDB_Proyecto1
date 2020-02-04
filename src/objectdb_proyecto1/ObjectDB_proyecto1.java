package objectdb_proyecto1;

import javax.persistence.*;
import java.util.*;

//SI ALGUNA BD SE NOS CORROMPE, ELIMINARLA DIRECTAMENTE EN LA CARPETA DONDE ESTÉ ALMACENADA Y 
//VOLVER A CREARLA
//OJO!!! CADA VEZ QUE SE MODIFIQUE UN DATO, TENEMOS QUE HACER EL COMMIT !!!  
//OJO!!! SI VEMOS QUE NO PODEMOS COMPROBAR UNA MODIFICACIÓN HECHA, CREAR UN NUEVO ENTITYMANAGER 
//Y UN QUERY DE SELECT NUEVO !!!
public class ObjectDB_proyecto1 {

    public static void main(String[] args) {

        Ejercicios1.almacenarPuntos();
        Ejercicios1.mostrarPuntos();
        Ejercicios1.mostrarPunto();
        Ejercicios1.actualizarPunto();
        Ejercicios1.mostrarPuntos();
        Ejercicios1.eliminarPunto();
        Ejercicios1.mostrarPuntos();
        Ejercicios1.emf.close();
        //EJERCICIOS INICIALES:
//        // Open a database connection
//        // (create a new database if it doesn't exist yet):
//        EntityManagerFactory emf
//                = Persistence.createEntityManagerFactory("$objectdb/db/p2.odb");
//        EntityManager em = emf.createEntityManager();
//
//        // Close the database connection:
//        // Store 1000 Point objects in the database:
//        em.getTransaction().begin();
//        for (int i = 0; i < 10; i++) {
//            Point p = new Point(i, i);
//            em.persist(p);
//        }
//        em.getTransaction().commit();
//        em.flush();
//        em.clear();
//
//        // Find the number of Point objects in the database:
//        Query q1 = em.createQuery("SELECT COUNT(p) FROM Point p");
//        System.out.println("Total Points: " + q1.getSingleResult());
//
//        // Find the average X value:
//        Query q2 = em.createQuery("SELECT AVG(p.x) FROM Point p");
//        System.out.println("Average X: " + q2.getSingleResult());
//
//        // Retrieve all the Point objects from the database:
//        TypedQuery<Point> query
//                = em.createQuery("SELECT p FROM Point p", Point.class);
//        List<Point> results = query.getResultList();
//        for (Point p : results) {
//            System.out.println(p);
//        }
//
//        //SELECCIONAR UN OBJETO EN CONCRETO (DARÁ ERROR SI NO EXISTE, VER EL EXPLORER!!!)
//        //USAMOS "getSIngleResult" CUANDO SABEMOS QUE SOLO VAMOS A MOSTRAR UN RESULTADO (EN ESTE CASO UN OBJETO)
//        //
//        System.out.println("SELECCIONAR UN OBJETO CONCRETO: ");
//
//        Query q3 = em.createQuery("select p from Point as p where p.id = 3");
//        System.out.println(q3.getSingleResult());
//
//        //MODIFICAR UN OBJETO:
//        //PRIMERO LO SELECCIONAMOS Y MOSTRAMOS:
//        em.getTransaction().begin();
//        System.out.println("SELECCIONAR Y MODIFICAR UN OBJETO CONCRETO: ");
//        Query q4 = em.createQuery("select p from Point as p where p.id = 5");
//        System.out.println(q4.getSingleResult());
//        //LO ALMACENAMOS EN UNA VARIABLE DE LA CLASE PORQUE ES UN OBJETO !!!(AÑADIMOS SETTERS EN LA CLASE !!!)
//        Point punto = (Point) q4.getSingleResult();
//        //LO MODIFICAMOS COMO UN OBJETO DE JAVA
//        punto.setX(999);
//
//        //COMPROBAMOS:
//        System.out.println(q4.getSingleResult());
//
//        em.getTransaction().commit();
//        em.flush();
//
//        //MODIFICACIÓN MASIVA DE TODOS OBJETOS:
//        //MODIFICACIÓN DE VARIOS OBJETOS:
//        System.out.println("MODIFICAR VARIOS OBJETOS A LA VEZ:");
//        //OJO!! CUANDO SOLO MODIFICAMOS UN OBJETO NO HACE FALTA HACER UN UPDATE, CON
//        //UN SELECT Y GUARDAR EN UN OBJETO EL RESULTADO LLEGA
//        //PERO EN ESTE CASO, COMO SON VARIOS, ES MEJOR USAR UN UPDATE:
//        //
//        //OJO!!! SI DA TRANSACTION ERROR, PONER LA TRANSACTION Y EL COMMIT 
//        em.getTransaction().begin();
//        Query q5 = em.createQuery("update Point c set c.y = 6");
//        q5.executeUpdate();
//        //VER COMO HACER CON UNA VARIABLE         
//        //int cont = q5.setParameter("valor", 999).executeUpdate();
//
//        //PODEMOS COMPROBARLO EN EL EXPLORADOR DE OBJECT BD TAMBIÉN
////        System.out.println("SE HAN ACTUALIZADO: " + cont + "FILAS");
//        //(para comprobar copiamos y pegamos el for del ejemplo)
//        em.getTransaction().commit();
//        em.flush();
//
//        System.out.println("COMPROBAMOS:");
//        EntityManager em2 = emf.createEntityManager();
//
//        TypedQuery<Point> query2
//                = em2.createQuery("SELECT p FROM Point p", Point.class);
//        List<Point> results2 = query2.getResultList();
//
//        for (Point p : results2) {
//            System.out.println(p);
//        }
//
//        //
//        //ELIMINACIÓN DE UN OBJETO EN CONCRETO:
//        System.out.println("ELIMINAR UN OBJETO EN CONCRETO: ");
//        //TENEMOS QUE SELECCIONARLO, METERLO EN UN OBJETO, Y USAR EL MÉTODO REMOVE
//        Query q6 = em.createQuery("select p from Point as p where p.id = 7");
//        System.out.println("SELECCIONADO: " + q6.getSingleResult());
//        System.out.println("BORRANDO");
//        Point puntoBorra = (Point) q6.getSingleResult();
//        em.getTransaction().begin();
//        em.remove(puntoBorra);
//        em.getTransaction().commit();
//        System.out.println("COMPROBAMOS: ");
//
//        EntityManager em3 = emf.createEntityManager();
//
//        TypedQuery<Point> query3
//                = em3.createQuery("SELECT p FROM Point p", Point.class);
//        List<Point> results3 = query3.getResultList();
//
//        for (Point p : results3) {
//            System.out.println(p);
//        }
//
//        //ELIMINACIÓN DE VARIOS OBJETOS:
//        //OJO! SE ELIMINAN AQUÍ, EN LOCAL, PERO NO EN LA VERDADERA BD
//        //POR ESO PARECE QUE ESTÁN ELIMINADOS PERO EN CAMBIO SI VOLVEMOS A CREARLOS
//        //EL ID NO EMPIEZA EN 0, SINO EN EL ÚLTIMO NÚMERO DE LOS BORRADOS
//        //PARA ELLO, EN CADA EJECUCIÓN BORRAR LA BD
//        System.out.println("BORRADO DE TODA LA BD:");
//        em.getTransaction().begin();
//        Query q7 = em.createQuery("delete from Point");
//        q7.executeUpdate();
//        em.getTransaction().commit();
//
//        System.out.println("COMPROBAMOS: ");
//
//        EntityManager em4 = emf.createEntityManager();
//
//        TypedQuery<Point> query4
//                = em4.createQuery("SELECT p FROM Point p", Point.class);
//        List<Point> results4 = query4.getResultList();
//
//        for (Point p : results4) {
//            System.out.println(p);
//        }
//
//        em2.close();
//        em.close();
//        emf.close();
//        //EN PRÓXIMOS EJERCICIOS, MEJOR HACER MÉTODOS, AHORRAMOS CÓDIGO
    }

}
