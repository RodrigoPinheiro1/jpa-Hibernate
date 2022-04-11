package br.com.alura.loja.modelo;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.*;

@Entity
@Table(name = "produtos")
@Getter
@Setter
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String descricao;
	private BigDecimal preco;
	private LocalDate dataCadastro = LocalDate.now();
//    @Enumerated (EnumType.STRING) //Faz com que as informações fiquem gravadas em texto não por posição
	@ManyToOne //cardinalidade
	private Categoria categoria; //ENUM.


	public Produto(String nome, String descricao, BigDecimal preco, Categoria categoria) {

		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
		this.categoria = categoria;
	}

	public Produto() {

	}
}

