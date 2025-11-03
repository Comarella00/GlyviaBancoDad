package Glyvia.Glyvia.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity(name="GLICEMIA")
@Table(name="GLICEMIA")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Glicemia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_glicemia")
    private Long idGlicemia;

    @Column(name = "valor_glicemia", nullable = false)
    private Double valorGlicemia;

    @Column(name = "data_glicemia", nullable = false)
    private LocalDate dataGlicemia;

    @Column(name = "hora_glicemia", nullable = false)
    private LocalTime horaGlicemia;

    @Column(name = "checagem_glicemia")
    private Double checagemGlicemia;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;
}
