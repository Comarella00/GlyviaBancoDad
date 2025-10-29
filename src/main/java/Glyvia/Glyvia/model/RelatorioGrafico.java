package Glyvia.Glyvia.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@Entity(name="RELATORIOGRAFICO")
@Table(name="RELATORIOGRAFICO")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Setter
@Getter
public class RelatorioGrafico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRelatorioGrafico;

    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;

    @NotBlank(message = "Obrigatório o campo tipo de dado!")
    @Column(name = "tipoDadoRG", length = 100, nullable = false)
    private String tipoDadoRG;

    @NotNull(message = "Obrigatório o campo data início!")
    @Column(name = "dataInicioRG", length = 100, nullable = false)
    private Date dataInicioRG;

    @NotNull(message = "Obrigatório o campo data fim!")
    @Column(name = "dataFimRG", length = 100, nullable = false)
    private Date dataFimRG;

    @NotNull(message = "Obrigatório o campo valor mínimo!")
    @Column(name = "valorMinRG", length = 100, nullable = false)
    private Double valorMinRG;

    @NotNull(message = "Obrigatório o campo valor máximo!")
    @Column(name = "valorMaxRG", length = 100, nullable = false)
    private Double valorMaxRG;

    @NotNull(message = "Obrigatório o campo valor média!")
    @Column(name = "valorMediaRG", length = 100, nullable = false)
    private Double valorMediaRG;
}
