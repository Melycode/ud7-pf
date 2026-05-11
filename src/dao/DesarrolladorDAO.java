package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import modelo.Desarrollador;
import modelo.Proyecto;

import java.util.ArrayList;
import java.util.List;

public class DesarrolladorDAO {
    private EntityManagerFactory emf;

    public DesarrolladorDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public void insertarDesarrollador(Desarrollador d) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(d);
        em.getTransaction().commit();
        em.close();
    }

    public void actualizarDesarrollador(int id, String nombre) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Desarrollador d = em.find(Desarrollador.class,id);
        if (d != null) {
            d.setNombre(nombre);
        }
        em.getTransaction().commit();
        em.close();
    }

    public void borrarDesarrollador(int id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Desarrollador d = em.find(Desarrollador.class, id);
        if (d != null) {
            em.remove(d);
        }
        em.getTransaction().commit();
        em.close();
    }

    public void asignarDesarrollador(int idDesarrollador, int idProyecto) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Desarrollador d = em.find(Desarrollador.class, idDesarrollador);
        Proyecto p = em.find(Proyecto.class, idProyecto);
        if (d != null && p != null) {
            p.getDesarrolladores().add(d);
            d.getProyectos().add(p);
        }
        em.getTransaction().commit();
        em.close();
    }

    public void borrarDesarrolladorDeProyecto(int idDesarrollador, int idProyecto) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Desarrollador d = em.find(Desarrollador.class, idDesarrollador);
        Proyecto p = em.find(Proyecto.class, idProyecto);
        if (d != null && p != null) {
            d.getProyectos().remove(p);
            p.getDesarrolladores().remove(d);
        }
        em.getTransaction().commit();
        em.close();
    }

    public List<Proyecto> obtenerProyectoDeDesarrollador(int idDesarrollador) {
        EntityManager em = emf.createEntityManager();
        Desarrollador d = em.find(Desarrollador.class, idDesarrollador);
        List<Proyecto> p = d.getProyectos();
        p.toString();
        em.close();
        return p;
    }

    public double obtenerMediaExperienciaAnios() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Double> query = em.createQuery("select avg(d.anyosExperiencia) from Desarrollador d", Double.class);
        double media = query.getSingleResult();
        em.close();
        return media;
    }

    public List<Desarrollador> obtenerDesarrolladorSinProyecto() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Desarrollador> query = em.createQuery("select d from Desarrollador d", Desarrollador.class);
        List<Desarrollador> todos = query.getResultList();
        List<Desarrollador> result = new ArrayList<>();
        for (Desarrollador d : todos) {
            if (d.getProyectos().isEmpty()) {
                result.add(d);
            }
        }
        em.close();
        return result;
    }


}
