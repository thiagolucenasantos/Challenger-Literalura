package br.com.thiago.literalura.catalogo_de_livros.controller;

import br.com.thiago.literalura.catalogo_de_livros.model.Livro;
import br.com.thiago.literalura.catalogo_de_livros.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/livros")
public class LivroController {

    @Autowired
    private LivroService livroService;

    @GetMapping("/buscar")
    public Livro buscarLivroPorTitulo(@RequestParam String titulo) throws IOException, InterruptedException {
        return livroService.buscarLivroPorTitulo(titulo);
    }

    @GetMapping
    public List<Livro> listarLivros() {
        return livroService.listarLivros();
    }

}
