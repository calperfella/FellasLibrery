import jakarta.persistence.*;

@Entity
public class SolicitudUsuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSolicitud;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Tipo tipo;

    @Column(nullable = false)
    private String descripcion;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private java.sql.Timestamp fecha;

    @Enumerated(EnumType.STRING)
    private Estado estado = Estado.PENDIENTE;

    public enum Tipo {
        REPORTE, SOLICITUD
    }

    public enum Estado {
        PENDIENTE, EN_PROCESO, RESUELTO
    }

    // Getters y setters...
}
