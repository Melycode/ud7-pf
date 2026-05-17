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

    public void actualizarDesarrollador(int id, String nombre, int anyosExperiencia, double salario) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Desarrollador d = em.find(Desarrollador.class,id);
        if (d != null) {
            d.setNombre(nombre);
            d.setAnyosExperiencia(anyosExperiencia);
            d.setSalario(salario);
        }
        em.getTransaction().commit();
        em.close();
    }

    public void borrarDesarrollador(int id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Desarrollador encontrado = em.find(Desarrollador.class, id);
        if (encontrado != null) {
            em.remove(encontrado);
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
            d.getProyectos().add(p);
            p.getDesarrolladores().add(d);
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

    public List<Proyecto> obtenerProyectoDeUnDesarrollador(int idDesarrollador) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Proyecto> query = em.createQuery(
                "select p from Desarrollador d join d.proyectos p where d.id = :id",
                Proyecto.class
        );
        query.setParameter("id", idDesarrollador);
        List<Proyecto> lista = query.getResultList();
        em.close();
        return lista;
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
        TypedQuery<Desarrollador> query = em.createQuery("select d from Desarrollador d left join d.proyectos p group by d having count(p) = 0", Desarrollador.class);
        List<Desarrollador> desarrolladores = query.getResultList();
        em.close();
        return desarrolladores;
    }


}
