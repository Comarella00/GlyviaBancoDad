package Glyvia.Glyvia.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RelatorioGlicemiaRequest {

    private LocalDate dataGlicemia;
    private LocalTime horaGlicemia;
    private Double valorGlicemia;
}
