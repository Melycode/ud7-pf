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
        //proyectoDAO.actualizarProyecto(21, "App Promo");
        //proyectoDAO.borrarProyecto(21);



        DesarrolladorDAO desarrolladorDAO = new DesarrolladorDAO(emf);
        //desarrolladorDAO.insertarDesarrollador(new Desarrollador("Milan Gutiérrez", 2, 28.0));
        //desarrolladorDAO.actualizarDesarrollador(22, "Adrián Gutiérrez");
        // desarrolladorDAO.borrarDesarrollador(22);
        // desarrolladorDAO.asignarDesarrollador(11, 8);



        emf.close();
    }
}
