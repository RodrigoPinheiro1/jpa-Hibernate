package br.com.alura.loja.vo;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Getter

public class RelatorioDeVendasVo {  //  Classe de valor


    private String nome;
    private Long quantidadeVendida;
    private LocalDate dataUltimaVenda;


    public RelatorioDeVendasVo(String nome, Long quantidadeVendida, LocalDate dataUltimaVenda) {
        this.nome = nome;
        this.quantidadeVendida = quantidadeVendida;
        this.dataUltimaVenda = dataUltimaVenda;
    }

    @Override
    public String toString() {
        return "RelatorioDeVendasVo{" +
                "nome='" + nome + '\'' +
                ", quantidadeVendida=" + quantidadeVendida +
                ", dataUltimaVenda=" + dataUltimaVenda +
                '}';

    }
}
