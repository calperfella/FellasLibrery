import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Transaccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTransaccion;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_libro", nullable = false)
    private Libro libro;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Tipo tipo;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private java.sql.Timestamp fechaTransaccion;

    @Column(columnDefinition = "DECIMAL(10, 2) NOT NULL")
    private Double cantidadFlcoin;

    private Date fechaDevolucion;

    public enum Tipo {
        COMPRA, ALQUILER
    }

    // Getters y setters...
}
