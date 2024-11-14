import jakarta.persistence.*;

@Entity
public class HistorialSaldo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idHistorial;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @Column(columnDefinition = "DECIMAL(10, 2) NOT NULL")
    private Double cambioSaldo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Tipo tipo;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private java.sql.Timestamp fecha;

    public enum Tipo {
        RECARGA, COMPRA, ALQUILER
    }

    // Getters y setters...
}
