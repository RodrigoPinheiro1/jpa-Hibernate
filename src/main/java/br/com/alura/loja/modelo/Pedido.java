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
        private BigDecimal valorTotal;
        private LocalDate data = LocalDate.now();

        @ManyToOne
        private  Cliente cliente;

    public Pedido(Cliente cliente) {
        this.cliente = cliente;
    }

    public  void adicionarItem(ItemPedido item){
        item.setPedido(this);
        this.itens.add(item);
    }
    @OneToMany (mappedBy = "pedido") //
    private List<ItemPedido> itens = new ArrayList<>(); // impede o null exception;

    public Pedido() {
    }
}
