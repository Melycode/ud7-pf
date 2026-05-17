package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import modelo.Desarrollador;
import modelo.Proyecto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProyectoDAO {
    private EntityManagerFactory emf;

    public ProyectoDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public void insertarProyecto(Proyecto p) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(p);
        em.getTransaction().commit();
        em.close();
    }

    public void actualizarProyecto(int id, String nombre, double presupuesto, String lenguajePrincipal) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Proyecto p = em.find(Proyecto.class,id);
        if (p != null) {
            p.setNombre(nombre);
            p.setPresupuesto(presupuesto);
            p.setLenguajePrincipal(lenguajePrincipal);
        }
        em.getTransaction().commit();
        em.close();
    }

    public void borrarProyecto(int id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Proyecto p = em.find(Proyecto.class, id);
        if (p != null) {
            em.remove(p);
        }
        em.getTransaction().commit();
        em.close();
    }

    public List<Desarrollador> obtenerDesarrolladoresDeCadaProyecto(int id) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Desarrollador> query = em.createQuery(
                "select d from Proyecto p join p.desarrolladores d where p.id = :id",
                Desarrollador.class
        );
        query.setParameter("id", id);
        List<Desarrollador> lista = query.getResultList();
        em.close();
        return lista;
    }

    public Map<String, Long> obtenerTodosDesarrolladores() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Object[]> query = em.createQuery("select p.id, p.nombre, count(distinct d) from Proyecto p left join p.desarrolladores d group by p.id, p.nombre", Object[].class);
        Map<String, Long> lista = new HashMap<>();
        for (Object[] obj : query.getResultList()) {
            String nombre = (String) obj[1];
            Long cantidad = (Long) obj[2];
            lista.put(nombre, cantidad);
        }
        em.close();
        return lista;

    }

    public List<Proyecto> obtenerProyectoConMasDe5() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Proyecto> query = em.createQuery(
                "select p from Proyecto p where size(p.desarrolladores) > 5", Proyecto.class);
        List<Proyecto> lista = query.getResultList();
        em.close();
        return lista;
    }

    public List<Proyecto> obtener3ProyectosPresupuestoAlto() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Proyecto> query = em.createQuery("select p from Proyecto p order by p.presupuesto desc", Proyecto.class);
        query.setMaxResults(3);
        List<Proyecto> lista = query.getResultList();
        em.close();
        return lista;
    }

    public Proyecto obtenerProyectoConLenguajePrincipal(String lenguajePrincipal) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Proyecto> query = em.createQuery("select p from Proyecto p where p.lenguajePrincipal = :lenguaje " +
                "order by p.presupuesto asc", Proyecto.class);
        query.setParameter("lenguaje", lenguajePrincipal);
        query.setMaxResults(1);
        Proyecto p = query.getSingleResult();
        em.close();
        return p;
    }

}
