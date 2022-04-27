package dao;

import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Produto;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class ProdutoDao {


    private EntityManager em;

    public ProdutoDao(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(Produto produto) {

        this.em.persist(produto);


    }

    public void atualizar(Produto produto) {

        this.em.merge(produto);
    }

    public Produto buscarPorId(Long id) {

        return em.find(Produto.class, id);
    }

    public List<Produto> buscarTodos() {

        String jpql = "SELECT p FROM Produto p";
        return em.createQuery(jpql, Produto.class).getResultList(); //JPQL
    }

    public List<Produto> buscarPorNome(String nome) {

        String jpql = "SELECT p FROM Produto p where  p.nome = : nome"; // dois pontos : faz com que seja dinamico o atributo namedParametrer

        return em.createQuery(jpql, Produto.class).setParameter("nome", nome).getResultList();//JPQL
    }

    public List<Produto> buscarPorNomeaCategoria(String nome) {

        String jpql = "SELECT p FROM Produto p where  p.categoria.nome = : nome"; // dois pontos : faz com que seja dinamico o atributo namedParametrer

        return em.createQuery(jpql, Produto.class).setParameter("nome", nome).getResultList();//JPQL
    }

    public BigDecimal buscarPrecoDoProdtuoComNome(String nome) {

        String jpql = "SELECT p.preco FROM Produto p where  p.nome = : nome"; // dois pontos : faz com que seja dinamico o atributo namedParametrer

        return em.createQuery(jpql, BigDecimal.class).setParameter("nome", nome).getSingleResult();// carregando unico resultado;
    }

    public List<Produto> buscarPorParametros(String nome, BigDecimal preco, LocalDate dataCadastro) {


        String jpql = "SELECT p from Produto p where 1=1 ";

        if (nome != null && !nome.trim().isEmpty()) {
            jpql += "and p.nome =:nome";
        }
        if (preco != null) {

            jpql += "and p.preco =:preco";
        }
        if (dataCadastro != null) {
            jpql += "and p.dataCadastro =:dataCadastro";

        }
        TypedQuery<Produto> query = em.createQuery(jpql, Produto.class);

        if (nome != null && !nome.trim().isEmpty()) {
            query.setParameter("nome", nome);
        }
        if (preco != null) {

            query.setParameter("preco", preco);
        }
        if (dataCadastro != null) {
            query.setParameter("dataCadastro", dataCadastro);

        }
        return query.getResultList();
    }

    public List<Produto> buscarPorParametrosComCriteria(String nome, BigDecimal preco, LocalDate dataCadastro) {

        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Produto> query = criteriaBuilder.createQuery(Produto.class);
        Root<Produto> from = query.from(Produto.class); //faz o from do banco


        Predicate filtros = criteriaBuilder.and(); //faz o and
        if (nome != null && !nome.trim().isEmpty()) {

          filtros =  criteriaBuilder.and(filtros, criteriaBuilder.equal(from.get("nome"), nome)); //filtro pega o antigo  ge greater
        }
        if (preco != null) {

            filtros =  criteriaBuilder.and(filtros,criteriaBuilder.equal(from.get("preco"), preco));
        }
        if (dataCadastro != null) {

            filtros =  criteriaBuilder.and(filtros, criteriaBuilder.equal(from.get("dataCadastro"), dataCadastro));
        }
        query.where(filtros);

        return  em.createQuery(query).getResultList();
    }


}
