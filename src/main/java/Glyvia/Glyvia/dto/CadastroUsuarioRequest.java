package Glyvia.Glyvia.dto;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CadastroUsuarioRequest {
    @NotBlank
    private String email;

    @NotBlank
    private String senha;

    @NotBlank
    private String confirmarSenha;
}
