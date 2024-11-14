import jakarta.persistence.*;
import java.util.List;

@Entity
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String autor;

    @Column(name = "año_publicacion", nullable = false)
    private Integer anoPublicacion; // Renamed to avoid special character

    private String categoria;

    @Column(unique = true)
    private String isbn;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Formato formato;

    @Column(columnDefinition = "DECIMAL(10, 2) NOT NULL")
    private Double precioFlcoin;

    @Column(columnDefinition = "INT DEFAULT 0")
    private Integer stock;

    private String ubicacion;

    // Relaciones
    @OneToMany(mappedBy = "libro")
    private List<Transaccion> transacciones;

    public enum Formato {
        FISICO, VIRTUAL, AUDIOLIBRO
    }

    // Getters y setters...
}
