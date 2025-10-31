    package Glyvia.Glyvia.repository;

    import Glyvia.Glyvia.model.Usuario;
    import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.stereotype.Repository;

    import java.util.Optional;

    @Repository
    public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
        Optional<Usuario> findByEmail(String email); //Encontrar o usuario por email

    }
