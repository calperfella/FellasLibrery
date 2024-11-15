package com.tu_proyecto.service;

import com.tu_proyecto.model.Libro;
import com.tu_proyecto.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

@Service
public class GoogleBooksService {

    private static final String GOOGLE_BOOKS_API_URL = "https://www.googleapis.com/books/v1/volumes?q=";

    @Autowired
    private LibroRepository libroRepository;

    public List<Libro> buscarLibrosEnGoogle(String consulta) {
        List<Libro> libros = new ArrayList<>();
        String url = GOOGLE_BOOKS_API_URL + consulta;

        RestTemplate restTemplate = new RestTemplate();
        String respuesta = restTemplate.getForObject(url, String.class);

        JSONObject jsonResponse = new JSONObject(respuesta);
        JSONArray items = jsonResponse.getJSONArray("items");

        for (int i = 0; i < items.length(); i++) {
            JSONObject item = items.getJSONObject(i);
            JSONObject volumeInfo = item.getJSONObject("volumeInfo");

            Libro libro = new Libro();
            libro.setNombre(volumeInfo.optString("title", "Título no disponible"));
            libro.setAutor(volumeInfo.optJSONArray("authors") != null ? volumeInfo.getJSONArray("authors").join(", ")
                    : "Autor desconocido");
            libro.setIsbn(
                    volumeInfo.optJSONArray("industryIdentifiers") != null
                            ? volumeInfo.getJSONArray("industryIdentifiers").getJSONObject(0).optString("identifier",
                                    "ISBN no disponible")
                            : "ISBN no disponible");
            libro.setAño(volumeInfo.optString("publishedDate", "Fecha no disponible"));
            libro.setFormato("Digital"); // Puedes ajustar esto según tus necesidades
            libro.setPrecio(10000); // Valor de ejemplo
            libro.setStock(10); // Valor de ejemplo

            libros.add(libro);

            // Guardar el libro en la base de datos si no existe ya
            if (!libroRepository.existsByIsbn(libro.getIsbn())) {
                libroRepository.save(libro);
            }
        }
        return libros;
    }
}
