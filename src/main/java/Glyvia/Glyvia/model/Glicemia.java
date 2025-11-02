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
    private Long idGlicemia;

    //Na aba de adicionar glicemia
    @Column(name = "valorGlicemia", length = 100, nullable = false)
    private Double valorGlicemia;

    @Column(name = "dataGlicemia", length = 100, nullable = false)
    private LocalDate dataGlicemia;

    @Column(name = "horaGlicemia", length = 100, nullable = false)
    private LocalTime horaGlicemia;


    //Na aba de alerta
    @Column(name = "checagemGlicemia", length = 100, nullable = false)
    private Double checagemGlicemia;


    // 🔗 Relacionamento com o usuário
    @ManyToOne
    @JoinColumn(name = "id", nullable = false)
    private Usuario usuario;
}
