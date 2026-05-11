package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import modelo.Desarrollador;
import modelo.Proyecto;

import java.util.List;

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

    public void actualizarProyecto(int id, String nombre) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Proyecto p = em.find(Proyecto.class,id);
        if (p != null) {
            p.setNombre(nombre);
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






    public List<Proyecto> obtenerProyectoConMasDe5() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Proyecto> query = em.createQuery(
                "select p from Proyecto p join p.desarrolladores d group by p having count(d) > 5",
                Proyecto.class);
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
        TypedQuery<Proyecto> query = em.createQuery("select p from Proyecto p where p.lenguajePrincipal = :lenguajePrincipal " +
                "order by p.presupuesto asc", Proyecto.class);
        query.setParameter("lenguajePrincipal", lenguajePrincipal);
        query.setMaxResults(1);
        Proyecto resultado = query.getSingleResult();
        em.close();
        return resultado;
    }

}
