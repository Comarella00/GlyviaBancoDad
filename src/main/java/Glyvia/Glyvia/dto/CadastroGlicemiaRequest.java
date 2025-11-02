package Glyvia.Glyvia.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CadastroGlicemiaRequest {

    @NotBlank
    private Double valorGlicemia;

    private LocalDate dataGlicemia;

    private LocalTime horaGlicemia;

    private Long id;
}
