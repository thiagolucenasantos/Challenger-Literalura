package br.com.thiago.literalura.catalogo_de_livros.service;



import br.com.thiago.literalura.catalogo_de_livros.model.Autor;
import br.com.thiago.literalura.catalogo_de_livros.model.Livro;
import br.com.thiago.literalura.catalogo_de_livros.repository.AutorRepository;
import br.com.thiago.literalura.catalogo_de_livros.repository.LivroRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private AutorRepository autorRepository;

    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public Livro buscarLivroPorTitulo(String titulo) throws IOException, InterruptedException {
        String url = "https://gutendex.com/books?search=" + titulo;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        JsonNode root = objectMapper.readTree(response.body());

        JsonNode bookData = root.get("results").get(0);

        Autor autor = new Autor();
        autor.setNome(bookData.get("authors").get(0).get("name").asText());
        autor.setAnoNascimento(bookData.get("authors").get(0).get("birth_year").asInt());
        autor.setAnoFalecimento(bookData.get("authors").get(0).get("death_year").asInt());

        autor = autorRepository.save(autor);

        Livro livro = new Livro();
        livro.setTitulo(bookData.get("title").asText());
        livro.setIdioma(bookData.get("languages").get(0).asText());
        livro.setNumeroDownloads(bookData.get("download_count").asInt());
        livro.setAutor(autor);

        return livroRepository.save(livro);
    }

    public List<Livro> listarLivros() {
        return livroRepository.findAll();
    }
}
