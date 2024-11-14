import jakarta.persistence.*;
import java.util.List;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuario;

    @Column(nullable = false, unique = true)
    private String nombreUsuario;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String contrasena; // Renamed field

    private String direccion;
    private String telefono;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private java.sql.Timestamp fechaRegistro;

    @Column(columnDefinition = "DECIMAL(10, 2) DEFAULT 0")
    private Double saldoFlcoin;

    // Relaciones
    @OneToMany(mappedBy = "usuario")
    private List<Transaccion> transacciones;

    @OneToMany(mappedBy = "usuario")
    private List<SolicitudUsuario> solicitudes;

    @OneToMany(mappedBy = "usuario")
    private List<HistorialSaldo> historialSaldo;

    @OneToOne(mappedBy = "usuario")
    private PreferenciaUsuario preferencia;

    // Getters y setters...
}
