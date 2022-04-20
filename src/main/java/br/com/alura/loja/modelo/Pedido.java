package br.com.alura.loja.modelo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@Table (name = "pedidos")
public class Pedido {


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @Column (name = "valor_total")
        private BigDecimal valorTotal = BigDecimal.ZERO;
        private LocalDate data = LocalDate.now();


        // colando lazy faz com que não deixe pesado as consultas;
        @ManyToOne(fetch = FetchType.LAZY) // por padrão na jpa ela é carrega automaticamento, eager é carregamento antecipado;
        private  Cliente cliente;

    public Pedido(Cliente cliente) {
        this.cliente = cliente;
    }

    public  void adicionarItem(ItemPedido item){
        item.setPedido(this);
        this.itens.add(item);
        this.valorTotal = this.valorTotal.add(item.getValor());
    }
    @OneToMany  (mappedBy = "pedido", cascade = CascadeType.ALL)  //mapped by para funcionamento bidimencional cascade salvar em cascata, junto com o item pedido
// one to many ele não carrega tudo automaticamente, to many é lazy só acessa quando é chamado
    private List<ItemPedido> itens = new ArrayList<>(); // impede o null exception;

    public Pedido() {
    }
}
