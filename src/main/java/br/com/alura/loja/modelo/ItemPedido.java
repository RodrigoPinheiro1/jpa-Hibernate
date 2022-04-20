package br.com.alura.loja.modelo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(name = "itens_pedido")
public class ItemPedido {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id ;
    @Column (name = "preco_unitario")
        private BigDecimal precoUnitario;
        private int quantidade;

        @ManyToOne (fetch = FetchType.LAZY) //mapeamento to one faz tudo automatico carregando tudo;
        private Pedido pedido;
        @ManyToOne (fetch = FetchType.LAZY)
        private  Produto produto;

    public ItemPedido(int quantidade, Pedido pedido, Produto produto) {
        this.quantidade = quantidade;
        this.pedido = pedido;
        this.produto = produto;
        this.precoUnitario = produto.getPreco();
    }

    public ItemPedido() {

    }

    public BigDecimal getValor() {

        return  precoUnitario.multiply(new BigDecimal(quantidade));
    }
}
