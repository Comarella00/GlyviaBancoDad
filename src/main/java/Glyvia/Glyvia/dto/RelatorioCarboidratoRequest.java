package Glyvia.Glyvia.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RelatorioCarboidratoRequest {
    private LocalDate dataRefeicao;
    private LocalTime horaRefeicao;
    private Double carboidratos;
}
