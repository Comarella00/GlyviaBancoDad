package Glyvia.Glyvia.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RelatorioCaloriaRequest {
    private LocalDate dataRefeicao;
    private LocalTime horaRefeicao;
    private Double calorias;
}
