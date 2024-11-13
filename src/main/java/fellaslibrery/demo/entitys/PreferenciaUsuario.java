import jakarta.persistence.*;

@Entity
public class PreferenciaUsuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPreferencia;

    @OneToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @Enumerated(EnumType.STRING)
    private Tema tema = Tema.CLARO;

    @Enumerated(EnumType.STRING)
    private Accesibilidad accesibilidad = Accesibilidad.NINGUNA;

    private Boolean notificaciones = true;

    public enum Tema {
        CLARO, OSCURO
    }

    public enum Accesibilidad {
        NINGUNA, CONTRASTE_ALTO, LECTOR_DE_PANTALLA
    }

    // Getters y setters...
}
