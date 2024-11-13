import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "libreria")
public class Libreria implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idLibreria;

    @Column(nullable = false)
    private String nombreLibreria;

    @Column(nullable = false)
    private String direccion;

    @Column
    private String telefono;

    @Column
    private Double latitud;

    @Column
    private Double longitud;

    // Getters y setters...
    public Integer getIdLibreria() {
        return idLibreria;
    }

    public void setIdLibreria(Integer idLibreria) {
        this.idLibreria = idLibreria;
    }

    public String getNombreLibreria() {
        return nombreLibreria;
    }

    public void setNombreLibreria(String nombreLibreria) {
        this.nombreLibreria = nombreLibreria;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }
}
