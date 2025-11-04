package Glyvia.Glyvia.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AtualizaGlicemiaRequest {
    private Long idGlicemia; // ID que será atualizado
    private Double valorGlicemia;
    private LocalDate dataGlicemia;
    private LocalTime horaGlicemia;
    private Double checagemGlicemia;
    private Long idUsuario; // opcional — manter o vínculo com o usuário
}

