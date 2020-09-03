package objectdb_proyecto1;

import javax.persistence.*;
import java.util.*;
//EJERCICIOS HECHOS EN MÉTODOS
//PARA ASEGURARSE DE QUE TODO VAYA BIEN, UN MÉTODO PARA CADA EJERCICIO
//DENTRO DE CADA MÉTODO UN ENTITY NUEVO Y, SI HACE FALTA, EL BEGIN Y EL COMMIT

public class Ejercicios1 {

    public static EntityManagerFactory emf = Persistence.createEntityManagerFactory("$objectdb/db/p2.odb");

    public static void almacenarPuntos() {

        System.out.println("ALMACENAMOS PUNTOS");

        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        for (int i = 0; i < 10; i++) {
            Point p = new Point(i, i);
            em.persist(p);
        }
        em.getTransaction().commit();

        em.close();

    }

    public static void mostrarPuntos() {

        System.out.println("MOSTRAMOS PUNTOS");

        EntityManager em = emf.createEntityManager();
        //SELECCIONAMOS TODOS LOS OBJETOS DE LA BD COMO "p"
        TypedQuery<Point> query
                = em.createQuery("SELECT p FROM Point p", Point.class);
        //EN ESTA LISTA OBTENEMOS OBJETOS DE TIPO POINT !!!
        List<Point> results = query.getResultList();
        for (Point p : results) {
            System.out.println(p.getId() + "(" + p.getX() + "," + p.getY() + ")");
        }

        em.close();

    }

    public static void mostrarPunto() {

        System.out.println("MOSTRAMOS PUNTO DE ID 10");

        EntityManager em = emf.createEntityManager();

        Query q = em.createQuery("select p from Point p where p.id = :value");
        //SI SELECCIONAMOS EL OBJETO, POR DEFECTO SOLO MUESTRA LOS ATRIBUTOS, NO EL ID
        System.out.println(q.setParameter("value", 10).getSingleResult());

        em.close();

    }

    public static void actualizarPunto() {

        System.out.println("ACTUALIZAMOS PUNTO DE ID 10");

        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        Query q = em.createQuery("update Point p set p.y = (p.y+:value) where p.id=10");

        q.setParameter("value", 2).executeUpdate();

        em.getTransaction().commit();

        em.close();

    }

    public static void eliminarPunto() {
        System.out.println("ELIMINAMOS PUNTO DE ID 5");

        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        Query q = em.createQuery("select p from Point as p where p.id = 5");
        Point puntoBorra = (Point) q.getSingleResult();

        em.remove(puntoBorra);

        em.getTransaction().commit();

    }
    
    public static void actualizarMasivoSelectivo(){
        
        System.out.println("ACTUALIZAR PUNTOS CON Y <= QUE 5");
        
        
        
    }

}
