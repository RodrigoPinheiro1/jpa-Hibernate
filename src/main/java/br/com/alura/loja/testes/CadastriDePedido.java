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
import java.util.List;

public class CadastriDePedido {


    public static void main(String[] args) {

        popularBancoDeDados();
        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);
        ClienteDao clienteDao = new ClienteDao(em);


        Produto produto = produtoDao.buscarPorId(1l);
        Produto produto2 = produtoDao.buscarPorId(2l);

        Cliente cliente = clienteDao.buscarPorId(1l);


        em.getTransaction().begin();

        Pedido pedido = new Pedido(cliente);
        pedido.adicionarItem(new ItemPedido(10, pedido, produto));
        pedido.adicionarItem(new ItemPedido(10, pedido, produto2));

        Pedido pedido2 = new Pedido(cliente);
        pedido.adicionarItem(new ItemPedido(10, pedido, produto2));



        PedidoDao pedidoDao = new PedidoDao(em);
        pedidoDao.cadastrar(pedido);

        BigDecimal totalVendido = pedidoDao.valorTotalVendido();
        System.out.println("valor totoal" +totalVendido);


        List<RelatorioDeVendasVo> relatorio =pedidoDao.relatorioDeVendas();

        relatorio.forEach(System.out::println);



        em.getTransaction().commit();





    }

    /**
     *
     */
    private static void popularBancoDeDados() {

        Categoria celulares = new Categoria("CELULARES");
        Produto celular = new Produto("Xiaomi Redmi", "Muito legal", new BigDecimal("800"), celulares);
        Cliente cliente = new Cliente("rodrigo", "1231233");


        Categoria celulares2 = new Categoria("testeeee2");
        Produto celular2 = new Produto("IPHONE", "É UAI", new BigDecimal("9000"), celulares);
        Cliente cliente2 = new Cliente("EU", "99999999");

        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);
        CategoriaDao categoriaDao = new CategoriaDao(em);
        ClienteDao clienteDao = new ClienteDao(em);

        em.getTransaction().begin();

        clienteDao.cadastrar(cliente);
        categoriaDao.cadastrar(celulares);
        produtoDao.cadastrar(celular);

        clienteDao.cadastrar(cliente2);
        categoriaDao.cadastrar(celulares2);
        produtoDao.cadastrar(celular2);

        em.getTransaction().commit();
        em.close();

    }

}
