package objectdb_proyecto1;

import javax.persistence.*;
import java.util.*;

//SI ALGUNA BD SE NOS CORROMPE, ELIMINARLA DIRECTAMENTE EN LA CARPETA DONDE ESTÉ ALMACENADA Y 
//VOLVER A CREARLA
public class ObjectDB_proyecto1 {

    public static void main(String[] args) {
        // Open a database connection
        // (create a new database if it doesn't exist yet):
        EntityManagerFactory emf
                = Persistence.createEntityManagerFactory("$objectdb/db/p2.odb");
        EntityManager em = emf.createEntityManager();

        // Store 1000 Point objects in the database:
        em.getTransaction().begin();
        for (int i = 0; i < 1000; i++) {
            Point p = new Point(i, i);
            em.persist(p);
        }
        em.getTransaction().commit();

        // Find the number of Point objects in the database:
        Query q1 = em.createQuery("SELECT COUNT(p) FROM Point p");
        System.out.println("Total Points: " + q1.getSingleResult());

        // Find the average X value:
        Query q2 = em.createQuery("SELECT AVG(p.x) FROM Point p");
        System.out.println("Average X: " + q2.getSingleResult());

        // Retrieve all the Point objects from the database:
        TypedQuery<Point> query
                = em.createQuery("SELECT p FROM Point p", Point.class);
        List<Point> results = query.getResultList();
        for (Point p : results) {
            System.out.println(p);
        }

        //SELECCIONAR UN OBJETO EN CONCRETO
        //USAMOS "getSIngleResult" CUANDO SABEMOS QUE SOLO VAMOS A MOSTRAR UN RESULTADO (EN ESTE CASO UN OBJETO)
        //
        System.out.println("SELECCIONAR UN OBJETO CONCRETO: ");

        Query q3 = em.createQuery("select p from Point as p where p.id = 3");
        System.out.println(q3.getSingleResult());

        //MODIFICAR UN OBJETO:
        //PRIMERO LO SELECCIONAMOS Y MOSTRAMOS:
        System.out.println("SELECCIONAR Y MODIFICAR UN OBJETO CONCRETO: ");
        Query q4 = em.createQuery("select p from Point as p where p.id = 5");
        System.out.println(q4.getSingleResult());
        //LO ALMACENAMOS EN UNA VARIABLE DE LA CLASE PORQUE ES UN OBJETO !!!(AÑADIMOS SETTERS EN LA CLASE !!!)
        Point punto = (Point) q4.getSingleResult();
        //LO MODIFICAMOS COMO UN OBJETO DE JAVA
        punto.setX(999);

        //COMPROBAMOS:
        System.out.println(q4.getSingleResult());

        //MODIFICACIÓN MASIVA DE TODOS OBJETOS:
        //MODIFICACIÓN DE VARIOS OBJETOS:
        // Close the database connection:
        em.close();
        emf.close();
    }
}
