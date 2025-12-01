package Glyvia.Glyvia.model;

import jakarta.persistence.*;
import lombok.*;

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

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "calorias")
    private Double calorias;

    @Column(name = "carboidratos")
    private Double carboidratos;

    @Column(name = "dataRefeicao")
    private LocalDate dataRefeicao;

    @Column(name = "horaRefeicao")
    private LocalTime horaRefeicao;

}
