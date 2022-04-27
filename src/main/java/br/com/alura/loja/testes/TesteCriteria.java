package br.com.alura.loja.testes;

import br.com.alura.loja.modelo.*;
import br.com.alura.loja.vo.RelatorioDeVendasVo;
import dao.CategoriaDao;
import dao.ClienteDao;
import dao.PedidoDao;
import dao.ProdutoDao;
import util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class TesteCriteria {

    public static void main(String[] args) {

        popularBancoDeDados();
        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);
        produtoDao.buscarPorParametrosComCriteria(null, null, LocalDate.now());


    }


    private static void popularBancoDeDados() {

        Categoria celulares = new Categoria("Celulares");
        Categoria videogames = new Categoria("VideoGames");
        Categoria informatica = new Categoria("informatica");

        Produto celular = new Produto("Xiaomi Redmi", "Muito legal", new BigDecimal("800"), celulares);
        Produto videogame = new Produto("PS5", "legal", new BigDecimal("800"), videogames);
        Produto macbook = new Produto("MACbook", "ruim", new BigDecimal("800"), informatica);

        Cliente cliente = new Cliente("Rodrigo Pinheiro Silva", "123123123");

        Pedido pedido = new Pedido(cliente);
        pedido.adicionarItem(new ItemPedido(10, pedido, celular));
        pedido.adicionarItem(new ItemPedido(40, pedido, videogame));

        Pedido pedido2 = new Pedido(cliente);
        pedido2.adicionarItem(new ItemPedido(2, pedido2, macbook));


        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);
        CategoriaDao categoriaDao = new CategoriaDao(em);
        ClienteDao clienteDao = new ClienteDao(em);
        PedidoDao pedidoDao = new PedidoDao(em);


        em.getTransaction().begin();

        categoriaDao.cadastrar(celulares);
        categoriaDao.cadastrar(videogames);
        categoriaDao.cadastrar(informatica);

        produtoDao.cadastrar(celular);
        produtoDao.cadastrar(videogame);
        produtoDao.cadastrar(macbook);

        pedidoDao.cadastrar(pedido);
        pedidoDao.cadastrar(pedido2);

        clienteDao.cadastrar(cliente);

        em.getTransaction().commit();
        em.close();


    }


}
