package br.com.alura.loja.testes;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Produto;
import dao.CategoriaDao;
import dao.ProdutoDao;
import util.JPAUtil;

public class CadastroDeProduto {

	public static void main(String[] args) {

		cadastrarProduto();
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);

		Produto p = produtoDao.buscarPorId(1l);
		System.out.println(p.getPreco());


		List <Produto> todos = produtoDao.buscarPorNomeaCategoria("CELULARES");

		todos.forEach(p2 -> System.out.println(p.getNome()));


		BigDecimal precoDoProduto = produtoDao.buscarPrecoDoProdtuoComNome("Xiaomi Redmi");

		System.out.println("Preço do produto "+precoDoProduto);

	}
		private static void cadastrarProduto (){

			Categoria celulares = new Categoria("CELULARES");
			Produto celular = new Produto("Xiaomi Redmi", "Muito legal", new BigDecimal("800"), celulares );

			EntityManager em = JPAUtil.getEntityManager();
			ProdutoDao produtoDao = new ProdutoDao(em);
			CategoriaDao categoriaDao = new CategoriaDao(em);

			em.getTransaction().begin();

			categoriaDao.cadastrar(celulares);
			produtoDao.cadastrar(celular);

			em.getTransaction().commit();


			em.close();

		}





	}


