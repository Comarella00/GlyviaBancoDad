package Glyvia.Glyvia.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    private Long idGlicemia;

    @NotNull(message = "Obrigatório o campo valor da glicemia!")
    @Column(name = "valorGlicemia", length = 100, nullable = false)
    private Double valorGlicemia;

    @NotNull(message = "Obrigatório o campo data do registro da glicemia!")
    @Column(name = "dataGlicemia", length = 100, nullable = false)
    private LocalDate dataGlicemia;

    @NotNull(message = "Obrigatório o campo hora do registro da glicemia!")
    @Column(name = "horaGlicemia", length = 100, nullable = false)
    private LocalTime horaGlicemia;

    @NotNull(message = "Obrigatório o campo valor da glicemia!")
    @Column(name = "checagemGlicemia", length = 100, nullable = false)
    private Double checagemGlicemia;
}
