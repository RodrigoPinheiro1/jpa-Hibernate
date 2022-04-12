package br.com.alura.loja.modelo;

import com.sun.xml.bind.v2.runtime.Name;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "clientes")
@Getter
@Setter
public class Cliente  extends Pessoa{

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private  Long idCliente;

    public Cliente(String nome, String cpf ) {

        this.nome = nome;
        this.cpf = cpf;
    }

    public Cliente() {

    }
}
