package br.com.alura.loja.modelo;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "categoria")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCategoria;
    private String nome;

    public Categoria(String nome) {
        this.nome = nome;
    }

    public Categoria() {

    }
}
