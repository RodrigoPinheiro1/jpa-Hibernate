package dao;

import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Produto;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class ProdutoDao {


    private EntityManager em;

    public ProdutoDao(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(Produto produto) {

        this.em.persist(produto);


    }public  void atualizar (Produto produto){

        this.em.merge(produto);
    }
    public Produto buscarPorId(Long id){

      return   em.find(Produto.class, id);
    }
    public List<Produto> buscarTodos() {

        String jpql = "SELECT p FROM Produto p";
        return em.createQuery(jpql, Produto.class).getResultList(); //JPQL
    }
    public List<Produto> buscarPorNome( String nome) {

        String jpql = "SELECT p FROM Produto p where  p.nome = : nome"; // dois pontos : faz com que seja dinamico o atributo namedParametrer

        return em.createQuery(jpql, Produto.class).setParameter("nome",nome) .getResultList();//JPQL
    }
    public List<Produto> buscarPorNomeaCategoria(String nome) {

        String jpql = "SELECT p FROM Produto p where  p.categoria.nome = : nome"; // dois pontos : faz com que seja dinamico o atributo namedParametrer

        return em.createQuery(jpql, Produto.class).setParameter("nome",nome) .getResultList();//JPQL
    }
    public BigDecimal buscarPrecoDoProdtuoComNome(String nome) {

        String jpql = "SELECT p.preco FROM Produto p where  p.nome = : nome"; // dois pontos : faz com que seja dinamico o atributo namedParametrer

        return em.createQuery(jpql, BigDecimal.class).setParameter("nome",nome) .getSingleResult();// carregando unico resultado;
    }

}
