package Glyvia.Glyvia.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity(name="REFEICAO")
@Table(name="REFEICAO")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Setter
@Getter
public class Refeicao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRefeicao;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_glicemia")
    private Glicemia glicemia;

    @Lob
    @Column(name = "foto", nullable = false)
    private byte[] foto;

    @Column(name = "descricao", length = 100, nullable = false)
    private String descricao;

    @Column(name = "calorias", length = 100, nullable = false)
    private Double calorias;

    @Column(name = "carboidratos", length = 100, nullable = false)
    private Double carboidratos;

    @Column(name = "insulina", length = 100, nullable = false)
    private Double insulina;

    @Column(name = "dataRefeicao", length = 100, nullable = false)
    private LocalDate dataRefeicao;

    @Column(name = "horaRefeicao", nullable = false)
    private LocalTime horaRefeicao;

}
