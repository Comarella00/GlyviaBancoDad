package Glyvia.Glyvia.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@Entity(name="HISTORICO")
@Table(name="HISTORICO")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Historico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idHistorico;

    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "idGlicemia")
    private Glicemia glicemia;

    @ManyToOne
    @JoinColumn(name = "idRefeicao")
    private Refeicao refeicao;

    @NotNull(message = "Obrigatório o campo data início!")
    @Column(name = "dataInicioHist", length = 100, nullable = false)
    private Date dataInicioHist;

    @NotNull(message = "Obrigatório o campo data fim!")
    @Column(name = "dataFimHist", length = 100, nullable = false)
    private Date dataFimHist;

    @NotBlank(message = "Obrigatório o campo tipo de dado!")
    @Column(name = "tipoDadoHist", length = 100, nullable = false)
    private String tipoDadoHist;

    @NotNull(message = "Obrigatório o campo valor histórico!")
    @Column(name = "valorHist", length = 100, nullable = false)
    private Double valorHist;
}
