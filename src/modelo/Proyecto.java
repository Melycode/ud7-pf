package modelo;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Proyecto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String nombre;
    @ManyToMany
    private List<Desarrollador> desarrolladores = new ArrayList<>();
    private double presupuesto;
    private String lenguajePrincipal;

    public Proyecto(String nombre, double presupuesto, String lenguajePrincipal) {
        this.nombre = nombre;
        this.presupuesto = presupuesto;
        this.lenguajePrincipal = lenguajePrincipal;
    }

    public Proyecto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Desarrollador> getDesarrolladores() {
        return desarrolladores;
    }

    public void setDesarrolladores(List<Desarrollador> desarrolladores) {
        this.desarrolladores = desarrolladores;
    }

    public double getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(double presupuesto) {
        this.presupuesto = presupuesto;
    }

    public String getLenguajePrincipal() {
        return lenguajePrincipal;
    }

    public void setLenguajePrincipal(String lenguajePrincipal) {
        this.lenguajePrincipal = lenguajePrincipal;
    }

    @Override
    public String toString() {
        return "Proyecto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", presupuesto=" + presupuesto +
                ", lenguajePrincipal='" + lenguajePrincipal + '\'' +
                '}' + "\n";
    }
}
