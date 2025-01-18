package com.ale.proyectos.literatura.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Optional;


@Entity
@Table(name = "libros")
@Data
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String titulo;
    @ManyToOne
    private Autor autor;
    @Enumerated(EnumType.STRING)
    private Idioma idioma;
    private Integer numeroDeDescargas;




    public Libro(LibroRecord libro) {
        this.titulo = libro.titulo();

        Optional<AutorRecord> autor = libro.autores().stream().findFirst();
        autor.ifPresent(autorRecord -> this.autor = new Autor(autorRecord));

        Optional<String> idioma = libro.idiomas().stream().findFirst();
        idioma.ifPresent(s -> this.idioma = Idioma.stringToEnum(s));

        this.numeroDeDescargas = libro.numeroDeDescargas();
    }

    public Libro() {
    }

    ;



    public void imprimirInformacion() {
        System.out.println("*****Libro*****");
        System.out.println("Titulo:" + titulo);
        System.out.println("Autor: " + autor.getNombre());
        System.out.println("Idioma: " + idioma.getIdiomaCompleto());
        System.out.println("Numero de Descargas: " + numeroDeDescargas);
        System.out.println("");
    }

    @Override
    public String toString() {
        return titulo;
    }
}
