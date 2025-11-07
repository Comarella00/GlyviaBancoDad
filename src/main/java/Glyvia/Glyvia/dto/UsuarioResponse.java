package Glyvia.Glyvia.dto;

import Glyvia.Glyvia.model.Usuario;

public class UsuarioResponse {

    private Long id;
    private String email;
    private String nome;
    private String genero;
    private String tipoInsulina;
    private String viaAplicacao;
    private Double pesoAtual;
    private Double altura;
    private Double metaGlicemica;
    private Double icr;
    private String dataNascimento;
    private String fotoPerfil; // ✅ novo campo

    // ✅ Construtor padrão (necessário para o Jackson e frameworks)
    public UsuarioResponse() {}

    // ✅ Construtor usado no stream
    public UsuarioResponse(Long id, String email, String nome, String genero, String tipoInsulina,
                           String viaAplicacao, Double pesoAtual, Double altura,
                           Double metaGlicemica, Double icr, String dataNascimento, String fotoPerfil) {
        this.id = id;
        this.email = email;
        this.nome = nome;
        this.genero = genero;
        this.tipoInsulina = tipoInsulina;
        this.viaAplicacao = viaAplicacao;
        this.pesoAtual = pesoAtual;
        this.altura = altura;
        this.metaGlicemica = metaGlicemica;
        this.icr = icr;
        this.dataNascimento = dataNascimento;
        this.fotoPerfil = fotoPerfil;
    }

    // ✅ Construtor que converte um entity em DTO (útil para o buscarPorId)
    public UsuarioResponse(Usuario usuario) {
        this(usuario.getId(), usuario.getEmail(), usuario.getNome(),
                usuario.getGenero(), usuario.getTipoInsulina(),
                usuario.getViaAplicacao(), usuario.getPesoAtual(),
                usuario.getAltura(), usuario.getMetaGlicemica(),
                usuario.getIcr(), usuario.getDataNascimento(),
                usuario.getFotoPerfil());
    }

    // ✅ Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }

    public String getTipoInsulina() { return tipoInsulina; }
    public void setTipoInsulina(String tipoInsulina) { this.tipoInsulina = tipoInsulina; }

    public String getViaAplicacao() { return viaAplicacao; }
    public void setViaAplicacao(String viaAplicacao) { this.viaAplicacao = viaAplicacao; }

    public Double getPesoAtual() { return pesoAtual; }
    public void setPesoAtual(Double pesoAtual) { this.pesoAtual = pesoAtual; }

    public Double getAltura() { return altura; }
    public void setAltura(Double altura) { this.altura = altura; }

    public Double getMetaGlicemica() { return metaGlicemica; }
    public void setMetaGlicemica(Double metaGlicemica) { this.metaGlicemica = metaGlicemica; }

    public Double getIcr() { return icr; }
    public void setIcr(Double icr) { this.icr = icr; }

    public String getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(String dataNascimento) { this.dataNascimento = dataNascimento; }

    public String getFotoPerfil() { return fotoPerfil; }
    public void setFotoPerfil(String fotoPerfil) { this.fotoPerfil = fotoPerfil; }
}
