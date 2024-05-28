package br.com.thiago.literalura.catalogo_de_livros.repository;



import br.com.thiago.literalura.catalogo_de_livros.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<Livro, Long> {
}
