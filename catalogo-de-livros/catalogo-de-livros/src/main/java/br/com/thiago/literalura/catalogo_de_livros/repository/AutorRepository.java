package br.com.thiago.literalura.catalogo_de_livros.repository;

import br.com.thiago.literalura.catalogo_de_livros.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutorRepository extends JpaRepository<Autor, Long> {
}
