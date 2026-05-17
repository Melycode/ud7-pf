import dao.DesarrolladorDAO;
import dao.ProyectoDAO;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import modelo.Desarrollador;
import modelo.Proyecto;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("project.odb");
        ProyectoDAO proyectoDAO = new ProyectoDAO(emf);
        //proyectoDAO.insertarProyecto(new Proyecto("App Marketing", 60000.0, "Python"));
        //proyectoDAO.actualizarProyecto(23, "App Marketing Promo", 65000.0, "Python");
        //proyectoDAO.borrarProyecto(23);
        //System.out.println(proyectoDAO.obtenerDesarrolladoresDeCadaProyecto(1));
       // System.out.println(proyectoDAO.obtenerTodosDesarrolladores());
        //System.out.println(proyectoDAO.obtenerProyectoConMasDe5());
        //System.out.println(proyectoDAO.obtener3ProyectosPresupuestoAlto());
        //System.out.println(proyectoDAO.obtenerProyectoConLenguajePrincipal("Python"));


        DesarrolladorDAO desarrolladorDAO = new DesarrolladorDAO(emf);
        //desarrolladorDAO.insertarDesarrollador(new Desarrollador("Milan Gutiérrez", 2, 28.0));
        //desarrolladorDAO.actualizarDesarrollador(24, "Adrián Gutiérrez", 5, 38.0);
        // desarrolladorDAO.borrarDesarrollador(24);
        // desarrolladorDAO.asignarDesarrollador(11, 8);
        // desarrolladorDAO.borrarDesarrolladorDeProyecto(11,8);
        //System.out.println(desarrolladorDAO.obtenerProyectoDeUnDesarrollador(11));
        //System.out.println(desarrolladorDAO.obtenerMediaExperienciaAnios());
        //System.out.println(desarrolladorDAO.obtenerDesarrolladorSinProyecto());

        emf.close();
    }
}
